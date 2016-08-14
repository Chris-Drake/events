package nz.co.chrisdrake.events.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module public class ApiModule {

    @Provides @Singleton @Named("ApiClient") OkHttpClient provideHttpClient(OkHttpClient client,
        ApiAuthenticator authenticator) {
        return createApiClient(client, authenticator);
    }

    @Provides @Singleton Gson provideGson() {
        return createGson();
    }

    @Provides @Singleton Retrofit provideRetrofit(@Named("ApiClient") OkHttpClient client,
        Gson gson) {
        return createRetrofit(client, gson);
    }

    @Provides @Singleton EventFinderService provideApi(Retrofit retrofit) {
        return retrofit.create(EventFinderService.class);
    }

    static Gson createGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    static OkHttpClient createApiClient(OkHttpClient client, ApiAuthenticator authenticator) {
        return client.newBuilder().authenticator(authenticator).build();
    }

    static Retrofit createRetrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder().baseUrl("http://api.eventfinda.co.nz/v2/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
    }
}
