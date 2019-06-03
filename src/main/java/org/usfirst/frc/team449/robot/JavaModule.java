package org.usfirst.frc.team449.robot;

import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.oblarg.oblog.Loggable;
import org.usfirst.frc.team449.robot.mixIn.DoubleSupplierMixIn;
import org.usfirst.frc.team449.robot.mixIn.DoubleUnaryOperatorMixIn;
import org.usfirst.frc.team449.robot.mixIn.LoggableMixIn;

import java.util.function.DoubleSupplier;
import java.util.function.DoubleUnaryOperator;

/**
 * A module that adds mix-ins for various Java interfaces, classes, etc.
 */
public class JavaModule extends SimpleModule {

    /**
     * Default constructor
     */
    public JavaModule() {
        super("JavaModule");
    }

    /**
     * Mixes in some mix-ins to the given context.
     *
     * @param context the context to set up
     */
    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);
        context.setMixInAnnotations(DoubleUnaryOperator.class, DoubleUnaryOperatorMixIn.class);
        context.setMixInAnnotations(DoubleSupplier.class, DoubleSupplierMixIn.class);
        context.setMixInAnnotations(Loggable.class, LoggableMixIn.class);
    }
}
