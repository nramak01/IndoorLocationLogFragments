package uk.ac.bbk.android.indoorlocationlog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;

/**
 * This fragment class hooks up the layout
 * and pulls out the ImageView
 * Created by Narayanan on 23/04/2017.
 */

public class LocationLogFragment extends Fragment {

    private static final String TAG = "LocationLogFragment";

    private ImageView mImageView;
    protected IALocationManager mIALocationManager;
    private IALocationListener mIALocationListener = new IALocationListener() {

        // Called when the location has changed.
        @Override
        public void onLocationChanged(IALocation location) {

            Log.i(TAG, "Latitude: " + location.getLatitude());
            Log.i(TAG, "Longitude: " + location.getLongitude());
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }
    };
    public static LocationLogFragment newInstance() {
        return new LocationLogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mIALocationManager = IALocationManager.create(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Menu item pulled into own reference instance variable so that it can be enabled later
        View v = inflater.inflate(R.layout.fragment_location_log, container, false);
        mImageView = (ImageView) v.findViewById(R.id.image);
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIALocationManager.destroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mIALocationManager.requestLocationUpdates(IALocationRequest.create(), mIALocationListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        mIALocationManager.removeLocationUpdates(mIALocationListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        mIALocationManager.requestLocationUpdates(IALocationRequest.create(), mIALocationListener);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_location_log, menu);
        MenuItem searchItem = menu.findItem(R.id.action_locate);
        searchItem.setEnabled(true);
    }

}
