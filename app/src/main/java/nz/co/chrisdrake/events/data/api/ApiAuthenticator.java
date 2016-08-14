package nz.co.chrisdrake.events.data.api;

import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import nz.co.chrisdrake.events.BuildConfig;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

@Singleton public class ApiAuthenticator implements Authenticator {

    @Inject ApiAuthenticator() {

    }

    @Override public Request authenticate(Route route, Response response) throws IOException {
        String credential = Credentials.basic(BuildConfig.EVENTFINDER_API_USERNAME,
            BuildConfig.EVENTFINDER_API_PASSWORD);
        return response.request().newBuilder().header("Authorization", credential).build();
    }
}
