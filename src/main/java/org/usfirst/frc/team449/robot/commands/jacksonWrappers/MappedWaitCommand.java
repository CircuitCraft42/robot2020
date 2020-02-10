package org.usfirst.frc.team449.robot.commands.jacksonWrappers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * Does nothing but takes a specified amount of time to finish.
 *
 * @see WaitCommand
 */
//@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class)
public class MappedWaitCommand extends WaitCommand {
    /**
     * Instantiates a {@link WaitCommand} with the given timeout.
     *
     * @param timeout the time the command takes to run (seconds)
     */
    @JsonCreator
    public MappedWaitCommand(@JsonProperty(required = true) final double timeout) {
        super(timeout);
    }
}
