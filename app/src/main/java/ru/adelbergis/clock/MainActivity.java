package ru.adelbergis.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static final int TICK_DELAY_MILLIS = 250;
    // 16.11.2015 13:24:33
    private SimpleDateFormat mTimeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.US);
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private TextView mTimeTextView;
    private Runnable mTickRoutine = new Runnable() {
        @Override
        public void run() {
            mTimeTextView.setText(mTimeFormat.format(Calendar.getInstance().getTime()));//форматируем и помещаем время
            mHandler.postDelayed(this, TICK_DELAY_MILLIS);//так мы говорим чтобы обновление произошло еще раз
            //через определенный кусок времени
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimeTextView = findViewById(R.id.view_time);
        mTickRoutine.run();//запускаем отслеживание
    }

    @Override
    protected void onDestroy() {
        //метод запускается при уничтожений экрана
        super.onDestroy();
        mHandler.removeCallbacks(mTickRoutine);//уничтажаем отслеживание
        mTimeTextView = null;
    }
}