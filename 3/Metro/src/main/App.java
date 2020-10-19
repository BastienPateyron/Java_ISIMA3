package main;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Metro Stop
        MetroStop monArret = new MetroStop(1975, 2.33871281165883, 48.8844176451841, "Abbesses", "PARIS-18EME", "metro");
        // System.out.println(monArret.toString());
        
        // StopList
        StopList maStopList = new StopList();
        // maStopList.add(monArret);
        // maStopList.add(monArret);

        // System.out.println(maStopList);
        
        // Parser
        Parser monParser = new Parser();
        // monParser.openFile("Metro/ratp_arret.csv");
        // MetroStop premierParsed = monParser.parseLine();
        // MetroStop secondParsed = monParser.parseLine();
        maStopList.loadFile("Metro/ratp_arret.csv");
        maStopList.sortByArrondissementThenNom();
        
        System.out.println(maStopList);
        // for (int i = 0; i < 100; i++) {
        //     System.out.println(maStopList.getStopList().get(i));
        // }
        
        // System.out.println(monParser.getFilename() + " " + monParser.getFileReader() + " " + monParser.getBufferedReader());
        
        // monParser.openFile("Metro/ratp_empty.csv");
        // System.out.println(monParser.parseFile());
    }
}
