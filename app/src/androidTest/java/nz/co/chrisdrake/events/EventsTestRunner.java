package nz.co.chrisdrake.events;

import android.os.Bundle;
import android.support.test.runner.AndroidJUnitRunner;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;

import static android.view.WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD;
import static android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
import static android.view.WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;

public final class EventsTestRunner extends AndroidJUnitRunner {

    @Override public void onCreate(Bundle arguments) {
        super.onCreate(arguments);

        ActivityLifecycleMonitorRegistry.getInstance()
            .addLifecycleCallback((activity, stage) -> {
                if (stage == Stage.PRE_ON_CREATE) {
                    activity.getWindow().addFlags(
                            FLAG_DISMISS_KEYGUARD | FLAG_TURN_SCREEN_ON | FLAG_KEEP_SCREEN_ON);
                }
            });
    }
}