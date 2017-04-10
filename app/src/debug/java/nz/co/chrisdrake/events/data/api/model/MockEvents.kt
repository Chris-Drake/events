package nz.co.chrisdrake.events.data.api.model

import org.joda.time.LocalDate

object MockEvents {
    private val COORDINATES = Point(37.783, -122.401)

    val KEYNOTE = Event(
            id = 1,
            name = "Keynote",
            url = "https://events.google.com/io2015/schedule?sid=__keynote__",
            description = "Join us to learn about ...",
            dateStart = Sessions.KEYNOTE_SESSION.dateStart,
            dateEnd = Sessions.KEYNOTE_SESSION.dateEnd,
            point = COORDINATES,
            locationSummary = "Keynote Room (L3)",
            address = "Moscone West Convention Center",
            isFree = false,
            isFeatured = true,
            dateTimeSummary = Sessions.KEYNOTE_SESSION.dateTimeSummary,
            imageResource = Images.IO_RESOURCE,
            sessionResource = SessionResource(listOf(Sessions.KEYNOTE_SESSION)),
            restrictions = null)

    val NEW_IN_ANDROID = Event(
            id = 2,
            name = "What's new in Android",
            url = "https://events.google.com/io2015/schedule?sid=ea96312e-e3d3-e411-b87f-00155d5066d7",
            description = "This session will...",
            dateStart = Sessions.NEW_IN_ANDROID_SESSION.dateStart,
            dateEnd = Sessions.NEW_IN_ANDROID_SESSION.dateEnd,
            point = COORDINATES,
            locationSummary = "Room 3 (L2)",
            address = "Moscone West Convention Center",
            isFree = false,
            isFeatured = false,
            dateTimeSummary = Sessions.NEW_IN_ANDROID_SESSION.dateTimeSummary,
            imageResource = Images.IO_RESOURCE,
            sessionResource = SessionResource(listOf(Sessions.NEW_IN_ANDROID_SESSION)),
            restrictions = null)

    private object Sessions {
        private val MAY_28 = LocalDate.parse("2015-05-28").toDate()

        val KEYNOTE_SESSION = Session(
                dateStart = MAY_28,
                dateEnd = MAY_28,
                dateTimeSummary = "May 28, 8 AM")

        val NEW_IN_ANDROID_SESSION = Session(
                dateStart = MAY_28,
                dateEnd = MAY_28,
                dateTimeSummary = "May 28 / 1:00 PM - 2:00 PM")
    }

    private object Images {
        val IO = Image(isPrimary = true,
                transforms = TransformResource(
                        transforms = listOf(Transform(
                                url = "https://events.google.com/io2015/images/io15-color.png",
                                width = 800,
                                height = 600,
                                transformSize = TransformSize.OTHER))))

        val IO_RESOURCE = ImageResource(listOf(IO))
    }
}
