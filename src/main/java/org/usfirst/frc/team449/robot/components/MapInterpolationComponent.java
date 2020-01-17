package org.usfirst.frc.team449.robot.components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class)
public class MapInterpolationComponent {

    //http://paulbourke.net/miscellaneous/interpolation/
    enum InterpolationMethod{
        LINEAR, COSINE
    }

    private InterpolationMethod currentMethod;

    private TreeMap<Double, Double> LUT;

    private Map.Entry<Double, Double> upper;
    private Map.Entry<Double, Double> lower;

    @JsonCreator
    public MapInterpolationComponent(@JsonProperty(required = true) InterpolationMethod method,
                                     @JsonProperty(required = true) List<Map.Entry<Double, Double>> entries){
        currentMethod = method;
        LUT = new TreeMap<>();
        for(Map.Entry<Double, Double> entry : entries){
            LUT.put(entry.getKey(), entry.getValue());
        }
    }

    public void updateMethod(InterpolationMethod method){
        currentMethod = method;
    }

    public double calculate(double x){
        if(LUT.containsKey(x)){
            return LUT.get(x);
        }
        updateEntries(x);
        double ratio = (x - lower.getKey()) / (upper.getKey() - lower.getKey());
        switch(currentMethod){
            case LINEAR:
                return lerp(ratio);
            case COSINE:
                return cerp(ratio);
            default:
                return 0;
        }
    }

    private void updateEntries(double x){
        lower = LUT.floorEntry(x) != null ? LUT.floorEntry(x) : new AbstractMap.SimpleEntry<>(0., 0.);
        upper = LUT.ceilingEntry(x) != null ? LUT.ceilingEntry(x) : new AbstractMap.SimpleEntry<>(0., 0.);
    }


    private double lerp(double x){
        return lower.getValue() * (1 - x) + upper.getValue()*x;
    }

    private double cerp(double x){
        double smoothpoint = (1 - Math.cos(x*Math.PI)) /2;
        return lerp(smoothpoint);
    }
}
