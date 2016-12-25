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
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ServiceImpl extends IntentService {

    private BufferedReader reader;
    private ArrayList<Beacon> scanned_beacons = new ArrayList<>();
    public static int NOTIFICATION_ID = 1; //Andriy. 02.12.2016

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
        long seconds = intent.getExtras().getLong("seconds");
        //how long the service should sleep, in milliseconds
        long millis = seconds * 1000;

        while (true) {
            try {
                Beacon beacon = scanBeacon();
                if(beacon != null){
                    //TODO: add beacons to the List of scanned beacons
                    // Done. Petro.
                    scanned_beacons.add(beacon);
                    //TODO: notification
                    //TODO: intent to AddBeaconsActivity
                    //Done. Andriy 02.12.2016, Petro 25.12.2016 (stackBuilder)

                    /*
                    Intent intent1 = new Intent(this, AddBeaconsActivity.class);
                    intent1.putParcelableArrayListExtra("Beacon Array", scanned_beacons);
                    PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
                    Notification notification = new Notification.Builder(this)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("New Beacon found")
                            .setContentIntent(pIntent)
                            .setAutoCancel(true)
                            .setPriority(Notification.PRIORITY_MAX)
                            .setDefaults(Notification.DEFAULT_VIBRATE)
                            .setContentText(beacon.toString())
                            .build();
                    */

                    NotificationCompat.Builder notification = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("New Beacon found")
                            .setAutoCancel(true)
                            .setPriority(Notification.PRIORITY_MAX)
                            .setDefaults(Notification.DEFAULT_VIBRATE)
                            .setContentText(beacon.toString());
                    Intent intent1 = new Intent(this, AddBeaconsActivity.class);
                    intent1.putParcelableArrayListExtra("Beacon Array", scanned_beacons);
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                    stackBuilder.addParentStack(AddBeaconsActivity.class).addNextIntent(intent1);
                    PendingIntent pIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                    notification.setContentIntent(pIntent);
                    NotificationManager notificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFICATION_ID, notification.build());
                }
                else {
                    break;
                }

                //TODO: put the service to sleep
                // Done Petro. 30.11.16
                Thread.sleep(millis);
            }
            catch (InterruptedException iEx){
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
        Log.v("ServiceImpl", "setupInputReader executed");
    }

    private Beacon scanBeacon() {
        //TODO: Read a line and split one row into the beacon components uuid, major and minor
        // Done. Petro 29.12.16
        //create a new beacon and return it
        Log.v("ServiceImpl", "scanBeacon() called");
        String line;
        Beacon scanned_beacon = null;
        try {
            line = reader.readLine();
            if(!line.isEmpty()) {
                String[] beacon_params = line.split(MainActivity.COMMA_DELIMITER);
                String uuid = beacon_params[0];
                int major = Integer.parseInt(beacon_params[1]);
                int minor = Integer.parseInt(beacon_params[2]);
                scanned_beacon = new Beacon(uuid, major, minor);
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return scanned_beacon;
    }

    public void onDestroy() {
        //TODO: implement this // Done. Petro
        super.onDestroy();
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("ServiceImpl", "onDestroy");
    }
}