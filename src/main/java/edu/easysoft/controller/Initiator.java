package edu.easysoft.controller;

public class Initiator {
    private static final String fileName = "./src/main/resources/output" +
            "/results.txt";
    private static final String URLSWAPI = "https://swapi.dev/api/";

    public static void startBusiness(String namePattern){
        SWAPIClient client = new SWAPIClient();
        PageProcessor pageProcessor = new PageProcessor();

        String str=pageProcessor.ProcessSWAPI(namePattern,
                URLSWAPI,client);

        FileProcessor.WritingStringToFile(str,fileName);
        System.out.println(str);

    }
}
