package nz.co.chrisdrake.events.domain.interactor

import android.support.annotation.VisibleForTesting
import nz.co.chrisdrake.events.ActivityScope
import nz.co.chrisdrake.events.data.api.DateQuery
import nz.co.chrisdrake.events.data.api.EventFinderService
import nz.co.chrisdrake.events.data.api.LocationQuery
import nz.co.chrisdrake.events.domain.model.Event
import nz.co.chrisdrake.events.ui.explore.ExploreFilter
import rx.Observable
import javax.inject.Inject

@ActivityScope class FindEvents @Inject @VisibleForTesting constructor(
        private val eventFinderService: EventFinderService,
        private val filter: ExploreFilter
) {

    fun buildObservable(offset: Int): Observable<List<Event>> {
        val locationQuery = LocationQuery.Builder().location(filter.locationId).build()
        val interval = filter.interval

        val request = eventFinderService.events(
                location = locationQuery,
                startDate = DateQuery(interval.start),
                endDate = DateQuery(interval.end),
                featured = if (filter.isFeaturedOnly) 1 else 0,
                free = if (filter.isFreeOnly) 1 else 0,
                offset = offset)

        return request
                .flatMapIterable { it.events }
                .map { event ->
                    val futureSessions = event.sessionsFrom(interval.startMillis)
                    
                    Event(
                            id = event.id,
                            name = event.name,
                            url = event.url,
                            thumbnailUrl = event.thumbnailUrl(),
                            description = event.description,
                            dateTimeSummary = futureSessions.getOrNull(0)?.dateTimeSummary ?: event.dateTimeSummary,
                            locationSummary = event.locationSummary,
                            hasMultipleSessions = futureSessions.size > 1)
                }
                .toList()
    }
}
