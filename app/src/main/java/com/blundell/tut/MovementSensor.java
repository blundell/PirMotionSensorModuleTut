package com.blundell.tut;

interface MovementSensor {

    void startup();

    void shutdown();

    interface Listener {
        void onMovement();
    }

}
