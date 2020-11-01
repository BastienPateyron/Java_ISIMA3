package test;




import main.MetroStop;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MetroStopTest {


   private MetroStop monArret;

   @BeforeEach
   public void setUp() {
      monArret = new MetroStop(1975, 2.33871281165883, 48.8844176451841, "Abbesses", "PARIS-18EME", "metro");
   }

   @AfterEach
   public void tearDown() {
      monArret = null;
   }


   @Test
   public void testAccesseurs() {
      assertEquals(1975, monArret.getId());
      assertEquals(2.33871281165883, monArret.getLongitude(), 0.0000000000001);
      assertEquals(48.8844176451841, monArret.getLatitude(), 0.0000000000001);
      assertEquals("Abbesses", monArret.getNom());
      assertEquals("PARIS-18EME", monArret.getArrondissement());
      assertEquals("metro", monArret.getType());
   }

   @Test
   public void testEquals() {
      MetroStop monArret_2 = new MetroStop(1975, 2.33871281165883, 48.8844176451841, "Abbesses", "PARIS-18EME", "metro");
      assertEquals(monArret, monArret_2);
      assertTrue(monArret.equals(monArret_2)); // Probablement équivalent
   }

   @Test
   public void testToString() {
      String affichage = "1975\tAbbesses\tPARIS-18EME\tmetro\t2.33871281165883\t48.8844176451841";
      assertEquals(monArret.toString(), affichage);
   }
}