package main;

public class Mesure {

   private Main main;

   private class Main {

      private double temp;
      private double temp_max;
      private double temp_min;
      private double humidity;

      @Override
      public String toString() {
         return "humidity=" + humidity + ", temp=" + temp + ", temp_max=" + temp_max + ", temp_min=" + temp_min;
      }
   }

   public String toString() {
      return main.toString();
   }
}