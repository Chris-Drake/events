package nz.co.chrisdrake.events;

import android.content.Context;
import android.content.SharedPreferences;
import com.squareup.picasso.Picasso;
import dagger.Component;
import javax.inject.Singleton;
import nz.co.chrisdrake.events.data.DataModule;
import nz.co.chrisdrake.events.data.api.ApiModule;
import nz.co.chrisdrake.events.data.api.EventFinderService;
import nz.co.chrisdrake.events.data.realm.RealmHelper;
import nz.co.chrisdrake.events.data.realm.RealmModule;

@Singleton @Component(
    modules = { EventAppModule.class, DataModule.class, ApiModule.class, RealmModule.class })
public interface EventAppComponent {
    Context context();
    SharedPreferences sharedPreferences();
    EventFinderService service();
    Picasso picasso();
    RealmHelper realmHelper();
}
