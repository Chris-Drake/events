package nz.co.chrisdrake.events.data.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("unused") public final class DateAdapter {
    private static final DateFormat DATE_TIME_FORMATTER =
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    @ToJson String toJson(Date date) {
        return DATE_TIME_FORMATTER.format(date);
    }

    @FromJson Date fromJson(String dateTime) throws ParseException {
        return DATE_TIME_FORMATTER.parse(dateTime);
    }
}
