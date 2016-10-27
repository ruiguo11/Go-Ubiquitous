package com.example.android.sunshine.app.wearable;

import android.net.Uri;
import android.util.Log;

import com.example.android.sunshine.app.sync.SunshineSyncAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.data.FreezableUtils;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.Wearable;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ruiguo on 22/10/16.
 */


public class WearableListenerService extends com.google.android.gms.wearable.WearableListenerService {
    private static final String LOG_TAG= "WearableListenerService";
    //private static final String START_ACTIVITY_PATH = "/weather";
    private static final String DATA_ITEM_RECEIVED_PATH = "/weather";
    private SunshineSyncAdapter sSunshineSyncAdapter;


    @Override
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
*/

        for (DataEvent event : dataEvents) {
            if(event.getType()==DataEvent.TYPE_CHANGED){
                String path = event.getDataItem().getUri().getPath();
                if(path.equals(DATA_ITEM_RECEIVED_PATH ))
                    SunshineSyncAdapter.syncImmediately(this);

            }

        }
    }
}
