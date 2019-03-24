package com.stewesho.wasc;

public abstract class Condition {

    Weather weather;

    abstract double score(Number input);

    Weather getWeather(){
        return this.weather;
    }
}
