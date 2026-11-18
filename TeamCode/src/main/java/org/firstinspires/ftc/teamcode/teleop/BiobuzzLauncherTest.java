package org.firstinspires.ftc.teamcode.teleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="LauncherTestBiobuzz")
public class BiobuzzLauncherTest extends OpMode {
    private DcMotor Launcher, CounterRoller;

    @Override
    public void init(){
        Launcher = hardwareMap.get(DcMotor.class, "Launcher");
        CounterRoller = hardwareMap.get(DcMotor.class, "CounterRoller");
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop(){
        if (gamepad2.a) {
            Launcher.setPower(0.37);
            CounterRoller.setPower(0.93);
        }
        if (gamepad2.b) {
            Launcher.setPower(0);
            CounterRoller.setPower(0);
        }

    }

}
