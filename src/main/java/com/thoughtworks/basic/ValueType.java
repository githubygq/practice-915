package com.thoughtworks.basic;

public class ValueType {
    String valueType;
    ValueFactory valueFactory;

    public ValueType(String valueType, ValueFactory valueFactory) {
        this.valueType = valueType;
        this.valueFactory = valueFactory;
    }

    public ValueHandler getType() {
        return valueFactory.creat(valueType);
    }

    public Object getDefaultType() {
        return valueFactory.getDefault(valueType);
    }
}
