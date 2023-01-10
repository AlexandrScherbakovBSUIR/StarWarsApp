package edu.easysoft;

import edu.easysoft.controller.ArgsChecker;
import edu.easysoft.controller.FileProcessor;
import edu.easysoft.controller.PageProcessor;
import edu.easysoft.controller.SWAPIClient;

import java.io.File;
import java.io.IOException;

public class Main {
    private static final String fileName = "./src/main/resources/output" +
            "/results.txt";
    private static final String URLSWAPI = "https://swapi.dev/api/";

    public static  void main(String[] args) {

        SWAPIClient client = new SWAPIClient();
        PageProcessor pageProcessor = new PageProcessor();

        String str=pageProcessor.ProcessSWAPI(ArgsChecker.provideNamePattern(args),
                URLSWAPI,client);

        FileProcessor.WritingStringToFile(str,fileName);
        System.out.println(str);

    }

}
