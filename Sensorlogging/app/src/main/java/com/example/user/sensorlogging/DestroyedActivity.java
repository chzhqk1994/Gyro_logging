package com.example.user.sensorlogging;

        import android.content.Context;
        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.MotionEvent;
        import android.view.View;
        import android.widget.Button;

public class DestroyedActivity extends AppCompatActivity {

    private int i = 0;

    //Using the Accelometer & Gyroscoper
    private SensorManager mSensorManager = null;

    //Using the Accelometer
    private SensorEventListener mAccLis;
    private Sensor mAccelometerSensor = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.destroy_menu);



        //Using the Gyroscope & Accelometer
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //Using the Accelometer
        mAccelometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mAccLis = new AccelometerListener();



        // Start 버튼을 누르면 로깅 시작, Stop 버튼을 누르면 중단
        Button start = (Button) findViewById(R.id.a_start);
        Button stop = (Button) findViewById(R.id.a_stop);

        View.OnClickListener Clickname = new View.OnClickListener() {

            public void onClick(View v) {
                switch(v.getId()){

                    case R.id.a_start :
                        mSensorManager.registerListener(mAccLis, mAccelometerSensor, SensorManager.SENSOR_DELAY_UI);
                        break;

                    case R.id.a_stop:
                        onPause();
                        mSensorManager.unregisterListener(mAccLis);
                        break;
                };
            }

        };
        start.setOnClickListener(Clickname);
        stop.setOnClickListener(Clickname);
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.e("LOG", "onPause()");
        mSensorManager.unregisterListener(mAccLis);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.e("LOG", "onDestroy()");
        mSensorManager.unregisterListener(mAccLis);
    }

    private class AccelometerListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {

            double accX = event.values[0];
            double accY = event.values[1];
            double accZ = event.values[2];

            double angleXZ = Math.atan2(accX,  accZ) * 180/Math.PI;
            double angleYZ = Math.atan2(accY,  accZ) * 180/Math.PI;

            Log.d("Destroyed Road " + i++, "ACCELOMETER           [X]:" + String.format("%.9f", event.values[0])
                    + "           [Y]:" + String.format("%.9f", event.values[1])
                    + "           [Z]:" + String.format("%.9f", event.values[2])
                    + "           [angleXZ]: " + String.format("%.9f", angleXZ)
                    + "           [angleYZ]: " + String.format("%.9f", angleYZ));

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

}
