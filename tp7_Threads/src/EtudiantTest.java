import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class EtudiantTest {
   
   private int nombreEtudiants = 150000;

   @Test
   public void testSingleThread() {
      Instant debut = Instant.now();
      ArrayList<Etudiant> etudiants = new ArrayList<>();

      CreationEtudiant c1 = new CreationEtudiant(150000, etudiants);

      try {
         c1.monThread.join();

      } catch (Exception e) {
         e.printStackTrace();
      }

      Instant fin = Instant.now();
      
      // for(Etudiant e : etudiants) System.out.println(e);

      long duree = Duration.between(debut, fin).toMillis(); 
      System.out.println("SingleThread: " + (double) duree / 1000 + "s");
   }
   
   @Test
   public void testMultiThread() {
      ArrayList<Etudiant> etudiants = new ArrayList<>();
      
      Instant debut = Instant.now();
      
      CreationEtudiant c1 = new CreationEtudiant(50000, etudiants);
      CreationEtudiant c2 = new CreationEtudiant(50000, etudiants);
      CreationEtudiant c3 = new CreationEtudiant(50000, etudiants);

      try {
         c1.monThread.join();
         c2.monThread.join();
         c3.monThread.join();
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      Instant fin = Instant.now();
      
      // for(Etudiant e : etudiants) System.out.println(e);

      long duree = Duration.between(debut, fin).toMillis(); 
      System.out.println("MultiThread: " + (double) duree / 1000 + "s");
   }
   

        
}
