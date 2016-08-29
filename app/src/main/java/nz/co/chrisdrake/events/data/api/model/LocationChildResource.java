package nz.co.chrisdrake.events.data.api.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;

/** @see Location */
@AutoValue public abstract class LocationChildResource {
    public abstract List<Location> children();

    static LocationChildResource create(List<Location> children) {
        return new AutoValue_LocationChildResource(children);
    }

    public static JsonAdapter<LocationChildResource> jsonAdapter(Moshi moshi) {
        return new AutoValue_LocationChildResource.MoshiJsonAdapter(moshi);
    }
}
