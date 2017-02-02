### HC-SR501
#### PIR Motion Sensor
##### Android Things User Driver


Accompanying Blog Post:

http://blog.blundellapps.co.uk/tut-android-things-writing-a-pir-motion-sensor-driver/

Simple useage:

```java

   @Override
   protected void onCreate() {
        Gpio bus =  bus = new PeripheralManagerService().openGpio("BCM18");
        motionSensor = new PirMotionSensor(bus, this);
        motionSensor.startup();
   }

    @Override
    public void onMovement() {
        Log.d("TUT", "MOVEMENT DETECTED");
    }

    @Override
    protected void onDestroy() {
        motionSensor.shutdown();
        super.onDestroy();
    }


```
