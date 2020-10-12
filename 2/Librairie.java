import java.util.ArrayList;

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
      System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
      
      // Affichage des livres
      for (Livre livre : livres) {
         System.out.println(livre);
      }

      // Saut de ligne
      System.out.println();
   }

   public void trier() {
      // TODO
   }

}
