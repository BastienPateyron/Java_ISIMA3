public class Magazine extends Livre {
   
   private String periodicite;

   public Magazine(String titre, String categorie, Double prix, Integer dateParution, Double note, String periodicite) {
      super(titre, categorie, prix, dateParution, note);
      this.periodicite = periodicite;
   }

   @Override
   public String toString() {
      return super.toString() + "\t" + periodicite;
   }

}
