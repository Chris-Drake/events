package nz.co.chrisdrake.events.data.api.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.Date;

/** @see EventResource */
@AutoValue public abstract class Event implements Parcelable {
    public abstract long id();
    public abstract String name();
    public abstract String url();
    public abstract String description();
    public abstract String address();
    public abstract Point point();
    @Nullable public abstract String restrictions();
    @Json(name = "datetime_start") public abstract Date dateStart();
    @Json(name = "datetime_end") public abstract Date dateEnd();
    @Json(name = "datetime_summary") public abstract String dateTimeSummary();
    @Json(name = "location_summary") public abstract String locationSummary();
    @Json(name = "is_free") public abstract boolean isFree();
    @Json(name = "is_featured") public abstract boolean isFeatured();
    @Json(name = "images") public abstract ImageResource imageResource();
    @Json(name = "sessions") public abstract SessionResource sessionResource();

    public static JsonAdapter<Event> jsonAdapter(Moshi moshi) {
        return new AutoValue_Event.MoshiJsonAdapter(moshi);
    }

    static Builder builder() {
        return new AutoValue_Event.Builder();
    }

    @AutoValue.Builder abstract static class Builder {
        abstract Builder id(long id);
        abstract Builder name(String name);
        abstract Builder url(String url);
        abstract Builder description(String description);
        abstract Builder address(String address);
        abstract Builder point(Point point);
        abstract Builder restrictions(String restrictions);
        abstract Builder dateStart(Date dateStart);
        abstract Builder dateEnd(Date dateEnd);
        abstract Builder dateTimeSummary(String dateTimeSummary);
        abstract Builder locationSummary(String locationSummary);
        abstract Builder isFree(boolean isFree);
        abstract Builder isFeatured(boolean isFeatured);
        abstract Builder imageResource(ImageResource imageResource);
        abstract Builder sessionResource(SessionResource sessionResource);
        abstract Event build();
    }
}
