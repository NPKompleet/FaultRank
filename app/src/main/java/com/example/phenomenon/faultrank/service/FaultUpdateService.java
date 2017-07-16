package com.example.phenomenon.faultrank.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.phenomenon.faultrank.provider.FaultProvider;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class FaultUpdateService extends IntentService {
    private static final String TAG = FaultUpdateService.class.getSimpleName();
    //Intent actions
    public static final String ACTION_INSERT = TAG + ".INSERT";
    public static final String ACTION_UPDATE = TAG + ".UPDATE";
    public static final String ACTION_DELETE = TAG + ".DELETE";

    public static final String EXTRA_VALUES = TAG + ".ContentValues";

    public static void insertNewFault(Context context, ContentValues values) {
        Intent intent = new Intent(context, FaultUpdateService.class);
        intent.setAction(ACTION_INSERT);
        intent.putExtra(EXTRA_VALUES, values);
        context.startService(intent);
    }

    public static void updateFault(Context context, Uri uri, ContentValues values) {
        Intent intent = new Intent(context, FaultUpdateService.class);
        intent.setAction(ACTION_UPDATE);
        intent.setData(uri);
        intent.putExtra(EXTRA_VALUES, values);
        context.startService(intent);
    }

    public static void deleteFault(Context context, Uri uri) {
        Intent intent = new Intent(context, FaultUpdateService.class);
        intent.setAction(ACTION_DELETE);
        intent.setData(uri);
        context.startService(intent);
    }




    public FaultUpdateService() {
        super(TAG);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (ACTION_INSERT.equals(intent.getAction())) {
            ContentValues values = intent.getParcelableExtra(EXTRA_VALUES);
            performInsert(values);
        } else if (ACTION_UPDATE.equals(intent.getAction())) {
            ContentValues values = intent.getParcelableExtra(EXTRA_VALUES);
            performUpdate(intent.getData(), values);
        } else if (ACTION_DELETE.equals(intent.getAction())) {
            performDelete(intent.getData());
        }

    }


    private void performInsert(ContentValues values) {
        if (getContentResolver().insert(FaultProvider.Faults.CONTENT_URI, values) != null) {
            Log.d(TAG, "Inserted new task");
        } else {
            Log.w(TAG, "Error inserting new task");
        }
    }

    private void performUpdate(Uri uri, ContentValues values) {
        int count = getContentResolver().update(uri, values, null, null);
        Log.d(TAG, "Updated " + count + " task items");
    }

    private void performDelete(Uri uri) {
        int count = getContentResolver().delete(uri, null, null);

        //Cancel any reminders that might be set for this item
        /*PendingIntent operation =
                ReminderAlarmService.getReminderPendingIntent(this, uri);
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        manager.cancel(operation);*/

        Log.d(TAG, "Deleted "+count+" tasks");
    }


   }
