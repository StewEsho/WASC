package com.stewesho.wasc;

public class ComparisonCondition extends Condition {

    double base;
    Weather weather;

    ComparisonCondition(double base, Weather weather){
        this.base = base;
        this.weather = weather;
    }

    public double score(double input){
        if (input == this.base)
            return 0;
        else
            return input > this.base ? 1 : -1;
    }
}
