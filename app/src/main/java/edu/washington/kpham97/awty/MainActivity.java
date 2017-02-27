package edu.washington.kpham97.awty;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    alarmReceiver alarm = new alarmReceiver();
    Button button;
    boolean start;
    static EditText message;
    static EditText phone;
    static EditText interval;
    int MY_PERMISSIONS_REQUEST_SEND_MMS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = true;

        message = (EditText) findViewById(R.id.message);
        phone = (EditText) findViewById(R.id.phonenumber);
        phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        interval = (EditText) findViewById(R.id.interval);

        button = (Button) findViewById(R.id.startstop);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int intNum = 0;
        try
        {
            intNum = Integer.parseInt(interval.getText().toString());
        }
        catch (NumberFormatException e)
        {
            // handle the exception
        }
        if (message.getText().toString().length() > 0 &
                phone.getText().toString().length() > 0 &
                interval.getText().toString().length() > 0 & intNum > 0){
            if (start) {
                alarm.setAlarm(this);
                button.setText("Stop");
                MyApplication.alarmText = phone.getText().toString() + ": " + message.getText().toString();
                MyApplication.phone = phone.getText().toString();
                MyApplication.intervalTime = intNum * 60 * 1000;
                Log.i("Operation:", "Variable display" + MyApplication.alarmText + " " + MyApplication.intervalTime);
            } else {
                alarm.cancelAlarm(this);
                button.setText("Start");
            }
            start = !start;
        } else {
            if(message.getText().toString().length()<=0){
                Toast.makeText(getApplicationContext(), "Error Invalid Message: Please input message text", Toast.LENGTH_SHORT).show();
            } else if (!PhoneNumberUtils.isGlobalPhoneNumber(phone.getText().toString())){
                Toast.makeText(getApplicationContext(), "Error Invalid Phone: Please input phone number", Toast.LENGTH_SHORT).show();
            } else if (interval.getText().toString().length() <= 0){
                Toast.makeText(getApplicationContext(), "Error Invalid Interval: Please input interval time", Toast.LENGTH_SHORT).show();
            } else if (intNum <= 0){
                Toast.makeText(getApplicationContext(), "Error Invalid Interval: Please input interval time greater than 0", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
