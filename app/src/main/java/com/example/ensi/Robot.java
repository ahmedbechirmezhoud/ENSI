package com.example.ensi;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class Robot extends Activity implements SensorEventListener  {

    private Path path;
    Point currentPosition;
    private int width, height;
    private float Vright, Vleft;
    private float[] gravityAcceleration;
    private float[] linearAcceleration;
    private float omegaX = 0;

    /**
     * Get sensors Data and update Position
     */
    public Robot(Path predefinedPath ,int width, int height){
        this.path = predefinedPath;
        this.currentPosition = predefinedPath.predefinedPath.get(0);
        this.width = width;
        this.height = height;
    }

    /**
     * update Vright and send it to the Arduino
     * @param Vright Velocity of the right model
     */
    public void setVright(float Vright) {
        Vright = Vright;
    }

    /**
     * update Vleft and send it to the Arduino
     * @param Vleft Velocity of the right model
     */
    public void setVleft(float Vleft) {
        Vleft = Vleft;
    }


    private SensorManager mSensorManager;
    Sensor accelerometer;
    Sensor gravity;
    Sensor gyroscope;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gravity = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        gyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, gravity, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_UI);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


    private static final float NS2S = 1.0f / 1000000000.0f;
    private final float[] deltaRotationVector = new float[4];
    private float timestamp = 0;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (gyroscope.equals(sensorEvent.sensor)) {

            if (timestamp != 0) {
                final float dT = (sensorEvent.timestamp - timestamp) * NS2S;

                // Integrate Angular Velocity to get Angle
                omegaX += sensorEvent.values[0] * dT;   // on X axis

            }
            timestamp = sensorEvent.timestamp;



        } else if (accelerometer.equals(sensorEvent.sensor)) {

            if (timestamp != 0) {
                final float dT = (sensorEvent.timestamp - timestamp) * NS2S;

                //Get linear acceleration (X,Y,Z) relative to the phone
                //subtract Gravity acceleration (X,Y,Z) relative to the phone
                linearAcceleration[0] = sensorEvent.values[0] - gravityAcceleration[0]; //X
                linearAcceleration[1] = sensorEvent.values[1] - gravityAcceleration[1]; //Y
                linearAcceleration[2] = sensorEvent.values[2] - gravityAcceleration[2]; //Z

                //Integrate linear acceleration (Y,-Z)
                //Feed integration result to the Robot Position X, Y
                currentPosition.setPosition(
                        currentPosition.getX() + linearAcceleration[1] * dT * dT,
                        currentPosition.getY() - linearAcceleration[2] * dT * dT);


            }
            timestamp = sensorEvent.timestamp;


        } else if (gravity.equals(sensorEvent.sensor)) {

            gravityAcceleration[0] = sensorEvent.values[0];
            gravityAcceleration[1] = sensorEvent.values[1];
            gravityAcceleration[2] = sensorEvent.values[2];
            linearAcceleration[0] -= sensorEvent.values[0];
            linearAcceleration[1] -= sensorEvent.values[1];
            linearAcceleration[2] -= sensorEvent.values[2];

        } else {
            throw new IllegalStateException("Unexpected value: " + sensorEvent.sensor);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

