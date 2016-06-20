package com.example.downa.przypominajka;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(Cords.finger.getPion(), Cords.finger.getPoziom());
        mMap.addMarker(new MarkerOptions().position(sydney).title(Cords.finger.getName()));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        pointToPosition(sydney);
    }

    private void pointToPosition(LatLng position) {
        //Build camera position
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(position)
                .zoom(17).build();
        //Zoom in and animate the camera.
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
