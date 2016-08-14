package nz.co.chrisdrake.events.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

@Module public class DataModule {
    @Provides @Singleton SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides @Singleton OkHttpClient provideHttpClient(Context context) {
        return createOkHttpClient(context);
    }

    @Provides @Singleton Picasso providePicasso(Context context, OkHttpClient client) {
        return new Picasso.Builder(context)
            .downloader(new OkHttp3Downloader(client))
            .build();
    }

    static OkHttpClient createOkHttpClient(Context context) {
        File cacheDir = new File(context.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, 1024 * 1024 * 50);
        return new OkHttpClient.Builder().cache(cache).build();
    }
}
