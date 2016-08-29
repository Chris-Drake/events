package nz.co.chrisdrake.events.data.api.model;

import java.util.Collections;

public final class MockLocations {
    private static final Location SAN_FRANCISCO = Location.builder()
        .id(101)
        .name("San Francisco")
        .build();

    static final Location UNITED_STATES = Location.builder()
        .id(100)
        .name("United States")
        .children(LocationChildResource.create(Collections.singletonList(SAN_FRANCISCO)))
        .build();

    private MockLocations() {
        throw new AssertionError("Non-instantiable.");
    }
}
