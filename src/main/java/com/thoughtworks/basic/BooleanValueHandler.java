package com.thoughtworks.basic;

public class BooleanValueHandler implements ValueHandler {
    @Override
    public Boolean handle(String value) {
        return Boolean.parseBoolean(value);
    }
}
