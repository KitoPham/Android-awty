package edu.washington.kpham97.awty;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by Kito Pham on 2/21/2017.
 */

public class alarmReceiver  extends WakefulBroadcastReceiver{


    private AlarmManager manager;
    private PendingIntent alarmIntent;
    private  int intervalNum;

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent service = new Intent(context, schedulingService.class);
        startWakefulService(context,service);
    }

    public void setAlarm(Context context){
        manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, alarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context,0,intent,0);

        manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,MyApplication.intervalTime, MyApplication.intervalTime, alarmIntent);

    }

    public void cancelAlarm(Context context){
        if(manager != null){
            manager.cancel(alarmIntent);
        }

    }
}
