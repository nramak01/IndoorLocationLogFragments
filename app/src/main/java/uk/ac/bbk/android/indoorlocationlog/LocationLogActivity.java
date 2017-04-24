package uk.ac.bbk.android.indoorlocationlog;

import android.support.v4.app.Fragment;

/**
 * Base Activity class which uses Fragment
 */
public class LocationLogActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return LocationLogFragment.newInstance();
    }
}
