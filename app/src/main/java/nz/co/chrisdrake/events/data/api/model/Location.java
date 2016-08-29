package nz.co.chrisdrake.events.data.api.model;

import android.support.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/** @see LocationResource */
@AutoValue public abstract class Location {
    public static final Location NEW_ZEALAND = Location.builder()
        .id(574)
        .name("New Zealand")
        .summary("New Zealand")
        .build();

    public abstract int id();
    public abstract String name();
    @Nullable public abstract String summary();
    @Nullable public abstract LocationChildResource children();

    public static JsonAdapter<Location> jsonAdapter(Moshi moshi) {
        return new AutoValue_Location.MoshiJsonAdapter(moshi);
    }

    static Builder builder() {
        return new AutoValue_Location.Builder();
    }

    @AutoValue.Builder abstract static class Builder {
        abstract Builder id(int id);
        abstract Builder name(String name);
        abstract Builder summary(String summary);
        abstract Builder children(LocationChildResource children);
        abstract Location build();
    }
}
