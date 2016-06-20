package com.example.downa.przypominajka;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements
        ConnectionCallbacks, OnConnectionFailedListener {

    protected GoogleApiClient mGoogleApiClient;

    protected static final String TAG = "MainActivity";

    protected Location mLastLocation;

    private AlertDialog.Builder dialogBuilder;

    public static Cords current;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        buildGoogleApiClient();
        Cords.initialize(0,0);
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        // Provides a simple way of getting a device's location and is well suited for
        // applications that do not require a fine-grained location and that do not need location
        // updates. Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            Cords.initialize(mLastLocation.getLatitude(),mLastLocation.getLongitude());
        } else {
            Toast.makeText(this, "No GPS signal", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.info){
            Dialog();
            return true;
        }
        if (id == R.id.exit){
            finish();
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }


    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    public void Dialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Information");
        dialogBuilder.setMessage("Projet made by Jakub Downarowicz");
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Closed Info", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog InfoDialog = dialogBuilder.create();
        InfoDialog.show();
    }

    public void listReminder(View v){
        startActivity(new Intent(this, ListReminders.class));
    }

    public void addReminder(View v){
        startActivity(new Intent(this, AddReminder.class));
    }

    public void locationReminder(View v){
        startActivity(new Intent(this, LocationReminder.class));
    }
}
