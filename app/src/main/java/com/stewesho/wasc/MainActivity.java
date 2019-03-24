package com.stewesho.wasc;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Powered By Dark Sky
        Spanned powered_by;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            powered_by = Html.fromHtml(getString(R.string.powered_by), Html.FROM_HTML_MODE_LEGACY);
        else
            powered_by = Html.fromHtml(getString(R.string.powered_by));
        TextView textPoweredBy = (TextView) findViewById(R.id.textPoweredBy);
        textPoweredBy.setText(powered_by);
        textPoweredBy.setMovementMethod(LinkMovementMethod.getInstance());

        //Get data
        FindLocation();
    }

    public void FindLocation(){
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.GPS_PROVIDER;

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                getForecast(location);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Toast toast_no_location = Toast.makeText(getApplicationContext(), "No location could be found.", Toast.LENGTH_LONG);
                toast_no_location.show();
            }
        };

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_CALENDAR)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        10);
            }

            Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
//            Check for a new location every 5 minutes
//            if (lastKnownLocation == null )
                locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);
//            else
//                getForecast(lastKnownLocation);

        } catch (SecurityException e){
            Toast toast_no_location = Toast.makeText(getApplicationContext(), "No location could be found.", Toast.LENGTH_LONG);
            toast_no_location.show();
        } catch (NullPointerException e){
            finish();
            System.exit(1);
        }
    }

    public void getForecast(Location location){
        double lat = location.getLatitude();
        double lng = location.getLongitude();

        Intent gotoShowClothes = new Intent(this, ShowClothes.class);
        gotoShowClothes.putExtra("latitude", lat);
        gotoShowClothes.putExtra("longitude", lng);
        startActivity(gotoShowClothes);
    }
}
