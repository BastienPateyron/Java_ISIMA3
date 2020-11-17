import java.time.LocalDate;
import java.util.Random;

public class Etudiant {
   public String ine;
   public String nom;
   public String prenom;
   public LocalDate anniversaire;
   public String lieuDeNaissance;
   
   public Etudiant(String ine, String nom, String prenom, LocalDate anniversaire, String lieuDeNaissance) {
      this.ine = ine;
      this.nom = nom;
      this.prenom = prenom;
      this.anniversaire = anniversaire;
      this.lieuDeNaissance = lieuDeNaissance;
   }

   public Etudiant(Etudiant e) {
      this.ine = e.ine;
      this.nom = e.nom;
      this.prenom = e.prenom;
      this.anniversaire = e.anniversaire;
      this.lieuDeNaissance = e.lieuDeNaissance;
   }

   public Etudiant() {
      this(randomStudent());
   }

   public static int rangedInt(int min, int max) {
      Random random = new Random(); // 1 ?
      return random.nextInt(max - min) + min;
   }

   public static String randomIne() {
      StringBuilder ine = new StringBuilder();
      for(int i = 0; i < 10; i++) ine.append(rangedInt(0, 9));
      ine.append(alphabet.charAt(rangedInt(0, 25)));
      return ine.toString();
   }

   /**
    * Génère un étudiant aléatoire
    */
   public static Etudiant randomStudent() {
      String ine = randomIne();
      String nom = noms[rangedInt(0, noms.length)];
      String prenom = prenoms[rangedInt(0, prenoms.length)];
      LocalDate anniversaire = LocalDate.of(rangedInt(1994, 2002), rangedInt(1, 13), rangedInt(1, 28));
      String lieuDeNaissance = villes[rangedInt(0, villes.length)];
      return new Etudiant(ine, nom, prenom, anniversaire, lieuDeNaissance);
   }

   private static String[] noms     = {"Doe", "Dawson", "Kent", "Byers", "Clinton", "Dalton", "Crawford", "Oxford", "Stark", "Kennedy", "Trump", "Obama", "Biden"};
   private static String[] prenoms  = {"Joe", "Jack", "Jessy", "Jessie", "Kelly", "Molly", "Cindy", "Paul", "Lucas", "Agatha", "Jessica", "Chris", "Stan", "Bill"};
   private static String[] villes   = {"Lourdes", "Tourcoing", "Paris", "Marseille", "Lyon", "Guéret", "Clermont-Ferrand", "Lille", "Bayonne", "Bruges", "Annecy"};
   private static String   alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

   @Override
   public String toString() {
      return "Etudiant [ine=" + ine + ", nom=" + nom + ", prenom=" + prenom + ", anniversaire=" + anniversaire + ", lieuDeNaissance=" + lieuDeNaissance + "]";
   }
}
