package com.stewesho.wasc;

public class ComparisonCondition extends Condition {

    double base;
    Weather weather;
    CompType comp;

    ComparisonCondition(double base, CompType comp, Weather weather){
        this.base = base;
        this.weather = weather;
        this.comp = comp;
    }

    public double score(Number _input){
        double input = _input.intValue();
        if (this.comp.equals(CompType.EQUALS))
            return base == input ? 1 : 0;
        else if (this.comp.equals(CompType.GREATER_THAN))
            return input > this.base ? 1 : 0;
        else
            return input < this.base ? 1 : 0;
    }
}