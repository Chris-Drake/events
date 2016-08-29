package nz.co.chrisdrake.events.data.api.model;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/** @see TransformResource */
@AutoValue public abstract class Transform implements Parcelable {
    /**
     * Represents different image sizes
     *
     * @see "http://www.eventfinda.co.nz/api/v2/image"
     */
    public enum TransformSize {
        @Json(name = "2")_80X80,
        @Json(name = "7")_650x280,
        @Json(name = "8")_190x127,
        @Json(name = "15")_75x75,
        @Json(name = "27")_350x350,
        OTHER
    }

    public abstract String url();
    public abstract int width();
    public abstract int height();
    @Json(name = "transformation_id") public abstract TransformSize transformSize();

    public static JsonAdapter<Transform> jsonAdapter(Moshi moshi) {
        return new AutoValue_Transform.MoshiJsonAdapter(moshi);
    }

    static Builder builder() {
        return new AutoValue_Transform.Builder();
    }

    @AutoValue.Builder abstract static class Builder {
        abstract Builder url(String url);
        abstract Builder width(int width);
        abstract Builder height(int height);
        abstract Builder transformSize(TransformSize transformSize);
        abstract Transform build();
    }
}
