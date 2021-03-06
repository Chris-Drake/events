package nz.co.chrisdrake.events.ui.explore;

import io.realm.RealmResults;
import java.util.Collections;
import java.util.List;
import nz.co.chrisdrake.events.data.api.DateQuery;
import nz.co.chrisdrake.events.data.api.EventFinderService;
import nz.co.chrisdrake.events.domain.interactor.FindEvents;
import nz.co.chrisdrake.events.data.api.LocationQuery;
import nz.co.chrisdrake.events.data.api.Order;
import nz.co.chrisdrake.events.data.api.model.EventResource;
import nz.co.chrisdrake.events.data.api.model.Location;
import nz.co.chrisdrake.events.data.api.model.LocationResource;
import nz.co.chrisdrake.events.data.api.model.MockEventResponse;
import nz.co.chrisdrake.events.data.realm.RealmHelper;
import nz.co.chrisdrake.events.data.realm.model.RealmLocation;
import nz.co.chrisdrake.events.domain.model.Event;
import nz.co.chrisdrake.events.ui.ViewState;
import org.joda.time.Interval;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rx.Observable;
import rx.functions.Func0;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) public class ExplorePresenterTest {
    @Mock EventFinderService mockService;
    @Mock ExploreView mockView;
    @Mock RealmHelper mockRealmHelper;
    @Mock ExploreFilter mockFilter;

    private ExplorePresenter presenter;

    @Before public void setUp() {
        initMocks();

        FindEvents findEvents = new FindEvents(mockService, mockFilter);
        presenter = spy(new ExplorePresenter(mockService, findEvents, mockRealmHelper));
        presenter.setView(mockView);

        when(presenter.applySchedulers()).thenReturn(new Observable.Transformer<Object, Object>() {
                @Override public Observable<Object> call(Observable<Object> objectObservable) {
                    return objectObservable;
                }
            });
    }

    @Test public void requestEvents_FilterWithNoOffset_RefreshViewStateSet() {
        initMockFilterWithNoOffset();
        presenter.requestEvents();
        verify(mockView).setViewState(ViewState.REFRESHING);
    }

    @Test public void requestEvents_FilterWithOffset_RefreshViewStateNotSet() {
        initMockFilterWithOffset();
        presenter.requestEvents();
        verify(mockView, never()).setViewState(ViewState.REFRESHING);
    }

    @Test public void requestEvents_FailResponse_ErrorShown() {
        initMockServiceWithEventException(mockService);
        presenter.requestEvents();
        verify(mockView).showErrorMessage(anyString());
    }

    @Test public void requestEvents_AddEventsCalled() {
        initMockServiceWithEventSuccessResponse(mockService);
        presenter.requestEvents();
        verify(presenter).addEvents(anyListOf(Event.class));
    }

    @Test public void addEvents_EmptyList_LoadMoreDisabled() {
        presenter.addEvents(Collections.emptyList());
        verify(mockView).disableLoadMore();
    }

    @Test public void addEvents_MoreThanZeroEvents_LoadMoreEnabled() {
        //noinspection unchecked
        List<Event> events = mock(List.class);
        doReturn(1).when(events).size();

        presenter.addEvents(events);

        verify(mockView).enableLoadMore();
    }

    @Test public void attemptRefresh_LoadMoreDisabled() {
        presenter.attemptRefresh();
        verify(mockView).disableLoadMore();
    }

    @Test public void loadExistingLocations_NoExistingLocations_RequestLocationsCalled() {
        initMockRealmHelperWithNoPersistedLocations(mockRealmHelper);
        initMockServiceWithLocationSuccessResponse(mockService);
        presenter.loadExistingLocations();
        verify(presenter).requestLocations();
    }

    @Test public void requestLocations_SuccessResponse_PersistLocationsCalled() {
        initMockRealmHelperWithNoPersistedLocations(mockRealmHelper);
        initMockServiceWithLocationSuccessResponse(mockService);
        presenter.requestLocations();
        verify(mockRealmHelper).setPersistedLocations(anyListOf(Location.class));
    }

    private void initMocks() {
        initMockServiceWithEventSuccessResponse(mockService);

        when(mockFilter.getInterval()).thenReturn(new Interval(0, 0));
    }

    private void initMockFilterWithNoOffset() {
        doReturn(0).when(mockView).getOffset();
    }

    private void initMockFilterWithOffset() {
        doReturn(1).when(mockView).getOffset();
    }

    private void initMockServiceWithEventSuccessResponse(EventFinderService service) {
        EventResource eventResource = MockEventResponse.GOOGLE_IO;
        when(service.events(any(Order.class), any(LocationQuery.class), any(DateQuery.class),
            any(DateQuery.class), anyInt(), anyInt(), anyInt())).thenReturn(
            Observable.just(eventResource));
    }

    private void initMockServiceWithEventException(EventFinderService service) {
        when(service.events(any(Order.class), any(LocationQuery.class), any(DateQuery.class),
            any(DateQuery.class), anyInt(), anyInt(), anyInt())).thenReturn(
            Observable.defer(new Func0<Observable<EventResource>>() {
                @Override public Observable<EventResource> call() {
                    throw new RuntimeException();
                }
            }));
    }

    private void initMockServiceWithLocationSuccessResponse(EventFinderService service) {
        LocationResource locationResource =
            new LocationResource(Collections.singletonList(Location.NEW_ZEALAND));
        when(service.locations(any(LocationQuery.class), anyInt(), anyInt())).thenReturn(
            Observable.just(locationResource));
    }

    private void initMockRealmHelperWithNoPersistedLocations(RealmHelper realmHelper) {
        RealmResults<RealmLocation> persistedLocations = mock(RealmResults.class);
        when(persistedLocations.size()).thenReturn(0);
        when(realmHelper.getPersistedLocations()).thenReturn(persistedLocations);
    }
}