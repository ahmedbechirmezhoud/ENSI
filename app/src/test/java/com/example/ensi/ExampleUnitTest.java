package com.example.ensi;

import org.junit.Test;

import static org.junit.Assert.*;

import static java.lang.Math.abs;

import android.hardware.Sensor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void SpeedPID() throws InterruptedException {
        PID pid = new PID(0.1,0,0);
        //set any other PID configuration options here.

        int sensor = 0;
        int target = 50000;

        AtomicInteger error = new AtomicInteger(target - sensor);

        while(abs(error.get()) > 10){
            //get some sort of sensor value
            System.out.println("Sensor : " + sensor);
            System.out.println("Target : " + target);

            //set some sort of target value
            double output = pid.getOutput(sensor,target);

            System.out.println("output : " + output);
            sensor += output * 5;
            error.set(target - sensor);

        }
    }


}