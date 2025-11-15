package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Config
public class Tele extends LinearOpMode {
    public static double leftarmposition = 0;
    @Override
    public void runOpMode() throws InterruptedException {

        Servo leftarm;
        leftarm = hardwareMap.get(Servo.class,"leftarm");

        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap,new Pose2d(0,0,0));

        waitForStart();

        while (opModeIsActive()) {
            mecanumDrive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x
                    ),
                    -gamepad1.right_stick_x
            ));
            leftarm.setPosition(leftarmposition);

        }
    }
}
