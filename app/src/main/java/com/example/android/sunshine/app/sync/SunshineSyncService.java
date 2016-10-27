package com.example.android.sunshine.app.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;

public class SunshineSyncService extends Service {
    private static final Object sSyncAdapterLock = new Object();
    private static SunshineSyncAdapter sSunshineSyncAdapter = null;
    //private static final String LOG_TAG= "SunShineSyncService";
    //private static final String DATA_ITEM_RECEIVED_PATH = "/weather";

    @Override
    public void onCreate() {
        Log.d("SunshineSyncService", "onCreate - SunshineSyncService");
        synchronized (sSyncAdapterLock) {
            if (sSunshineSyncAdapter == null) {
                sSunshineSyncAdapter = new SunshineSyncAdapter(getApplicationContext(), true);
            }
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return sSunshineSyncAdapter.getSyncAdapterBinder();
    }


    /*@Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        if (Log.isLoggable(LOG_TAG, Log.DEBUG)) {
            Log.d(LOG_TAG, "onDataChanged: " + dataEvents);
        }
        /*
        final List events = FreezableUtils
                .freezeIterable(dataEvents);

        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .build();

        ConnectionResult connectionResult =
                googleApiClient.blockingConnect(30, TimeUnit.SECONDS);

        if (!connectionResult.isSuccess()) {
            Log.e(LOG_TAG, "Failed to connect to GoogleApiClient.");
            return;
        }


        for (DataEvent event : dataEvents) {
            if(event.getType()==DataEvent.TYPE_CHANGED){
                String path = event.getDataItem().getUri().getPath();
                if(path.equals(DATA_ITEM_RECEIVED_PATH ))
                    SunshineSyncAdapter.syncImmediately(this);

            }

        }
    }
    */
}