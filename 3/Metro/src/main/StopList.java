package main;

import java.util.ArrayList;

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
         };
   
         return resultat.toString();
      }

   // Charge dans l'attribut 'stopList' les arrêts présents dans le fichier fourni
   public void loadFile(String filename) {

   }


}
