package cic.ipn.mx.mapsexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment =
                (SupportMapFragment) this.getSupportFragmentManager()
                        .findFragmentById(R.id.mfMap);

        mapFragment.getMapAsync(this);

        Button btnNormal = findViewById(R.id.btnNormal);
        Button btnHybrid = findViewById(R.id.btnHybrid);
        Button btnSatellite = findViewById(R.id.btnSatellite);
        Button btnTerrain = findViewById(R.id.btnTerrain);
        Button btnNone = findViewById(R.id.btnNone);

        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });
        btnHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
            }
        });
        btnSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            }
        });
        btnTerrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                }
            }
        });
        btnNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_NONE);
                }
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.map = googleMap;

        this.showMyUserLocation(true);

        LatLng zocalo = new LatLng(19.4326018, -99.1332049);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(zocalo);
        markerOptions.title("Zócalo CDMX");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.my_pin));

        this.map.addMarker(markerOptions);

        this.map.moveCamera(CameraUpdateFactory.newLatLng(zocalo));

    }

    private void showMyUserLocation(boolean enabled) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

            ActivityCompat.requestPermissions(this, permissions, 999);

        } else {

            this.map.setMyLocationEnabled(enabled);

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 999) {

            boolean granted = true;

            for (int permission : grantResults) {
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    granted = false;
                    break;
                }
            }

            if (granted) {
                this.showMyUserLocation(true);
            }

        }

    }
}
