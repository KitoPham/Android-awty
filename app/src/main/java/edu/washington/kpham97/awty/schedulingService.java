package edu.washington.kpham97.awty;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Kito Pham on 2/22/2017.
 */

public class schedulingService extends IntentService {

    Handler mHandler;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     *  Used to name the worker thread, important only for debugging.
     */
    public schedulingService() {
        super("Scheduling Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.i("Operations:", "Text Sent");
                SmsManager.getDefault().sendTextMessage(MyApplication.phone,null,MyApplication.alarmText, null, null);
            }
        });
        alarmReceiver.completeWakefulIntent(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
    }


}
