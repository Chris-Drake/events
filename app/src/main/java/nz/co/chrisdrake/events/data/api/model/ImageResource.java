package nz.co.chrisdrake.events.data.api.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;

/** @see Image */
@AutoValue public abstract class ImageResource implements Parcelable {
    public abstract List<Image> images();

    static ImageResource create(List<Image> images) {
        return new AutoValue_ImageResource(images);
    }

    @Nullable public Image getPrimaryImage() {
        for (Image image : images()) {
            if (image.isPrimary()) {
                return image;
            }
        }
        return null;
    }

    public static JsonAdapter<ImageResource> jsonAdapter(Moshi moshi) {
        return new AutoValue_ImageResource.MoshiJsonAdapter(moshi);
    }
}
