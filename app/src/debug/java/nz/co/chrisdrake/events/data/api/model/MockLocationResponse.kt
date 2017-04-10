package nz.co.chrisdrake.events.data.api.model

object MockLocationResponse {
    @JvmField val UNITED_STATES = LocationResource(locations = listOf(Locations.UNITED_STATES))

    private object Locations {
        val SAN_FRANCISCO = Location(
                id = 101,
                name = "San Francisco")

        val UNITED_STATES = Location(
                id = 100,
                name = "United States",
                children = LocationChildResource(children = listOf(SAN_FRANCISCO)))
    }
}
