package com.stewesho.wasc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Wardrobe {

    private HashMap<ClothingCategory, ArrayList<Clothing>> wardrobe;

    Wardrobe(Collection<Clothing> clothes){
        wardrobe = new HashMap<>();
        for (ClothingCategory cat : ClothingCategory.values()){
            wardrobe.put(cat, new ArrayList<Clothing>());
        }
        addClothes(clothes);
    }

    public void addClothes(Collection<Clothing> clothes){
        for (Clothing clothing : clothes) {
            wardrobe.get(clothing.getCategory()).add(clothing);
        }
    }

    public ArrayList<Clothing> getOutfit(HashMap<Weather, Number> weatherArray){
        ArrayList<Clothing> outfit = new ArrayList<>();
        for (ClothingCategory cat : ClothingCategory.values()) {
            //For OTHER items, add all that apply
            if (cat == ClothingCategory.OTHER){
                for (Clothing clothing : wardrobe.get(cat)){
                    double score = clothing.score(weatherArray);
                    if (score > 0.5){
                        outfit.add(clothing);
                    }
                }
            } else { //For all other parts, choose the best clothing to wear.
                double maxScore = 0;
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
}
