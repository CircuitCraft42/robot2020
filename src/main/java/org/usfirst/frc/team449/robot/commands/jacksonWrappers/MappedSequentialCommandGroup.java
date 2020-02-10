package org.usfirst.frc.team449.robot.commands.jacksonWrappers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Runs a list of commands in sequence.
 *
 * @see SequentialCommandGroup
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class)
public class MappedSequentialCommandGroup extends SequentialCommandGroup {
    /**
     * Creates a new SequentialCommandGroup.  The given commands will be run sequentially, with
     * the CommandGroup finishing when the last command finishes.
     *
     * @param commands           the commands to include in this group.
     * @param requiredSubsystems the list of subsystems that this command requires
     */
    @JsonCreator
    public MappedSequentialCommandGroup(@NotNull @JsonProperty(required = true) final Command[] commands,
                                        @Nullable final Subsystem[] requiredSubsystems) {
        super(commands);
        if (requiredSubsystems != null) this.addRequirements(requiredSubsystems);
    }
}
