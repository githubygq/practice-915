package com.thoughtworks.basic;

public class StringValueHandler implements ValueHandler {
    @Override
    public String handle(String value) {
        return String.valueOf(value);
    }
}
