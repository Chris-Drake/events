package nz.co.chrisdrake.events.data.api.model;

import java.util.Collections;

public final class MockLocationResponse {
    public static final LocationResource UNITED_STATES =
        LocationResource.create(Collections.singletonList(MockLocations.UNITED_STATES));

    private MockLocationResponse() {
        throw new AssertionError("Non-instantiable.");
    }
}
