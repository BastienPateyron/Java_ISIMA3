package main;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Metro Stop
        MetroStop monArret = new MetroStop(1975, 2.33871281165883, 48.8844176451841, "Abbesses", "PARIS-18EME", "metro");
        System.out.println(monArret.toString());
        
        // StopList
        StopList maStopList = new StopList();
        maStopList.add(monArret);
        maStopList.add(monArret);

        System.out.println(maStopList);

        // Parser
        Parser monParser = new Parser();
        // monParser.openFile("missingfile");
        monParser.openFile("Metro/ratp_arret.csv");
        MetroStop premierParsed = monParser.parseLine();
        MetroStop secondParsed = monParser.parseLine();

    }
}
