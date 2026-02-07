

package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.sun.tools.javac.Main;

//http://192.168.43.1:8080/dash
@TeleOp
@Config
public class Tele extends LinearOpMode {
    public static double MainArmPosition = 0;
    @Override
    public void runOpMode() throws InterruptedException {

        double leftY  = gamepad2.left_stick_y;
        double rightY = gamepad2.right_stick_y;
        ElapsedTime timer = new ElapsedTime();
        Servo ArmHook;
        ArmHook = hardwareMap.get(Servo.class,"ArmHook");
        DcMotor MainArm = hardwareMap.get(DcMotor.class, "MainArm");
        DcMotor LeftThing = hardwareMap.get(DcMotor.class, "LeftThing");
        DcMotor RightThing = hardwareMap.get(DcMotor.class, "RightThing");
        final double ext = 1.0;
        final double retract = 0.5;

        MecanumDrive mecanumDrive;
        mecanumDrive = new MecanumDrive(hardwareMap,new Pose2d(0,0,0));

        waitForStart();

        while (opModeIsActive()) {

            mecanumDrive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x
                    ),
                    -gamepad1.right_stick_x
            ));
            }

            if (gamepad2.a) {
                ArmHook.setPosition(ext);
            }
            else{
                ArmHook.setPosition(retract);
            }


            if (gamepad1.b) {
                LeftThing.setPower(1.0);
            } else {
                LeftThing.setPower(0.0);
            }

            if (gamepad1.b) {
                RightThing.setPower(1.0);
            } else {
                RightThing.setPower(0.0);
            }

            if (gamepad2.right_bumper) {

                MainArm.setPower(1);
                sleep(225);
                MainArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                ArmHook.setPosition(ext);
                sleep(2000);
            }

            ArmHook.setPosition(MainArmPosition);
            telemetry.addData("Servo Position",ArmHook.getPosition());
            telemetry.update();
            telemetry.addData("A Button", gamepad2.a);
            telemetry.update();





        }
    }















