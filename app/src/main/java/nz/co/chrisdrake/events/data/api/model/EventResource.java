package nz.co.chrisdrake.events.data.api.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;

/** @see Event */
@AutoValue public abstract class EventResource {
    public abstract List<Event> events();

    static EventResource create(List<Event> events) {
        return new AutoValue_EventResource(events);
    }

    public static JsonAdapter<EventResource> jsonAdapter(Moshi moshi) {
        return new AutoValue_EventResource.MoshiJsonAdapter(moshi);
    }
}
