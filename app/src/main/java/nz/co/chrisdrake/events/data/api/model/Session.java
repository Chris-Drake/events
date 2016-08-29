package nz.co.chrisdrake.events.data.api.model;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.Date;

/** @see SessionResource */
@AutoValue public abstract class Session implements Parcelable {
    @Json(name = "datetime_start") public abstract Date dateStart();
    @Json(name = "datetime_end") public abstract Date dateEnd();
    @Json(name = "datetime_summary") public abstract String dateTimeSummary();

    public static JsonAdapter<Session> jsonAdapter(Moshi moshi) {
        return new AutoValue_Session.MoshiJsonAdapter(moshi);
    }

    static Builder builder() {
        return new AutoValue_Session.Builder();
    }

    @AutoValue.Builder abstract static class Builder {
        abstract Builder dateStart(Date dateStart);
        abstract Builder dateEnd(Date dateEnd);
        abstract Builder dateTimeSummary(String dateTimeSummary);
        abstract Session build();
    }
}
