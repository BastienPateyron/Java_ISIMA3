package main;

public class App {
    public static void main(String[] args) throws Exception {

        StopList stopList = new StopList();       // Liste des arrêts de métro chargés en mémoire
        stopList.loadFile("Metro/ratp_arret.csv");

        while(true) {
            menu(stopList);
        }


        /* Le code ci-dessous est juste présent à titre d'exemple */
        
        // // Metro Stop
        // MetroStop monArret = new MetroStop(1975, 2.33871281165883, 48.8844176451841, "Abbesses", "PARIS-18EME", "metro");
        // System.out.println(monArret.toString());
        
        // // StopList
        // StopList maStopList = new StopList();
        // maStopList.add(monArret);
        // maStopList.add(monArret);
        // System.out.println(maStopList);
        
        // // Parser
        // Parser monParser = new Parser();
        // monParser.openFile("Metro/ratp_arret.csv");
        // MetroStop premierParsed = monParser.parseLine();
        // MetroStop secondParsed = monParser.parseLine();
        // maStopList.loadFile("Metro/ratp_arret.csv");
        // maStopList.sortByArrondissementThenNom();
        
        // System.out.println(maStopList);

        // for (int i = 0; i < 100; i++) {
        //     System.out.println(maStopList.getStopList().get(i));
        // }
        
        // System.out.println(monParser.getFilename() + " " + monParser.getFileReader() + " " + monParser.getBufferedReader());
        
        // monParser.openFile("Metro/ratp_empty.csv");
        // System.out.println(monParser.parseFile());
    }

    public static void menu(StopList stopList) {
        System.out.println("------------- Menu Principal -------------");
        System.out.println("1. afficher la liste de tous les arrêts");
        System.out.println("2. afficher la liste des 100 premiers arrêts");
        System.out.println("3. parser un fichier");
        System.out.println("4. trier par ID");
        System.out.println("5. trier par arrondissement puis par nom");
        System.out.println("Saisissez le numéro de votre choix: ");
        System.out.print("> ");
  
        int choix = 0;
        
        try {
           choix = Integer.parseInt(System.console().readLine());
           System.out.println(choix);
  
           switch (choix) {
              case 1: System.out.println(stopList); break;
              case 2: for (int i = 0; i < 100 && i < stopList.size(); i++) System.out.println(stopList.getStopList().get(i)); break;
              case 3: stopList.loadFile(saisirNomFichier()); break;
              case 4: stopList.sortById(); break;
              case 5: stopList.sortByArrondissementThenNom(); break;
              default: break;
           }
        } catch (Exception e) {
           System.out.println("Error " + e.getStackTrace());
        }
     }

    public static String saisirNomFichier() {
        System.out.println("Saisissez le nom du fichier (peut-être le préfixer de 'Metro/' selon son emplacement ou bien s'il est à la racine du dossier 'Metro'.");
        System.out.print("> ");
        return System.console().readLine();
    }
}
