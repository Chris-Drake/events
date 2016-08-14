package nz.co.chrisdrake.events.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

@Module public class DebugDataModule {
    private final boolean useMockMode;

    public DebugDataModule(boolean useMockMode) {
        this.useMockMode = useMockMode;
    }

    @Provides @Singleton @IsMockMode boolean provideMockMode() {
        return useMockMode;
    }

    @Provides @Singleton SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides @Singleton Picasso providePicasso(Context context, OkHttpClient client) {
        return new Picasso.Builder(context) //
            .downloader(new OkHttp3Downloader(client)) //
            .build();
    }

    @Provides @Singleton OkHttpClient provideHttpClient(Context context) {
        HttpLoggingInterceptor loggingInterceptor =
            new HttpLoggingInterceptor(message -> Timber.tag("OkHttp").d(message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return DataModule.createOkHttpClient(context).newBuilder()
            .addInterceptor(loggingInterceptor)
            .build();
    }
}