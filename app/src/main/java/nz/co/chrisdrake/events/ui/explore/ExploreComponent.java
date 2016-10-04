package nz.co.chrisdrake.events.ui.explore;

import dagger.Component;
import nz.co.chrisdrake.events.ActivityScope;
import nz.co.chrisdrake.events.EventAppComponent;

@Component(dependencies = EventAppComponent.class, modules = ExploreModule.class) @ActivityScope
interface ExploreComponent {
    void inject(ExploreFragment fragment);
}
