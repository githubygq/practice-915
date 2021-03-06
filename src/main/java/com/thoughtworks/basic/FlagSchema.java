package com.thoughtworks.basic;

public class FlagSchema {
    String flag;
    ValueType valueType;

    public FlagSchema(String flag, ValueType valueType) {
        this.flag = flag;
        this.valueType = valueType;
    }

    ValueHandler getType() {
        return valueType.getType();
    }

    Object getDefaultValue() {
        return valueType.getDefaultType();
    }

    String getFlag() {
        return this.flag;
    }
}
