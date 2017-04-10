package nz.co.chrisdrake.events.data.api.model

object MockEventResponse {
    @JvmField val GOOGLE_IO = EventResource(events = listOf(
            MockEvents.KEYNOTE,
            MockEvents.NEW_IN_ANDROID))
    @JvmField val EMPTY = EventResource(emptyList<Event>())
}
