package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class TalonSRXGroup implements MotorController{
    private TalonSRX[] talons;
    private double currentPower = 0;
    private boolean disabled = false;

    TalonSRXGroup(TalonSRX[] talons) {
        this.talons = talons;
    }

    public void set(double power) {
        if (!disabled) {
            currentPower = power;
            for (int i = 0; i < talons.length; i++) {
                talons[i].set(TalonSRXControlMode.PercentOutput, power);
            }
        }
    }

    public double get() {
        return currentPower;
    }

    public void setInverted(boolean inverted) {
        for (int i = 0; i < talons.length; i++) {
            talons[i].setInverted(inverted);
        }
    }

    public boolean getInverted() {
        return talons[0].getInverted();
    }

    public void disable() {
        set(0);
        disabled = true;
    }

    public void stopMotor() {
        set(0);
    }
}
