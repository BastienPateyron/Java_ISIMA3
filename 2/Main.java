import java.io.*;

public class Main {
   public static void main(String[] args) {
      System.out.println("---- super.librairie() ----");
      Librairie librairie = new Librairie();

      librairie.afficher();
      
      librairie.ajouter("Harry Potter", "Fantastique", 15.0, 19970627, 9.0);
      librairie.ajouter("MISC N°203", "Informatique", 9.5, 20200805, 7.5, "Mensuel");
      librairie.ajouter("Davinci Code", "Thriller", 12.0, 20200805, 8.5);
      librairie.ajouter("MISC N°204", "Informatique", 9.5, 20200805, 6.0, "Mensuel");

      librairie.afficher();


   }
}