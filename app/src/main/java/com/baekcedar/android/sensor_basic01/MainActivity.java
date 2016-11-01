package com.baekcedar.android.sensor_basic01;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


/*
 -- 센서 동작 기본 흐름
    1. SensorManger 생성
    2. Sensor 객체 생성( 내가 사용할 Sensor Type 선택 )
    3. Sensor Listener 작성
    4. Listener 등록 및 Sensor 값 받기
    5. Listener 해제
        (배터리 사용량을 위해 반드시 해제)
    - 동작 속도
    FASTEST, GAME, UI, NORMAL

    - 정확도
    HIGH, MEDIUM, LOW

 */
public class MainActivity extends AppCompatActivity implements SensorEventListener {
    Sensor acc, light,stepCount;
    SensorManager maanager;
    TextView tvAcc, tvLight,tvStepCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAcc       = (TextView) findViewById(R.id.accValue);
        tvLight     = (TextView) findViewById(R.id.lightValue);
        tvStepCount = (TextView) findViewById(R.id.stepValue);
        maanager    = (SensorManager) getSystemService(SENSOR_SERVICE);
        acc         = maanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        light       = maanager.getDefaultSensor(Sensor.TYPE_LIGHT);
        stepCount   = maanager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this){
            float x = 0;
            float y = 0;
            float z = 0;

            switch (event.sensor.getType()){
                case Sensor.TYPE_ACCELEROMETER :
                    x = event.values[0];
                    y = event.values[1];
                    z = event.values[2];
                    tvAcc.setText("x= "+x+", y= "+y+", z= "+z);
                    break;
                case Sensor.TYPE_LIGHT :
                    x = event.values[0];
                    y = event.values[1];
                    z = event.values[2];
                    tvLight.setText("x= "+x+", y= "+y+", z= "+z);
                    break;
                case Sensor.TYPE_STEP_COUNTER :
                    x = event.values[0];
                    tvStepCount.setText("step : "+x);
                    break;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        maanager.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);
        maanager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        maanager.registerListener(this, stepCount, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        maanager.unregisterListener(this);
    }
}
