package nz.co.chrisdrake.events.data.api.model

data class LocationResource(val locations: List<Location>)

data class Location(
        @JvmField val id: Int,
        @JvmField val name: String,
        @JvmField val summary: String? = null,
        @JvmField val children: LocationChildResource? = null
) {
    companion object {
        @JvmField val NEW_ZEALAND = Location(
                id = 574,
                name = "New Zealand",
                summary = "New Zealand")
    }
}

data class LocationChildResource(@JvmField val children: List<Location>)