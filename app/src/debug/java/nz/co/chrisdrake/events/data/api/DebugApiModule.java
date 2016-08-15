package nz.co.chrisdrake.events.data.api;

import com.squareup.moshi.Moshi;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import nz.co.chrisdrake.events.data.IsMockMode;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module public class DebugApiModule {

    @Provides @Singleton @Named("ApiClient") OkHttpClient provideHttpClient(OkHttpClient client,
        ApiAuthenticator authenticator) {
        return ApiModule.createApiClient(client, authenticator);
    }

    @Provides @Singleton Moshi provideMoshi() {
        return ApiModule.createMoshi();
    }

    @Provides @Singleton Retrofit provideRetrofit(@Named("ApiClient") OkHttpClient client,
        Moshi moshi) {
        return ApiModule.createRetrofit(client, moshi);
    }

    @Provides @Singleton EventFinderService provideApi(Retrofit retrofit,
        @IsMockMode boolean isMockMode) {
        return isMockMode ? new MockEventFinderService()
            : retrofit.create(EventFinderService.class);
    }
}
