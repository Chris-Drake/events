package nz.co.chrisdrake.events.ui;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import nz.co.chrisdrake.events.R;
import nz.co.chrisdrake.events.data.api.model.Event;
import nz.co.chrisdrake.events.ui.explore.ExploreFragment;
import nz.co.chrisdrake.events.util.CustomTabActivityHelper;

public class MainActivity extends AppCompatActivity
    implements ExploreFragment.ExploreEventListener {
    public static final int FRAGMENT_CONTAINER_ID = R.id.container;

    private final CustomTabActivityHelper customTabActivityHelper = new CustomTabActivityHelper();

    @Override public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            addFragment(new ExploreFragment());
        }
    }

    @Override protected void onStart() {
        super.onStart();
        customTabActivityHelper.bindCustomTabsService(this);
    }

    @Override protected void onStop() {
        super.onStop();
        customTabActivityHelper.unbindCustomTabsService(this);
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
            .add(FRAGMENT_CONTAINER_ID, fragment)
            .commit();
    }

    @Override public void onEventClicked(Event event) {
        Uri uri = Uri.parse(event.url());
        openCustomTab(uri, event.name());
    }

    @Override public void onAttributionClicked() {
        Uri uri = Uri.parse("http://www.eventfinda.co.nz");
        openBrowser(uri);
    }

    private void openCustomTab(Uri uri, String shareTitle) {
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
            .setType("text/plain")
            .setSubject(shareTitle)
            .setText(uri.toString())
            .getIntent();
        PendingIntent sharePendingIntent = PendingIntent.getActivity(this, 0, shareIntent,
            PendingIntent.FLAG_UPDATE_CURRENT);

        CustomTabsIntent customTabsIntent =
            new CustomTabsIntent.Builder(customTabActivityHelper.getSession()) //
                .setToolbarColor(ContextCompat.getColor(this, R.color.theme_primary))
                .setShowTitle(true)
                .setStartAnimations(this, R.anim.slide_in_right, R.anim.slide_out_left)
                .setExitAnimations(this, R.anim.slide_in_left, R.anim.slide_out_right)
                .addMenuItem(getString(R.string.event_share), sharePendingIntent)
                .build();

        CustomTabActivityHelper.openCustomTab(this, customTabsIntent, uri,
            new CustomTabActivityHelper.CustomTabFallback() {
                @Override public void openUri(Activity activity, Uri uri) {
                    openBrowser(uri);
                }
            });
    }

    private void openBrowser(Uri uri) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(browserIntent);
    }
}
