package nz.co.chrisdrake.events.data.api.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.auto.value.AutoValue;

@AutoValue public abstract class Event implements Parcelable {
    public abstract long id();
    public abstract String name();
    public abstract String url();
    @Nullable public abstract String thumbnailUrl();
    public abstract String description();
    public abstract String dateTimeSummary();
    public abstract String locationSummary();
    public abstract boolean hasMultipleSessions();

    public static Builder builder() {
        return new AutoValue_Event.Builder();
    }

    @AutoValue.Builder public abstract static class Builder {
        public abstract Builder id(long id);
        public abstract Builder name(String name);
        public abstract Builder url(String url);
        public abstract Builder thumbnailUrl(@Nullable String thumbnailUrl);
        public abstract Builder description(String description);
        public abstract Builder dateTimeSummary(String dateTimeSummary);
        public abstract Builder locationSummary(String locationSummary);
        public abstract Builder hasMultipleSessions(boolean hasMultipleSessions);
        public abstract Event build();
    }
}
