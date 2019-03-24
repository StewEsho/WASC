package com.stewesho.wasc;

public class SingleScaleCondition extends Condition {
    private double start;
    private double peak;
    private boolean increasing; //positive if going up, negative if going down
    private double average;
    private double range;
    private Weather weather;

    SingleScaleCondition(double start, double peak, Weather weather){
        this.start = start;
        this.peak = peak;
        this.average = (this.start + this.peak)/2;
        this.range = Math.abs(this.start - this.peak);
        this.increasing = this.peak > this.start;
    }

    public double score(double input){
        if (this.increasing ? input < this.start : input > this.start)
            return 0;
        else
            return Math.abs(input - this.start)/range;
    }
}
