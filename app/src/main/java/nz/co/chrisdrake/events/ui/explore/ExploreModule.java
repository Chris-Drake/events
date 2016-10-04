package nz.co.chrisdrake.events.ui.explore;

import dagger.Module;
import dagger.Provides;
import nz.co.chrisdrake.events.ActivityScope;

@Module class ExploreModule {

    private final ExploreFilter exploreFilter;

    ExploreModule(ExploreFilter exploreFilter) {
        this.exploreFilter = exploreFilter;
    }

    @Provides @ActivityScope ExploreFilter provideExploreFilter() {
        return exploreFilter;
    }
}
