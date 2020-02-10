package org.usfirst.frc.team449.robot.commands.jacksonWrappers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;

/**
 * Waits until a certain in-game time.
 *
 * @see WaitUntilCommand
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class)
public class MappedWaitUntilCommand extends WaitUntilCommand {

    /**
     * Default constructor.
     *
     * @param time The time, in seconds after auto starts, to wait until.
     */
    @JsonCreator
    public MappedWaitUntilCommand(@JsonProperty(required = true) final double time) {
        super(time);
    }
}
