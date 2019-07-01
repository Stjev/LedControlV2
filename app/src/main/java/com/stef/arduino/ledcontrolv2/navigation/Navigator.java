package com.stef.arduino.ledcontrolv2.navigation;

import android.app.Activity;

import androidx.navigation.Navigation;

import com.stef.arduino.ledcontrolv2.enums.LedMode;
import com.stef.arduino.ledcontrolv2.R;

public class Navigator {
    public static void goColorCycling(Activity activity) {
        Navigation.findNavController(activity, R.id.navigation_fragment)
                .navigate(R.id.action_global_colorCyclingFragment);
    }

    public static void goSoundReactive(Activity activity) {
        Navigation.findNavController(activity, R.id.navigation_fragment)
                .navigate(R.id.action_global_soundReactiveFragment);
    }

    public static void goStar(Activity activity) {
        Navigation.findNavController(activity, R.id.navigation_fragment)
                .navigate(R.id.action_global_starFragment);
    }

    public static void goStatic(Activity activity) {
        Navigation.findNavController(activity, R.id.navigation_fragment)
                .navigate(R.id.action_global_staticFragment);
    }

    /**
     * Given a ledmode this will navigate to the right options
     * @param ledMode the selected ledmode
     * @param activity the current activity
     */
    public static void navigateToOption(LedMode ledMode, Activity activity) {
        switch (ledMode) {
            case STARS:
                goStar(activity);
                break;
            case COLOR_CYCLE:
                goColorCycling(activity);
                break;
            case STATIC_COLOR:
                goStatic(activity);
                break;
            case SOUND_REACTIVE:
                goSoundReactive(activity);
                break;
            default:
                throw new UnsupportedOperationException("This mode is not yet implemented");
        }
    }
}
