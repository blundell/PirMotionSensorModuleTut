package com.blundell.tut;

interface MotionSensor {

    void startup();

    void shutdown();

    interface Listener {
        void onMovement();
    }

}
