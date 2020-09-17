package com.thoughtworks.basic;

public class Arg {
    String flag;
    String value;

    public Arg(String flag, String value) {
        this.flag = flag;
        this.value = value;
    }

    public Arg(String argPair, Schema schema) {

    }

    public Object parseValue() {
        return null;
    }

    public boolean withFlag(String flag) {
        if (null == flag || "".equals(flag)) {
            return false;
        }
        return true;
    }

    public String getFlag() {
        return this.flag;
    }

    public String getValue() {
        return value;
    }

    private void isArgValid() {

    }
}
