package com.example.beacon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

// created 02.12.2016. Petro.

public class AddBeaconsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beacons);
        Toast.makeText(this, "onCreate() AddBeaconsActivity", Toast.LENGTH_SHORT).show();
    }

    protected void onResume(){
        super.onResume();
        Toast.makeText(this, "onResume() AddBeaconsActivity", Toast.LENGTH_SHORT).show();
    }

    private void createTable() {
        // to be implemented
    }

    public void addBeaconsByButtonClick(View v) {
        Toast.makeText(this, "Add beacons button pressed", Toast.LENGTH_SHORT).show();
        //to be implemented
    }

    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(this, "onResume() AddBeaconsActivity", Toast.LENGTH_SHORT).show();
    }

}
