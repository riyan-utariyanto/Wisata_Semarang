package com.riyan.wisatasemarang.activities;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.riyan.wisatasemarang.R;
import com.riyan.wisatasemarang.helper.ServiceClient;
import com.riyan.wisatasemarang.helper.ServiceGenerator;
import com.riyan.wisatasemarang.model.ListWisata;
import com.riyan.wisatasemarang.model.Wisata;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LokasiAll extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi_all);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    List<Wisata> listWisata = new ArrayList<>();
    ProgressDialog pd;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        ServiceClient service = ServiceGenerator.createService(ServiceClient.class);
        Call<ListWisata> getListWisata = service.getWisata("semarang");

        pd = new ProgressDialog(LokasiAll.this);
        pd.setMessage("Please wait . . .");
        pd.show();

        getListWisata.enqueue(new Callback<ListWisata>() {
            @Override
            public void onResponse(Call<ListWisata> call, Response<ListWisata> response) {
                pd.dismiss();
                listWisata = response.body().getListWisataSemarang();

                for (int i = 0; i < listWisata.size(); i++) {
                    Double latWisata = Double.valueOf(listWisata.get(i).getLatitudeWisata());
                    Double longWisata = Double.valueOf(listWisata.get(i).getLongitudeWisata());

                    LatLng lokasiWisata = new LatLng(latWisata, longWisata);
                    mMap.addMarker(new MarkerOptions().position(lokasiWisata).title(listWisata.get(i).getNamaWisata()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(lokasiWisata));
                }
            }

            @Override
            public void onFailure(Call<ListWisata> call, Throwable t) {
                Toast.makeText(LokasiAll.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
