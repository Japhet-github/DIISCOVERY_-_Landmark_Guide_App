package com.landmarkguideapp.diiscovery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.Style;

public class Mapbox_Activity extends AppCompatActivity
{
    FloatingActionButton fabEdit;
    ExtendedFloatingActionButton fabMain;

    TextView tvFabMenu, fabEdit_text;
    Boolean isAllFabsVisible;

    // private long backPressedTime;
    // private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get mapbox instance - always before setContentView
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

        setContentView(R.layout.activity_mapbox);

        // mapView
        MapView mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(mapboxMap ->
                mapboxMap.setStyle(Style.MAPBOX_STREETS, style -> {

            // map setups will be here

        }));

        //ShowMenu();

        fabMain = findViewById(R.id.fab_MAIN);

        // button
        fabEdit = findViewById(R.id.fab_edit);

        // textView
        fabEdit_text = findViewById(R.id.textView_fabEdit);

        // visibility
        fabEdit.setVisibility(View.GONE);
        fabEdit_text.setVisibility(View.GONE);

        isAllFabsVisible = false;

        fabMain.shrink();
        fabMain.setOnClickListener(v -> {
            if (!isAllFabsVisible) {
                fabEdit.setVisibility(View.VISIBLE);
                fabEdit_text.setVisibility(View.VISIBLE);

                fabMain.extend();

                isAllFabsVisible = true;
            } else {
                fabEdit.setVisibility(View.GONE);
                fabEdit_text.setVisibility(View.GONE);

                fabMain.shrink();

                isAllFabsVisible = false;
            }
        });

        // click on fab edit
        fabEdit.setOnClickListener(v -> {
            Intent intent = new Intent(Mapbox_Activity.this, EditProfile_Activity.class);
            startActivity(intent);
        });

    }

    // back press button to exit the app
    int counter = 0;
    @Override
    public void onBackPressed() {
        // time is in milliseconds
        counter++;
        if(counter == 1){
            Toast.makeText(this, "Press Back again to exit the application.", Toast.LENGTH_SHORT).show();
        }else if(counter == 2){
            super.onBackPressed();
            finishAffinity();
        }
    }
}