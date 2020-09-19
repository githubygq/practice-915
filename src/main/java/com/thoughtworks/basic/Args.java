package com.thoughtworks.basic;

import java.util.*;

public class Args {
    List<Arg> argPairs = new ArrayList<>();
    Schema schema;
    StringBuilder worngMessage = new StringBuilder();
    Map<String, Integer> countMap = new HashMap<>();

    public Args(Schema schema) {
        this.schema = schema;
    }

    public List<Arg> getArgPairs() {
        return argPairs;
    }

    public StringBuilder getWorngMessage() {
        return worngMessage;
    }

    ValueHandler getValueOf(String flag) {
        return schema.getTypeOf(flag);
    }

    Object getDefaultValueOf(String flag) {
        return schema.getDefaultValueOf(flag);
    }

    public void mapToPairs(String args) {
        List<String> results = Arrays.asList(args.split("-"));
        String[] pice;
        for (String result : results) {
            result = result.trim();
            if (result != null && !"".equals(result)) {
                pice = result.split(" ");
                //piceMap.put(pice[0], pice[1]);
                pice[1] = (pice[1] == null || "".equals(pice[1])) ? (String) getDefaultValueOf(pice[0]) : pice[1];
                argPairs.add(new Arg(pice[0], pice[1]));
            }
        }
    }

    //方法太烂
    public boolean handleFlag() {
        for (FlagSchema flagSchemas : schema.flagSchemas) {
            countMap.put(flagSchemas.getFlag(), 0);
        }
        for (Arg arg : argPairs) {
            switch (arg.getFlag()) {
                case "l":
                    int l = countMap.get("l") == null ? 0 : countMap.get("l");
                    countMap.put("l", l + 1);
                    break;
                case "p":
                    int p = countMap.get("p") == null ? 0 : countMap.get("p");
                    countMap.put("p", p + 1);
                    break;
                case "d":
                    int d = countMap.get("d") == null ? 0 : countMap.get("d");
                    countMap.put("d", d + 1);
                    break;
                default:
                    worngMessage.delete(0, worngMessage.length());
                    worngMessage.append("非法的flag！");
                    return false;
            }
        }

        for (String key : countMap.keySet()) {
            if (countMap.get(key) > 1) {
                worngMessage.delete(0, worngMessage.length());
                worngMessage.append("重复的flag！");
                return false;
            }
            //添加缺失的指令
            if(countMap.get(key)==0){
                argPairs.add(new Arg(key,schema.getDefaultValueOf(key).toString()));
            }
        }
        return true;
    }

    public boolean handleValue() {
        worngMessage.delete(0, worngMessage.length());
        for (Arg arg : argPairs) {
            String key = arg.getFlag();
            String value = arg.getValue();

            switch (key) {
                case "l":
                    //代表不是布尔型
                    Boolean aBoolean = (Boolean) getValueOf(key).handle(value);
                    //代表原来的字符串是true或false
                    boolean result= ("true".equals(value)||"false".equals(value));
                    //排除本身就是false或者默认为false这种特殊情况
                    if (!(aBoolean)&&!result) {
                        worngMessage.append("-l 命令为非布尔类型！");
                        return false;
                    }
                    break;
                case "p":
                    try {
                        Integer integer = (Integer) getValueOf(key).handle(value);
                    }catch(NumberFormatException e){
                        worngMessage.append("-p 命令为非数字类型！");
                        return false;
                    }
                    Integer integer = (Integer) getValueOf(key).handle(value);
                    if (!(integer instanceof Integer)) {
                        worngMessage.append("-p 命令为非数字类型！");
                        return false;
                    }
                    break;
                case "d":
                    String string = (String) getValueOf(key).handle(value);
                    if (!(string instanceof String)) {
                        worngMessage.append("-d 命令为非字符串类型！");
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
}
