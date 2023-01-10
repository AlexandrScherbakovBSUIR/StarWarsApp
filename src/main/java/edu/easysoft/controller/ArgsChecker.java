package edu.easysoft.controller;

public class ArgsChecker {

    public static String provideNamePattern(String[] args){
        return checkArgs(args);
    }

    private static String checkArgs(String[] args) {
        String namePattern = "";
        if( args.length > 0 &&
                args[0] != null &&
                args[0].matches("[a-zA-Z]+")){

            namePattern = args[0];
            System.out.println("namePattern: " + namePattern);
        } else {
            namePattern = "L";
            System.out.println("namePattern: " + namePattern);
        }
        return namePattern;
    }
}

