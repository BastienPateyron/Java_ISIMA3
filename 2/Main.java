import java.io.*;

public class Main {
   public static void main(String[] args) {
      System.out.println("---- super.librairie() ----");
      Librairie librairie = new Librairie();

      librairie.afficher();
      
      librairie.ajouter("Harry Potter", "Fantastique", 15.0, 19970627, 9.0);
      librairie.ajouter("MISC N°203", "Informatique", 9.5, 20200805, 7.5, "Mensuel");

      librairie.afficher();


   }
}