/**
 * Classe pour parser la réponse de l'API
 */
public class Mesure {

   public String name;
   public Main main;

   Mesure(String name, double temp, double temp_max, double temp_min, double humidity) {
      this.name = name;
      this.main = new Main(temp, temp_max, temp_min, humidity);
   }

   /**
    * Sous classe pour parser la rubrique 'main' de la réponse de l'API
    */
   public class Main {

      public double temp;
      public double temp_max;
      public double temp_min;
      public double humidity;


      Main(double temp, double temp_max, double temp_min, double humidity) {
         this.temp = temp;
         this.temp_max = temp_max;
         this.temp_min = temp_min;
         this.humidity = humidity;
      }

      @Override
      public String toString() {
         return "humidity=" + humidity + ", temp=" + temp + ", temp_max=" + temp_max + ", temp_min=" + temp_min;
      }
   }

   public String toString() {
      return name + ": " + main.toString();
   }
}