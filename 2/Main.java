import java.io.*;

public class Main {
   public static void main(String[] args) {
      Librairie librairie = new Librairie();
      
      librairie.ajouter("Harry Potter", "Fantastique", 15.0, 19970627, 9.0);
      librairie.ajouter("MISC N°203", "Informatique", 9.5, 20200805, 7.5, "Mensuel");
      librairie.ajouter("MISC N°205", "Informatique", 9.5, 20201005, 6.0, "Mensuel");
      librairie.ajouter("Davinci Code", "Thriller", 12.0, 20001103, 8.5);
      librairie.ajouter("MISC N°204", "Informatique", 9.5, 20200905, 6.0, "Mensuel");
      librairie.ajouter("MISC N°99", "Informatique", 5.5, 20180905, 6.0, "Mensuel");
      librairie.ajouter("Zébulon en sauce", "Cuisine", 7.0, 20190821, 8.0);

      librairie.afficher();

      while(true) {
         menu(librairie);
      }


      
   }

   public static void menu(Librairie librairie) {
      System.out.println("------------- Menu Principal -------------");
      System.out.println("1. afficher tous les livres");
      System.out.println("2. afficher les livres uniquement");
      System.out.println("3. afficher les magazines uniquement");
      System.out.println("4. trier par nom");
      System.out.println("5. trier par date\n");
      System.out.println("Saisissez le numéro de votre choix: ");
      System.out.print("> ");

      int choix = 0;
      int saisie = 0;
      
      try {
         choix = Integer.parseInt(System.console().readLine());
         System.out.println(choix);

         switch (choix) {
            case 1: librairie.afficher(); break;
            case 2: librairie.afficher(Livre.class); break;
            case 3: librairie.afficher(Magazine.class); break;
            case 4: librairie.trier(); librairie.afficher(); break;
            case 5: librairie.trier("Date"); librairie.afficher(); break;
            // case 1: librairie.afficher(); break;
            default: break;
         }
      } catch (Exception e) {
         System.out.println("Error " + e.getStackTrace());
      }
   }
}