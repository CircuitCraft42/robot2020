package org.usfirst.frc.team449.robot.subsystem.interfaces.flywheel.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import io.github.oblarg.oblog.annotations.Log;
import org.jetbrains.annotations.NotNull;
import org.usfirst.frc.team449.robot.subsystem.interfaces.flywheel.SubsystemFlywheel;
import org.usfirst.frc.team449.robot.subsystem.interfaces.intake.SubsystemIntake;

/**
 * Turn on the flywheel and feeder (but not the kicker) in order to give the flywheel time to get up to speed.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class)
public class SpinUpFlywheel extends InstantCommand {

    /**
     * The subsystem to execute this command on.
     */
    @NotNull
    @Log.Exclude
    private final SubsystemFlywheel flywheel;
    @NotNull
    @Log.Exclude
    private final SubsystemIntake feeder;

    /**
     * Default constructor
     *
     * @param flywheel The subsystem to execute this command on.
     */
    @JsonCreator
    public SpinUpFlywheel(@NotNull @JsonProperty(required = true) SubsystemFlywheel flywheel,
                          @NotNull @JsonProperty(required = true) SubsystemIntake feeder) {
        this.flywheel = flywheel;
        this.feeder = feeder;
    }

    /**
     * Log when this command is initialized
     */
    @Override
    public void initialize() {
        Shuffleboard.addEventMarker("SpinUpFlywheel init.", this.getClass().getSimpleName(), EventImportance.kNormal);
        //Logger.addEvent("SpinUpFlywheel init.", this.getClass());
    }

    /**
     * Turn the feeder off and the flywheel on.
     */
    @Override
    public void execute() {
        feeder.setMode(SubsystemIntake.IntakeMode.IN_FAST); // Turn feeder on.
        flywheel.turnFeederOff(); // Turn kicker off.
        flywheel.setFlywheelState(SubsystemFlywheel.FlywheelState.SPINNING_UP); // Turn flywheel on.
    }

    /**
     * Log when this command ends
     */
    @Override
    public void end(boolean interrupted) {
        if(interrupted){
            Shuffleboard.addEventMarker("SpinUpFlywheel Interrupted!", this.getClass().getSimpleName(), EventImportance.kNormal);
        }
        Shuffleboard.addEventMarker("SpinUpFlywheel end.", this.getClass().getSimpleName(), EventImportance.kNormal);
    }
}