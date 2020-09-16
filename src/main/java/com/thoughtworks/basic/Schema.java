package com.thoughtworks.basic;

import java.util.ArrayList;
import java.util.List;

public class Schema {
    List<FlagSchema> flagSchemas =new ArrayList<FlagSchema>();

    public Schema(List<FlagSchema> flagSchemas) {
        this.flagSchemas = flagSchemas;
    }

    public String getTypeOf(String flag){
        for (FlagSchema flagSchema :flagSchemas) {
            if(flagSchema.getFlag().equals(flag)){
                return flagSchema.getType();
            }
        }
        return "非法的flag";
    }

    public Object getDefaultValueOf(String flag){
        for (FlagSchema flagSchema :flagSchemas) {
            if(flagSchema.getFlag().equals(flag)){
                return flagSchema.getDefaultValue();
            }
        }
        return "非法的flag";
    }

    /*public void validateFlagDistinct(){
        for (FlagSchema flagSchema :flagSchemas){

        }
    }*/

}
