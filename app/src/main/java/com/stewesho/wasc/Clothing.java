package com.stewesho.wasc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;

public class Clothing {
    private String name;
    private ClothingCategory cat;
    private HashMap<Weather, Condition> conditions;

    Clothing(String name, ClothingCategory cat){
        this.name = name;
        this.cat = cat;
        this.conditions = new HashMap<>();
    }

    public String getName(){
        return this.name;
    }

    public ClothingCategory getCategory(){
        return  this.cat;
    }

    public void addCondition(Condition c){
        this.conditions.put(c.getWeather(), c);
    }

    public double score(HashMap<Weather, Number> inputs){
        double score = 0;

        for (Weather weather : inputs.keySet()){
            if (conditions.containsKey(weather))
                score += conditions.get(weather).score(inputs.get(weather).doubleValue());
        }
        score /= conditions.size();
        return score;
    }

    public String toString(){
        return String.format(Locale.CANADA, "%s", this.name);
    }

}
