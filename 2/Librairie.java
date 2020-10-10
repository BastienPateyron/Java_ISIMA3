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
      for (Livre livre : livres) {
         System.out.println(livre);
      }
   }

   public void trier() {
      // TODO
   }

}
