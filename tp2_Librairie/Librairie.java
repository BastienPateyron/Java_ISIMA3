import java.util.ArrayList;
import java.util.Collections;

public class Librairie {
   // Contient la liste des livres

   // Attributs
   private ArrayList<Livre> livres = new ArrayList<>();

   // Méthodes
   public void ajouter(Livre livre) {
      livres.add(livre);
   }

   public void ajouter(String titre, String categorie, Double prix, Integer dateParution, Double note) {
      ajouter(new Livre(titre, categorie, prix, dateParution, note));
   }

   public void ajouter(String titre, String categorie, Double prix, Integer dateParution, Double note, String periodicite) {
      ajouter(new Magazine(titre, categorie, prix, dateParution, note, periodicite));
   }

   public void afficher() {
      // Afficher entête
      String format = "%1$-25s%2$-20s%3$-8s%4$-25s%5$-8s%6$-12s\n";
      System.out.format(format, "Titre", "Categorie", "Prix", "Date de parution", "Note", "Periodicite");
      System.out.println("_________________________________________________________________________________________________");
      
      // Affichage des livres
      for (Livre livre : livres) {
         System.out.println(livre);
      }

      // Saut de ligne
      System.out.println();
   }

   public <T> void afficher(Class<T> type) {
      String format = "%1$-25s%2$-20s%3$-8s%4$-25s%5$-8s%6$-12s\n";
      System.out.format(format, "Titre", "Categorie", "Prix", "Date de parution", "Note", "Periodicite");
      System.out.println("_________________________________________________________________________________________________");
      
      for (Livre livre : livres) {
         // if ( !(livre instanceof t) ) { System.out.println("Livre");}
         if (livre.getClass().equals(type)) {
             System.out.println(livre);
         }
      }

      // Saut de ligne
      System.out.println();
   }

   
   // Tri par titre
   public void trier() {
      // Collection.sort(livres);                                          // Fonctionne avec la méthode Livre.compareTo()
      // Collections.sort(livres, Livre.Comparators.TITRE);                // Fonctionne avec les Comparateurs définis dans Livre
      livres.sort( (l1, l2) -> l1.getTitre().compareTo(l2.getTitre()) );   // Utilise une Lambda
   }

   // Tri par date
   public void trier(String flag) {
      // Collections.sort(livres, Livre.Comparators.DATEPARUTION);
      livres.sort( (l1, l2) -> l2.getDateParution() - l1.getDateParution()); 
   }
}
