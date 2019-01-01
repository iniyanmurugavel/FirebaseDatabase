package com.user.firebasedatabase.UserModule.Weather;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.user.firebasedatabase.Adapter.Weather.ViewPagerAdapter;
import com.user.firebasedatabase.Common.Common;
import com.user.firebasedatabase.R;

public class MainActivity extends AppCompatActivity {

    private LocationRequest mLocationRequest;

    private long UPDATE_INTERVAL = 10 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */
    TabLayout tabLayout;
    ViewPager viewPager;
    private CoordinatorLayout coordinatorLayout;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.root_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Request Permission
//        Dexter.withActivity(this)
//                .withPermissions(
//                        Manifest.permission.ACCESS_COARSE_LOCATION,
//                        Manifest.permission.ACCESS_FINE_LOCATION
//                ).withListener(new MultiplePermissionsListener() {
//
//            @Override
//            public void onPermissionsChecked(MultiplePermissionsReport report) {
//                if (report.areAllPermissionsGranted()) {
//
//
//                }
//
//                /* ... */
//            }
//
//            @Override
//            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//
//
//                /* ... */
//
//
//                Snackbar.make(coordinatorLayout, "Permission Denied", Snackbar.LENGTH_SHORT).show();
//            }
//        }).check();

        viewPager = findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        buildLocationRequest();

        buildLocationCallback();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);


        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
        Toast.makeText(getApplicationContext(), "location Result", Toast.LENGTH_SHORT).show();




    }


    @Override
    protected void onStop() {
        super.onStop();
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);

    }

    private void buildLocationRequest() {

        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setSmallestDisplacement(10.0f);

    }

    private void buildLocationCallback() {


        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Toast.makeText(getApplicationContext(), "inside", Toast.LENGTH_SHORT).show();

                Log.e("MainActivity", locationResult.getLastLocation().getLatitude() + "/" + locationResult.getLastLocation().getLongitude());


                Common.current_location = locationResult.getLastLocation();

                viewPager = findViewById(R.id.view_pager);
                setupViewPager(viewPager);
                tabLayout = findViewById(R.id.tabs);
                tabLayout.setupWithViewPager(viewPager);

                Toast.makeText(getApplicationContext(), "location Result", Toast.LENGTH_SHORT).show();

                Log.e("MainActivity", locationResult.getLastLocation().getLatitude() + "/" + locationResult.getLastLocation().getLongitude());
                Toast.makeText(getApplicationContext(), "location latlng" + locationResult.getLastLocation().getLatitude(), Toast.LENGTH_SHORT).show();


            }
        };


    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(TodayWeatherFragment.getInstance(), "Today");
        adapter.addFragment(ForecastFragment.getInstance(), "5 DAYS");
        viewPager.setAdapter(adapter);
    }


}
