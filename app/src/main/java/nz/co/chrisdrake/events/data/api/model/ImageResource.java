package nz.co.chrisdrake.events.data.api.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;

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

    @AutoValue public abstract static class Image implements Parcelable {
        @Json(name = "is_primary") public abstract boolean isPrimary();

        public abstract TransformResource transforms();

        static Image create(boolean isPrimary, TransformResource transforms) {
            return new AutoValue_ImageResource_Image(isPrimary, transforms);
        }

        public static JsonAdapter<Image> jsonAdapter(Moshi moshi) {
            return new AutoValue_ImageResource_Image.MoshiJsonAdapter(moshi);
        }
    }
}
