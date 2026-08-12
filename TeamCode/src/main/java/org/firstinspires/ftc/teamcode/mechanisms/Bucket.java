package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Bucket {
    private Servo bucketTilt;
    static final double POS_OPEN = 0.5; //Bucket ready
    static final double POS_TILT = 0; //Bucket tilted
    public Bucket(HardwareMap hardwareMap) {
        bucketTilt = hardwareMap.get(Servo.class, "bucketTilt");
        bucketTilt.scaleRange(0,1);
    }
    public void bucketTilted() {
        bucketTilt.setPosition(POS_TILT);
    }
    public void bucketOpen() {
        bucketTilt.setPosition(POS_OPEN);
    }
}
