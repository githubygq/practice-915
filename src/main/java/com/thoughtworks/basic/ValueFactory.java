package com.thoughtworks.basic;

public class ValueFactory {
    public ValueHandler creat(String flagType) {
        switch (flagType) {
            case "boolean":
                return new BooleanValueHandler();
            case "integer":
                return new IntegerValueHandler();
            default:
        }
                return new StringValueHandler();
    }

    public Object getDefault(String flagType) {
        switch (flagType) {
            case "boolean":
                return false;
            case "integer":
                return 0;
            default:
                return "";
        }
    }
}
