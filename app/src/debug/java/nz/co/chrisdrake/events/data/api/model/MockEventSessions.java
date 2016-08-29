package nz.co.chrisdrake.events.data.api.model;

import java.util.Date;
import org.joda.time.LocalDate;

public final class MockEventSessions {
    private static final Date MAY_28 = LocalDate.parse("2015-05-28").toDate();

    static final Session KEYNOTE_SESSION = Session.builder()
        .dateStart(MAY_28)
        .dateEnd(MAY_28)
        .dateTimeSummary("May 28, 8 AM")
        .build();

    static final Session NEW_IN_ANDROID_SESSION = Session.builder()
        .dateStart(MAY_28)
        .dateEnd(MAY_28)
        .dateTimeSummary("May 28 / 1:00 PM - 2:00 PM")
        .build();

    private MockEventSessions() {
        throw new AssertionError("Non-instantiable.");
    }
}
