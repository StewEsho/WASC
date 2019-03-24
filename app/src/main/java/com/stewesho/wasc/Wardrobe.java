package com.stewesho.wasc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Wardrobe {

    private HashMap<ClothingCategory, ArrayList<Clothing>> wardrobe;

    Wardrobe(){
        wardrobe = new HashMap<>();
        for (ClothingCategory cat : ClothingCategory.values()){
            wardrobe.put(cat, new ArrayList<Clothing>());
        }
        //Default clothes auto-enabled
        this.addDefaultClothes();
    }

    Wardrobe(Collection<Clothing> clothes){
        this();
        addClothes(clothes);
    }

    Wardrobe(boolean defaultClothes){
        this();
        //Since default clothes is enabled, we must add all the standard clothes here.
        if (defaultClothes)
            this.addDefaultClothes();
    }

    public void addClothes(Collection<Clothing> clothes){
        for (Clothing clothing : clothes) {
            wardrobe.get(clothing.getCategory()).add(clothing);
        }
    }

    public void addDefaultClothes(){
        ArrayList<Clothing> defaultClothes = new ArrayList<>();
        //HEAD
        Clothing rimmedCap = new Clothing("Rimmed Cap", ClothingCategory.HEAD);
        rimmedCap.addCondition(new ComparisonCondition(4, CompType.GREATER_THAN, Weather.UV_INDEX));
        rimmedCap.addCondition(new SingleScaleCondition(1, 0, Weather.CLOUD_COVERAGE));
        rimmedCap.addCondition(new DoubleScaleCondition(0, 30, Weather.TEMPERATURE));
        defaultClothes.add(rimmedCap);

        Clothing toque = new Clothing("Toque", ClothingCategory.HEAD);
        toque.addCondition(new SingleScaleCondition(10, -5, Weather.TEMPERATURE));
        defaultClothes.add(toque);

        NullClothing nothing_head = new NullClothing(ClothingCategory.HEAD);
        defaultClothes.add(nothing_head);

        //FACE
        Clothing sunglasses = new Clothing("Sunglasses", ClothingCategory.FACE);
        sunglasses.addCondition(new SingleScaleCondition(0, 8, Weather.UV_INDEX));
        sunglasses.addCondition(new SingleScaleCondition(1, 0, Weather.CLOUD_COVERAGE));
        defaultClothes.add(sunglasses);

        NullClothing nothing_face = new NullClothing(ClothingCategory.FACE);
        defaultClothes.add(nothing_face);

        //NECK
        Clothing scarf = new Clothing("Scarf", ClothingCategory.NECK);
        scarf.addCondition(new SingleScaleCondition(0, -10, Weather.TEMPERATURE));
        scarf.addCondition(new SingleScaleCondition(30, 50, Weather.WINDSPEED));
        defaultClothes.add(scarf);

        NullClothing nothing_neck = new NullClothing(ClothingCategory.NECK);
        defaultClothes.add(nothing_neck);

        //UNDERTOP
        Clothing undershirt = new Clothing("Undershirt", ClothingCategory.UNDERTOP);
        undershirt.addCondition(new DoubleScaleCondition(-5, 5, Weather.TEMPERATURE));
        defaultClothes.add(undershirt);

        Clothing tshirt_under = new Clothing("T-Shirt Layering", ClothingCategory.UNDERTOP);
        tshirt_under.addCondition(new DoubleScaleCondition(-10, 0, Weather.TEMPERATURE));
        defaultClothes.add(tshirt_under);

        Clothing longsleeve_under = new Clothing("Longsleeve Layering", ClothingCategory.UNDERTOP);
        longsleeve_under.addCondition(new SingleScaleCondition(-5, -15, Weather.TEMPERATURE));
        defaultClothes.add(longsleeve_under);

        NullClothing nothing_undertop = new NullClothing(ClothingCategory.UNDERTOP);
        defaultClothes.add(nothing_undertop);

        //TOP
        Clothing tanktop = new Clothing("Tank Top", ClothingCategory.TOP);
        tanktop.addCondition(new SingleScaleCondition(20, 30, Weather.TEMPERATURE));
        tanktop.addCondition(new SingleScaleCondition(15, 0, Weather.WINDSPEED));
        defaultClothes.add(tanktop);

        Clothing tshirt = new Clothing("T-Shirt", ClothingCategory.TOP);
        tshirt.addCondition(new DoubleScaleCondition(10, 25, Weather.TEMPERATURE));
        defaultClothes.add(tshirt);

        Clothing longsleeve = new Clothing("Longsleeve", ClothingCategory.TOP);
        longsleeve.addCondition(new SingleScaleCondition(15, 0, Weather.TEMPERATURE));
        longsleeve.addCondition(new SingleScaleCondition(0, 50, Weather.WINDSPEED));
        defaultClothes.add(longsleeve);

        //COATS
        Clothing windbreaker = new Clothing("Windbreaker", ClothingCategory.COAT);
        windbreaker.addCondition(new DoubleScaleCondition(0, 15, Weather.TEMPERATURE));
        windbreaker.addCondition(new DoubleScaleCondition(15, 40, Weather.WINDSPEED));
        defaultClothes.add(windbreaker);

        Clothing cardigan = new Clothing("Cardigan Sweater", ClothingCategory.COAT);
        cardigan.addCondition(new DoubleScaleCondition(0, 10, Weather.TEMPERATURE));
        cardigan.addCondition(new ComparisonCondition(15, CompType.LESS_THAN, Weather.WINDSPEED));
        defaultClothes.add(cardigan);

        Clothing hoodie = new Clothing("Hoodie", ClothingCategory.COAT);
        hoodie.addCondition(new DoubleScaleCondition(-5, 10, Weather.TEMPERATURE));
        hoodie.addCondition(new DoubleScaleCondition(0, 0.75, Weather.PRECIP_CHANCE));
        defaultClothes.add(hoodie);

        Clothing jacket_light = new Clothing("Light Jacket", ClothingCategory.COAT);
        jacket_light.addCondition(new DoubleScaleCondition(-10, 10, Weather.TEMPERATURE));
        jacket_light.addCondition(new SingleScaleCondition(0.25, 0.8, Weather.PRECIP_CHANCE));
        jacket_light.addCondition(new DoubleScaleCondition(15, 40, Weather.WINDSPEED));
        defaultClothes.add(jacket_light);

        Clothing jacket_heavy = new Clothing("Heavy Jacket", ClothingCategory.COAT);
        jacket_heavy.addCondition(new SingleScaleCondition(-5, -10, Weather.TEMPERATURE));
        jacket_heavy.addCondition(new SingleScaleCondition(0.5, 1, Weather.PRECIP_CHANCE));
        jacket_heavy.addCondition(new SingleScaleCondition(15, 50, Weather.WINDSPEED));
        defaultClothes.add(jacket_heavy);

        NullClothing nothing_coat = new NullClothing(ClothingCategory.COAT);
        defaultClothes.add(nothing_coat);

        //HANDS
        Clothing light_gloves = new Clothing("Light Gloves", ClothingCategory.HANDS);
        light_gloves.addCondition(new DoubleScaleCondition(-5, 15, Weather.TEMPERATURE));
        defaultClothes.add(light_gloves);

        Clothing gloves = new Clothing("Gloves", ClothingCategory.HANDS);
        gloves.addCondition(new DoubleScaleCondition(-15, 5, Weather.TEMPERATURE));
        defaultClothes.add(gloves);

        Clothing mittens = new Clothing("Mittens", ClothingCategory.HANDS);
        mittens.addCondition(new SingleScaleCondition(0, -20, Weather.TEMPERATURE));
        defaultClothes.add(mittens);

        NullClothing nothing_hands = new NullClothing(ClothingCategory.HANDS);
        defaultClothes.add(nothing_hands);

        //PANT LAYERS
        Clothing long_johns = new Clothing("Long Johns", ClothingCategory.PANTS_LAYERS);
        long_johns.addCondition(new SingleScaleCondition(0, -20, Weather.TEMPERATURE));
        long_johns.addCondition(new SingleScaleCondition(10, 35, Weather.WINDSPEED));
        defaultClothes.add(long_johns);

        NullClothing nothing_pants_layers = new NullClothing(ClothingCategory.PANTS_LAYERS);
        defaultClothes.add(nothing_pants_layers);

        //PANTS
        Clothing shorts = new Clothing("Shorts", ClothingCategory.PANTS);
        shorts.addCondition(new SingleScaleCondition(15, 25, Weather.TEMPERATURE));
        shorts.addCondition(new SingleScaleCondition(15, -1, Weather.WINDSPEED));
        defaultClothes.add(shorts);

        Clothing trousers = new Clothing("Trousers", ClothingCategory.PANTS);
        trousers.addCondition(new SingleScaleCondition(25, -10, Weather.TEMPERATURE));
        defaultClothes.add(trousers);

        //FEET
        Clothing sandals = new Clothing("Sandals", ClothingCategory.FEET);
        sandals.addCondition(new SingleScaleCondition(20, 30, Weather.TEMPERATURE));
        sandals.addCondition(new SingleScaleCondition(6, -1, Weather.UV_INDEX));
        defaultClothes.add(sandals);

        Clothing shoes = new Clothing("Shoes", ClothingCategory.FEET);
        shoes.addCondition(new SingleScaleCondition(-10, 25, Weather.TEMPERATURE));
        shoes.addCondition(new SingleScaleCondition(1.25, 0, Weather.PRECIP_CHANCE));
        defaultClothes.add(shoes);

        Clothing boots = new Clothing("Boots", ClothingCategory.FEET);
        boots.addCondition(new SingleScaleCondition(5, -10, Weather.TEMPERATURE));
        boots.addCondition(new SingleScaleCondition(0.25, 0.85, Weather.PRECIP_CHANCE));
        defaultClothes.add(boots);

        //OTHER!!
        Clothing umbrella = new Clothing("Umbrella", ClothingCategory.OTHER);
        umbrella.addCondition(new ComparisonCondition(0.5, CompType.GREATER_THAN, Weather.PRECIP_CHANCE));
        defaultClothes.add(umbrella);

        Clothing sunscreen = new Clothing("Sunscreen", ClothingCategory.OTHER);
        sunscreen.addCondition(new ComparisonCondition(4, CompType.GREATER_THAN, Weather.UV_INDEX));
        sunscreen.addCondition(new SingleScaleCondition(0.5, 0.1, Weather.CLOUD_COVERAGE));
        defaultClothes.add(sunscreen);

        //Add all the clothes to the wardrobe
        this.addClothes(defaultClothes);
    }

    public ArrayList<Clothing> getOutfit(HashMap<Weather, Number> weatherArray){
        ArrayList<Clothing> outfit = new ArrayList<>();
        for (ClothingCategory cat : ClothingCategory.values()) {
            //For OTHER items, add all that apply
            if (cat == ClothingCategory.OTHER){
                for (Clothing clothing : wardrobe.get(cat)){
                    double score = clothing.score(weatherArray);
                    if (score > 0.25){
                        outfit.add(clothing);
                    }
                }
            } else { //For all other parts, choose the best clothing to wear.
                double maxScore = -1;
                Clothing bestClothing = null;
                for (Clothing clothing : wardrobe.get(cat)){
                    double score = clothing.score(weatherArray);
                    if (score > maxScore){
                        maxScore = score;
                        bestClothing = clothing;
                    }
                }
                outfit.add(bestClothing);
            }
        }
        return outfit;
    }

    public String toString(){
        return "WARDROBE: " + this.wardrobe.toString();
    }
}
