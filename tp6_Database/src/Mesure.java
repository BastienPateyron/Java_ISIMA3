/**
 * Classe pour parser la réponse de l'API
 */
public class Mesure {

   public String name;
   public Main main;

   /**
    * Sous classe pour parser la rubrique 'main' de la réponse de l'API
    */
   public class Main {

      public double temp;
      public double temp_max;
      public double temp_min;
      public double humidity;
      public String name;


      @Override
      public String toString() {
         return "humidity=" + humidity + ", temp=" + temp + ", temp_max=" + temp_max + ", temp_min=" + temp_min;
      }
   }

   public String toString() {
      return name + ": " + main.toString();
   }
}