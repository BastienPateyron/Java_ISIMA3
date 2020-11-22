import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class EtudiantTest {
   
   private int iterations = 10;

   @Test
   public void testSingleThread() {

      double avg = 0;

      for(int i = 0; i < iterations; i++) {
         Instant debut = Instant.now();
         ArrayList<Etudiant> etudiants = new ArrayList<>();
   
         CreationEtudiant c1 = new CreationEtudiant(150000, etudiants);
   
         try {
            c1.monThread.join();
   
         } catch (Exception e) {
            e.printStackTrace();
         }
   
         Instant fin = Instant.now();
         
         long duree = Duration.between(debut, fin).toMillis(); 

         avg += duree;
      }

      avg = (double) (avg / iterations) / 1000;
      System.out.println("SingleThread average time:\t" + avg + "s");
   }
   
   @Test
   public void testMultiThread() {
      ArrayList<Etudiant> etudiants = new ArrayList<>();

      double avg = 0;

      for(int i = 0; i < iterations; i++) {
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
      long duree = Duration.between(debut, fin).toMillis(); 
      avg += duree;
      }

      avg = (double) (avg / iterations) / 1000;
      System.out.println("MultiThread average time:\t" + avg + "s");
   }
   

        
}
