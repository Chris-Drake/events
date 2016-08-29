package nz.co.chrisdrake.events.data.api.model;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.List;

/** @see Session */
@AutoValue public abstract class SessionResource implements Parcelable {
    public abstract List<Session> sessions();

    static SessionResource create(List<Session> sessions) {
        return new AutoValue_SessionResource(sessions);
    }

    public static JsonAdapter<SessionResource> jsonAdapter(Moshi moshi) {
        return new AutoValue_SessionResource.MoshiJsonAdapter(moshi);
    }
}
