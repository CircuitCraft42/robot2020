package org.usfirst.frc.team449.robot.commands.multiSubsystem;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import org.jetbrains.annotations.Nullable;
import org.usfirst.frc.team449.robot.subsystem.interfaces.flywheel.SubsystemFlywheel;
import org.usfirst.frc.team449.robot.subsystem.interfaces.flywheel.commands.TurnAllOff;
import org.usfirst.frc.team449.robot.subsystem.interfaces.intake.SubsystemIntake;
import org.usfirst.frc.team449.robot.subsystem.interfaces.intake.commands.SetIntakeMode;
import org.usfirst.frc.team449.robot.subsystem.interfaces.solenoid.SubsystemSolenoid;
import org.usfirst.frc.team449.robot.subsystem.interfaces.solenoid.commands.SolenoidReverse;

/** Command group to reset everything. Turns everything off, raises intake */
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class)
public class ResetShooter<T extends Subsystem & SubsystemIntake & SubsystemSolenoid>
    extends ParallelCommandGroup {

  /**
   * Constructs a ResetShooter command group
   *
   * @param subsystemFlywheel flywheel subsystem. Can be null.
   * @param subsystemIntake intake subsystem. Can be null.
   */
  @JsonCreator
  public ResetShooter(@Nullable SubsystemFlywheel subsystemFlywheel, @Nullable T subsystemIntake) {
    if (subsystemFlywheel != null) {
      addCommands(new TurnAllOff(subsystemFlywheel));
    }
    if (subsystemIntake != null) {
      addCommands(
          new SolenoidReverse(subsystemIntake),
          new SetIntakeMode(subsystemIntake, SubsystemIntake.IntakeMode.OFF));
    }
  }
}
