package edu.easysoft;

import edu.easysoft.controller.ArgsChecker;
import edu.easysoft.controller.Initiator;

public class Main {

    public static  void main(String[] args) {
        Initiator.startBusiness(ArgsChecker.provideNamePattern(args));
    }
}
