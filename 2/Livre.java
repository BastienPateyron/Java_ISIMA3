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
      return (
         titre          + "\t" +
         categorie      + "\t" +
         prix           + "\t" +
         dateParution   + "\t" +
         note      
      );
   }
}