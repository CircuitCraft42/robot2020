package org.usfirst.frc.team449.robot.subsystem.interfaces.binaryMotor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.oblarg.oblog.Loggable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.usfirst.frc.team449.robot.generalInterfaces.simpleMotor.SimpleMotor;

/**
 * A simple example of a binary motor subsystem.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class)
public class BinaryMotorSimple extends SubsystemBase implements SubsystemBinaryMotor, Loggable {

    /**
     * The motor that this subsystem controls.
     */
    @NotNull
    private final SimpleMotor motor;

    /**
     * The velocity to run the motor at, on [-1, 1].
     */
    private final double velocity;

    /**
     * Whether or not the motor is currently on.
     */
    private boolean motorOn;

    /**
     * Default constructor.
     *
     * @param motor    The motor that this subsystem controls.
     * @param velocity The velocity to run the motor at, on [-1, 1]. Defaults to 1.
     */
    @JsonCreator
    public BinaryMotorSimple(@NotNull @JsonProperty(required = true) SimpleMotor motor,
                             @Nullable Double velocity) {
        this.motor = motor;
        this.velocity = velocity != null ? velocity : 1;
        motorOn = false;
    }

    /**
     * Turns the motor on, and sets it to a map-specified speed.
     */
    @Override
    public void turnMotorOn() {
        motor.setVelocity(velocity);
        motorOn = true;
    }

    /**
     * Turns the motor off.
     */
    @Override
    public void turnMotorOff() {
        motor.setVelocity(0);
        motorOn = false;
    }

    /**
     * @return true if the motor is on, false otherwise.
     */
    @Override
    public boolean isMotorOn() {
        return motorOn;
    }
}
