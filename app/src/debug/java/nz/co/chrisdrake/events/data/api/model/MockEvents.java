package nz.co.chrisdrake.events.data.api.model;

import java.util.Collections;
import nz.co.chrisdrake.events.data.api.model.EventResource.Event;

import static nz.co.chrisdrake.events.data.api.model.MockEventSessions.KEYNOTE_SESSION;
import static nz.co.chrisdrake.events.data.api.model.MockEventSessions.NEW_IN_ANDROID_SESSION;

public final class MockEvents {
    private static final Point COORDINATES = Point.create(37.783, -122.401);

    static final Event KEYNOTE = Event.builder()
        .id(1)
        .name("Keynote")
        .url("https://events.google.com/io2015/schedule?sid=__keynote__")
        .description("Join us to learn about ...")
        .dateStart(KEYNOTE_SESSION.dateStart())
        .dateEnd(KEYNOTE_SESSION.dateEnd())
        .point(COORDINATES)
        .locationSummary("Keynote Room (L3)")
        .address("Moscone West Convention Center")
        .isFree(false)
        .isFeatured(true)
        .dateTimeSummary(KEYNOTE_SESSION.dateTimeSummary())
        .imageResource(MockEventImages.IO_RESOURCE)
        .sessionResource(SessionResource.create(Collections.singletonList(KEYNOTE_SESSION)))
        .build();

    static final Event NEW_IN_ANDROID = Event.builder()
        .id(2)
        .name("What's new in Android")
        .url("https://events.google.com/io2015/schedule?sid=ea96312e-e3d3-e411-b87f-00155d5066d7")
        .description("This session will ...")
        .dateStart(NEW_IN_ANDROID_SESSION.dateStart())
        .dateEnd(NEW_IN_ANDROID_SESSION.dateEnd())
        .point(COORDINATES)
        .locationSummary("Room 3 (L2)")
        .address("Moscone West Convention Center")
        .isFree(false)
        .isFeatured(false)
        .dateTimeSummary(NEW_IN_ANDROID_SESSION.dateTimeSummary())
        .imageResource(MockEventImages.IO_RESOURCE)
        .sessionResource(SessionResource.create(Collections.singletonList(NEW_IN_ANDROID_SESSION)))
        .build();

    private MockEvents() {
        throw new AssertionError("Non-instantiable.");
    }
}
