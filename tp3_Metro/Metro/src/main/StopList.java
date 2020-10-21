package main;

import java.util.ArrayList;
import java.util.Comparator;

public class StopList {
   /* Collection d'objets MetroStop */

   private ArrayList<MetroStop> stopList = new ArrayList<>();

   public int size() {
      return stopList.size();
   }

   public void add(MetroStop metroStop) {
      stopList.add(metroStop);
   }

   @Override
   public String toString() {
      StringBuilder resultat = new StringBuilder("");

      for (MetroStop metroStop : stopList) {
         resultat.append(metroStop.toString()).append('\n');
      }
      ;

      return resultat.toString();
   }

   // Charge dans l'attribut 'stopList' les arrêts présents dans le fichier fourni
   public void loadFile(String filename) {
      Parser monParser = new Parser();
      stopList = monParser.parseFile(filename);
   }

   public void sortById() {
      // Version avec Lambda
      // stopList.sort((MetroStop a, MetroStop b) -> ((Integer) a.getId()).compareTo(((Integer) b.getId())));
      
      // Version avec comparator
      stopList.sort(Comparator.comparing(MetroStop::getId));

   }

   public void sortByArrondissementThenNom() {
      stopList.sort(Comparator.comparing(MetroStop::getArrondissement).thenComparing(MetroStop::getNom));
   }

   public ArrayList<MetroStop> getStopList() {
      return stopList;
   }

}
