package nz.co.chrisdrake.events.data.api;

import android.support.annotation.VisibleForTesting;
import java.util.List;
import javax.inject.Inject;
import nz.co.chrisdrake.events.ActivityScope;
import nz.co.chrisdrake.events.data.api.model.Event;
import nz.co.chrisdrake.events.data.api.model.EventResource;
import nz.co.chrisdrake.events.data.api.model.Session;
import nz.co.chrisdrake.events.ui.explore.ExploreFilter;
import org.joda.time.Interval;
import rx.Observable;

@ActivityScope public final class FindEvents {

    private final EventFinderService eventFinderService;
    private final ExploreFilter filter;

    @Inject @VisibleForTesting
    public FindEvents(EventFinderService eventFinderService, ExploreFilter filter) {
        this.eventFinderService = eventFinderService;
        this.filter = filter;
    }

    public Observable<List<Event>> buildObservable(int offset) {
        LocationQuery locationQuery =
            new LocationQuery.Builder().location(filter.getLocationId()).build();

        Interval interval = filter.getInterval();

        Observable<EventResource> request = eventFinderService.events( //
            Order.POPULARITY, //
            locationQuery, //
            new DateQuery(interval.getStart()), //
            new DateQuery(interval.getEnd()), //
            filter.isFeaturedOnly() ? 1 : 0, //
            filter.isFreeOnly() ? 1 : 0, //
            offset);

        return request //
            .map(EventResource::events) //
            .flatMap(Observable::from) //
            .flatMap(event -> //
                Observable.just(event.sessionResource().sessions())
                    .compose(futureSessions(interval.getStartMillis()))
                    .map(futureSessions -> {
                        final String dateTimeSummary;
                        if (futureSessions.size() > 0) {
                            dateTimeSummary = futureSessions.get(0).dateTimeSummary();
                        } else {
                            dateTimeSummary = event.dateTimeSummary();
                        }

                        return Event.builder()
                            .id(event.id())
                            .name(event.name())
                            .url(event.url())
                            .thumbnailUrl(event.thumbnailUrl())
                            .description(event.description())
                            .hasMultipleSessions(futureSessions.size() > 1)
                            .dateTimeSummary(dateTimeSummary)
                            .locationSummary(event.locationSummary())
                            .build();
                    })) //
            .toList();
    }

    private Observable.Transformer<List<Session>, List<Session>> futureSessions(long startMillis) {
        return observable -> observable.flatMap(Observable::from)
            .filter(session -> session.dateEnd().getTime() >= startMillis)
            .toList();
    }
}
