package com.blundell.tut;

import android.util.Log;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.GpioCallback;

import java.io.IOException;

class PirMovementSensor implements MovementSensor {

    private final Gpio driver;

    private final MovementSensor.Listener listener;

    PirMovementSensor(Gpio driver, Listener listener) {
        this.driver = driver;
        this.listener = listener;
    }

    @Override
    public void startup() {
        try {
            driver.setDirection(Gpio.DIRECTION_IN);
            driver.setActiveType(Gpio.ACTIVE_HIGH);
            driver.setEdgeTriggerType(Gpio.EDGE_RISING);
        } catch (IOException e) {
            throw new IllegalStateException("Sensor can't start - App is foobar'd", e);
        }
        try {
            driver.registerGpioCallback(callback);
        } catch (IOException e) {
            throw new IllegalStateException("Sensor can't register callback - App is foobar'd", e);
        }
    }

    private final GpioCallback callback = new GpioCallback() {
        @Override
        public boolean onGpioEdge(Gpio gpio) {
            listener.onMovement();
            return true; // True to continue listening
        }
    };

    @Override
    public void shutdown() {
        driver.unregisterGpioCallback(callback);
        try {
            driver.close();
        } catch (IOException e) {
            Log.e("TUT", "Failed to shut down. You might get errors next time you try to start.", e);
        }
    }

}
