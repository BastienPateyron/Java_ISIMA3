public class Livre {

   private String    titre;
   private String    categorie;
   private Double     prix;
   private Integer   dateParution;
   private Double     note;

   public Livre() {}
   public Livre(String titre, String categorie, Double prix, Integer dateParution, Double note) {
         this.titre = titre;
         this.categorie = categorie;
         this.prix = prix;
         this.dateParution = dateParution;
         this.note = note;
   }

   @Override
   public String toString() {
      
      String format = "%1$-25s%2$-20s%3$-8s%4$-25s%5$-8s";
      // System.out.format(format, "A", "AA", "AAA");

      return (
         String.format(format, titre, categorie, prix, dateParution, note)
         // titre          + "\t\t" +
         // categorie      + "\t\t" +
         // prix           + "\t\t" +
         // dateParution   + "\t\t" +
         // note      
      );
   }
}