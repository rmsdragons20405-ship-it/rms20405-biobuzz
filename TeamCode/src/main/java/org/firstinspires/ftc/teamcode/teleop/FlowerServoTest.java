package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ServoGate;
@Disabled
@TeleOp(name = "FlowerServoTest")
public class FlowerServoTest extends OpMode {
    ServoGate bench = new ServoGate();

    @Override
    public void init() {
        bench.init(hardwareMap);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {
        if (gamepad2.y) {
            bench.setServoPos(-.1);
        }
        if (gamepad2.dpad_up) {
            bench.setServoPos(0.1);
        }

    }



}
