package com.stewesho.wasc;

import java.util.HashMap;

public class NullClothing extends Clothing {

    NullClothing(ClothingCategory cat){
        super("Nothing", cat);
    }

    @Override
    public double score(HashMap<Weather, Number> inputs){
        return 0.2;
    }
}
