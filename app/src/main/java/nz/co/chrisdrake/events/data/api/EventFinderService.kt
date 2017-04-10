package nz.co.chrisdrake.events.data.api

import android.support.annotation.IntRange
import nz.co.chrisdrake.events.data.api.model.EventResource
import nz.co.chrisdrake.events.data.api.model.LocationResource
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/** @see "http://www.eventfinda.co.nz/api/v2/overview" */
interface EventFinderService {
    @GET("events.json") fun events(
            @Query("order") order: Order = Order.POPULARITY,
            @Query("location") location: LocationQuery,
            @Query("start_date") startDate: DateQuery,
            @Query("end_date") endDate: DateQuery,
            @Query("featured") @IntRange(from = 0, to = 1) featured: Int,
            @Query("free") @IntRange(from = 0, to = 1) free: Int,
            @Query("offset") offset: Int): Observable<EventResource>

    @GET("locations.json") fun locations(
            @Query("location") location: LocationQuery,
            @Query("rows") rows: Int,
            @Query("levels") levels: Int): Observable<LocationResource>
}
