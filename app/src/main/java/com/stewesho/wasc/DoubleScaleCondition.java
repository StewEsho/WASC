package com.stewesho.wasc;

public class DoubleScaleCondition extends Condition {

    private double upperbound;
    private double lowerbound;
    private double average;
    private double range;

    DoubleScaleCondition(double lowerbound, double upperbound, Weather weather){
        this.upperbound = upperbound;
        this.lowerbound = lowerbound;
        this.average = (this.upperbound + this.lowerbound)/2;
        this.range = this.upperbound - this.lowerbound;
        this.weather = weather;
    }

    public double score(Number input_){
        double input = input_.doubleValue();
        if (input > upperbound || input < lowerbound)
            return 0;
        else
            return 1 - Math.abs(input - average)/(2 * range);
    }

}
