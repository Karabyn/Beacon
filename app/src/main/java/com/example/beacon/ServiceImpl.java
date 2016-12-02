package com.example.beacon;

/**
 * Created by petro on 26-Nov-16.
 */

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ServiceImpl extends IntentService {

    //TODO: include needed fields
    private long seconds;
    private BufferedReader reader;
    private ArrayList<Beacon> scanned_beacons = new ArrayList<>();
    public static  int NOTIFICATION_ID = 1; //Andriy. 02.12.2016

    public ServiceImpl() {
        super("ServiceImpl");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.v("ServiceImpl", "onHandleIntent running");

        //TODO: uncomment this, when implemented setupInputReader();
        //Done. Petro 30.11.2016
        setupInputReader();

        //TODO: get the seconds from intent
        // Done. Petro 29.11.16
        seconds = intent.getExtras().getLong("seconds");

        //how long the service should sleep, in milliseconds
        long millis = seconds * 1000;

        while (true) {
            try {
                Beacon beacon = scanBeacon();
                Log.v("ServiceImpl", "onHandleIntent while loop executing");

                if(beacon != null){
                    //TODO: add beacons to the List of scanned beacons
                    // Done. Petro.
                    scanned_beacons.add(beacon);

                    //TODO: notification
                    //Done. Andriy 02.12.2016
                    Intent intent1 = new Intent(this, AddBeaconsActivity.class);

                    PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent1, 0);
                    Notification notification = new Notification.Builder(this)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("New Beacon found")
                            .setContentIntent(pIntent)
                            .setAutoCancel(true)
                            .setPriority(Notification.PRIORITY_MAX)
                            .setDefaults(Notification.DEFAULT_VIBRATE)
                            .setContentText(beacon.toString())
                            .build();
                    NotificationManager notificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFICATION_ID, notification);

                    //TODO: intent to AddBeaconsActivity

                    //build intent to switch to activity on click
                    //TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

                    //adds the back stack for the Intent (not the intent itself)
                    //stackBuilder.addParentStack(AddBeaconsActivity.class);

                    //adds the intent that starts and puts the activity to the top of the stack
                    //TODO: uncomment this and insert the above created intent as input
                    //stackBuilder.addNextIntent();

                    //PendingIntent waits for an event
                    //PendingIntent scanResultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

                    //notificationBuilder.setContentIntent(scanResultPendingIntent);
                    //Notification notification = notificationBuilder.build();
                    //NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    //notificationManager.notify(1, notification);

                    //Toast.makeText(this, String.format(scanned_beacons.get(0).toString()), Toast.LENGTH_SHORT).show();
                }
                else {
                    break;
                }

                //TODO: put the service to sleep
                // Done Petro. 30.11.16
                Thread.sleep(millis);

            }  catch (InterruptedException iEx){
                Log.d("Message", iEx.getMessage());
            }
        }
    }



    private void setupInputReader() {

        //TODO: read the file "Beacon.txt"
        // Done. Petro 29.11.16
        //read the header in advance to exclude it from the output
        try {
            File file = new File(getFilesDir(), "Beacons.txt");
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, String.format("setupInputReader executed"), Toast.LENGTH_SHORT).show();

    }

    private Beacon scanBeacon() {
        //TODO: Read a line and split one row into the beacon components uuid, major and minor
        // Done. Petro 29.12.16
        //create a new beacon and return it
        Log.v("ServiceImpl", "scanBeacon() called");
        String line = "";
        Beacon scanned_beacon = null;
        try {
            line = reader.readLine();
            if(line != null) {
                String[] beacon_params = line.split(MainActivity.COMMA_DELIMITER);
                String uuid = beacon_params[0];
                int major = Integer.parseInt(beacon_params[1]);
                int minor = Integer.parseInt(beacon_params[2]);
                scanned_beacon = new Beacon(uuid, major, minor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
         catch (NullPointerException e){
            e.printStackTrace();
        }
        try {
            Log.v("ServiceImpl", scanned_beacon.toString()); // prints scanned beacons in logs.
        }
        catch (NullPointerException e) {
            Log.e("ServiceImpl", "beacon.toString() NullPointerException exception");
        }
        return scanned_beacon;
    }

    public void onDestroy() {
        //TODO: implement this
        super.onDestroy();
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Service onDestroy", Toast.LENGTH_SHORT).show();
    }

}