package nz.co.chrisdrake.events.data.api.model;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;

import static nz.co.chrisdrake.events.data.api.model.Transform.TransformSize;

/** @see Transform */
@AutoValue public abstract class TransformResource implements Parcelable {
    public abstract List<Transform> transforms();

    static TransformResource create(List<Transform> transforms) {
        return new AutoValue_TransformResource(transforms);
    }

    /**
     * Attempts to find a {@link Transform} object whose size matches the
     * {@link TransformSize} argument.
     *
     * @param size the size to match
     * @return the Transform object with the matching size
     */
    @Nullable public Transform getTransformWithSize(@NonNull TransformSize size) {
        for (Transform transform : transforms()) {
            if (transform.transformSize() == size) {
                return transform;
            }
        }
        return null;
    }

    public static JsonAdapter<TransformResource> jsonAdapter(Moshi moshi) {
        return new AutoValue_TransformResource.MoshiJsonAdapter(moshi);
    }
}
