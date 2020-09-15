package com.thoughtworks.basic;

public class IntegerValueHandler implements ValueHandler {
    @Override
    public Integer handle(String value) {
        return Integer.valueOf(value);
    }
}
