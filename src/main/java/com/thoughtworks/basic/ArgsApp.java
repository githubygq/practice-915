package com.thoughtworks.basic;

public class ArgsApp {
    Args args;
    String commands;

    public ArgsApp(Args args, String commands) {
        this.args = args;
        this.commands = commands;
    }

    public String showCommand(){
        String result="";
        args.mapToPairs(commands);
        if(args.handleFlag()&&args.handleValue()){
            for (Arg arg:args.getArgPairs()) {
                result+=arg.getFlag()+":"+arg.getValue()+" ";
            }
        }else {
            result = args.getWorngMessage().toString();
            args.getWorngMessage().delete(0,args.getWorngMessage().length());
        }
        return result;
    }

}
