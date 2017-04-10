package nz.co.chrisdrake.events.data.api.model

import com.squareup.moshi.Json
import java.util.*

data class EventResource(val events: List<Event>)

data class Event(
        val id: Long,
        val name: String,
        val url: String,
        val description: String,
        val address: String,
        val point: Point,
        val restrictions: String?,
        @Json(name = "datetime_start") val dateStart: Date,
        @Json(name = "datetime_end") val dateEnd: Date,
        @Json(name = "datetime_summary") val dateTimeSummary: String,
        @Json(name = "location_summary") val locationSummary: String,
        @Json(name = "is_free") val isFree: Boolean,
        @Json(name = "is_featured") val isFeatured: Boolean,
        @Json(name = "images") val imageResource: ImageResource,
        @Json(name = "sessions") val sessionResource: SessionResource
) {

    fun thumbnailUrl(): String? {
        val preferredThumbnailSizes = listOf(
                TransformSize._650x280,
                TransformSize._190x127,
                TransformSize._350x350,
                TransformSize._80X80,
                TransformSize._75x75,
                TransformSize.OTHER)

        val transformResource = imageResource.primaryImage?.transforms ?: return null

        return preferredThumbnailSizes
                .mapNotNull { transformResource.getTransformWithSize(it)?.url }
                .firstOrNull()
    }

    fun sessionsFrom(startMillis: Long): List<Session> {
        return sessionResource.sessions.filter { (_, dateEnd) -> dateEnd.time >= startMillis }
    }
}

data class ImageResource(val images: List<Image>) {
    val primaryImage: Image?
        get() {
            return images.firstOrNull { it.isPrimary }
        }
}

data class Image(
        val transforms: TransformResource,
        @Json(name = "is_primary") val isPrimary: Boolean
)

data class Point(val lat: Double, val lng: Double)

data class SessionResource(val sessions: List<Session>)

data class Session(
        @Json(name = "datetime_start") val dateStart: Date,
        @Json(name = "datetime_end") val dateEnd: Date,
        @Json(name = "datetime_summary") val dateTimeSummary: String
)

data class TransformResource(val transforms: List<Transform>) {
    fun getTransformWithSize(size: TransformSize): Transform? {
        return transforms.firstOrNull { it.transformSize == size }
    }
}

data class Transform(
        val url: String,
        val width: Int,
        val height: Int,
        @Json(name = "transformation_id") val transformSize: TransformSize
)

/** @see "http://www.eventfinda.co.nz/api/v2/image" */
enum class TransformSize {
    @Json(name = "2") _80X80,
    @Json(name = "7") _650x280,
    @Json(name = "8") _190x127,
    @Json(name = "15") _75x75,
    @Json(name = "27") _350x350,
    OTHER
}