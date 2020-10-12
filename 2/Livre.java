import java.util.Comparator;

public class Livre implements Comparable<Livre> {

   private String    titre;
   private String    categorie;
   private Double    prix;
   private Integer   dateParution;
   private Double    note;

   public Livre() {}
   public Livre(String titre, String categorie, Double prix, Integer dateParution, Double note) {
         this.titre = titre;
         this.categorie = categorie;
         this.prix = prix;
         this.dateParution = dateParution;
         this.note = note;
   }

   public String getTitre() {
      return titre;
   }

   public Integer getDateParution() {
      return dateParution;
   }

   @Override
   public String toString() {
      
      String format = "%1$-25s%2$-20s%3$-8s%4$-25s%5$-8s";
      return (String.format(format, titre, categorie, prix, dateParution, note));
   }

   
   @Override
   public int compareTo(Livre livre) {
      return Comparators.TITRE.compare(this, livre);
   }

   // Méthodes de comparaison
   public static class Comparators {
      public static final Comparator<Livre> TITRE = (Livre l1, Livre l2) -> l1.titre.compareTo(l2.titre);
      public static final Comparator<Livre> DATEPARUTION = (Livre l1, Livre l2) -> l1.dateParution.compareTo(l2.dateParution);
   }
}
