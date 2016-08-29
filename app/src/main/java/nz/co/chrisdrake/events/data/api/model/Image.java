package nz.co.chrisdrake.events.data.api.model;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/** @see ImageResource */
@AutoValue public abstract class Image implements Parcelable {
    @Json(name = "is_primary") public abstract boolean isPrimary();
    public abstract TransformResource transforms();

    static Image create(boolean isPrimary, TransformResource transforms) {
        return new AutoValue_Image(isPrimary, transforms);
    }

    public static JsonAdapter<Image> jsonAdapter(Moshi moshi) {
        return new AutoValue_Image.MoshiJsonAdapter(moshi);
    }
}
