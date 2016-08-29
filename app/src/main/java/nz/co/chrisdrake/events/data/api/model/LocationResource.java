package nz.co.chrisdrake.events.data.api.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;

/** @see Location */
@AutoValue public abstract class LocationResource {
    public abstract List<Location> locations();

    public static LocationResource create(List<Location> locations) {
        return new AutoValue_LocationResource(locations);
    }

    public static JsonAdapter<LocationResource> jsonAdapter(Moshi moshi) {
        return new AutoValue_LocationResource.MoshiJsonAdapter(moshi);
    }
}
