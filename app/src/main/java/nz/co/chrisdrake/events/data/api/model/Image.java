package nz.co.chrisdrake.events.data.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.squareup.moshi.Json;

/** @see ImageResource */
public class Image implements Parcelable {
    @Json(name = "is_primary") public final boolean isPrimary;

    public final TransformResource transforms;

    public Image(boolean isPrimary, TransformResource transforms) {
        this.isPrimary = isPrimary;
        this.transforms = transforms;
    }

    protected Image(Parcel in) {
        transforms = in.readParcelable(TransformResource.class.getClassLoader());
        isPrimary = in.readByte() == 1;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(transforms, flags);
        dest.writeByte((byte) (isPrimary ? 1 : 0));
    }

    @Override public int describeContents() {
        return 0;
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
