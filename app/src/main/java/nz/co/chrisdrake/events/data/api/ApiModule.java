package nz.co.chrisdrake.events.data.api;

import com.squareup.moshi.Moshi;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module public class ApiModule {

    @Provides @Singleton @Named("ApiClient") OkHttpClient provideHttpClient(OkHttpClient client,
        ApiAuthenticator authenticator) {
        return createApiClient(client, authenticator);
    }

    @Provides @Singleton Moshi provideMoshi() {
        return createMoshi();
    }

    @Provides @Singleton Retrofit provideRetrofit(@Named("ApiClient") OkHttpClient client,
        Moshi moshi) {
        return createRetrofit(client, moshi);
    }

    @Provides @Singleton EventFinderService provideApi(Retrofit retrofit) {
        return retrofit.create(EventFinderService.class);
    }

    static Moshi createMoshi() {
        return new Moshi.Builder() //
            .add(new DateAdapter()) //
            .add(AdapterFactory.create()) //
            .build();
    }

    static OkHttpClient createApiClient(OkHttpClient client, ApiAuthenticator authenticator) {
        return client.newBuilder().authenticator(authenticator).build();
    }

    static Retrofit createRetrofit(OkHttpClient client, Moshi moshi) {
        return new Retrofit.Builder().baseUrl("http://api.eventfinda.co.nz/v2/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
    }
}
