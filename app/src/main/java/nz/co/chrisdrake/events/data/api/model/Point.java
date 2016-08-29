package nz.co.chrisdrake.events.data.api.model;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/** @see Event */
@AutoValue public abstract class Point implements Parcelable {
    public abstract double lat();
    public abstract double lng();

    static Point create(double lat, double lng) {
        return new AutoValue_Point(lat, lng);
    }

    public static JsonAdapter<Point> jsonAdapter(Moshi moshi) {
        return new AutoValue_Point.MoshiJsonAdapter(moshi);
    }
}
