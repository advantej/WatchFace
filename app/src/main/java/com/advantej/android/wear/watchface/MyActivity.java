package com.advantej.android.wear.watchface;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.view.WatchViewStub;
import android.text.format.DateUtils;
import android.widget.TextView;

import java.util.Calendar;

public class MyActivity extends Activity {

    private TextView mTextViewTime;
    private TextView mTextViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextViewTime = (TextView) stub.findViewById(R.id.time);
                mTextViewDate = (TextView) stub.findViewById(R.id.date);

                mHandler.post(new TimeSetter());
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private Handler mHandler = new Handler();

    private class TimeSetter implements Runnable {
        @Override
        public void run() {

            Calendar calendar = Calendar.getInstance();
            long time = calendar.getTimeInMillis();

            String timeStr = DateUtils.formatDateTime(MyActivity.this, time, DateUtils.FORMAT_SHOW_TIME);
            String dateStr = DateUtils.formatDateTime(MyActivity.this, time, DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_WEEKDAY);

            mTextViewTime.setText(timeStr);
            mTextViewDate.setText(dateStr);

            mHandler.postDelayed(new TimeSetter(), 1000);
        }
    }
}
