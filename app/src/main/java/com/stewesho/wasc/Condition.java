package com.stewesho.wasc;

public abstract class Condition {

    Weather weather;

    abstract double score(double input);

    Weather getWeather(){
        return this.weather;
    }
}
