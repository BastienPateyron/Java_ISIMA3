package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.JUnitException;

import main.MetroStop;
import main.Parser;

public class ParserTest {

   Parser monParser;
   String ratp_file = "Metro/ratp_arret.csv";
   String empty_file = "Metro/ratp_empty.csv";
   String badformat_file = "Metro/ratp_badformat.csv";
   String notfound_file = "Metro/ratp_notfound.csv";

   @BeforeEach
   public void setUp() {
      monParser = new Parser();
      try {
         monParser.openFile(ratp_file);
      } catch (Exception e) {
         throw new JUnitException(e.getMessage());
      }
   }

   @AfterEach
   public void tearDown() {
      monParser = null;
   }

   @Test
   public void testOpenFileNotFound() {
      assertThrows(FileNotFoundException.class, () -> monParser.openFile(notfound_file));
   }

   @Test
   public void testOpenFileCorrect() {
      assertEquals(monParser.getFilename(), ratp_file);
      assertNotNull(monParser.getBufferedReader());
      assertNotNull(monParser.getFileReader());
   }

   @Test
   public void testOpenFileEmpty() {
      try {
         monParser.openFile(empty_file);
         ArrayList<MetroStop> a = new ArrayList<>();
         assertEquals(a.toString(), monParser.parseFile().toString());
      } catch (Exception e) {
         throw new JUnitException(e.getMessage());
      }
      
   }

   @Test
   public void testReadLine() {
      String firstLine = "1975#2.33871281165883#48.8844176451841#Abbesses#PARIS-18EME#metro";
      assertEquals(firstLine, monParser.readLine());
      
      String secondLine = "1981#2.32674567371924#48.828398514348#Alésia#PARIS-14EME#metro";
      assertEquals(secondLine, monParser.readLine());
   }

   @Test
   public void testParseLine() {
      MetroStop premierArret = new MetroStop(1975, 2.33871281165883, 48.8844176451841, "Abbesses", "PARIS-18EME", "metro");
      MetroStop premierParsed = monParser.parseLine();
      assertEquals(premierParsed, premierArret);
      
      MetroStop secondArret = new MetroStop(1981, 2.32674567371924, 48.828398514348, "Alésia", "PARIS-14EME", "metro");
      MetroStop secondParsed = monParser.parseLine();
      assertEquals(secondParsed, secondArret);

      // TODO: Tester une ligne mal formatée
   }

   @Test
   public void testParseFile() {
      ArrayList<MetroStop> metroStopList = monParser.parseFile();

      // Tester le nombre de lignes
      assertEquals(12012, metroStopList.size());
      
      // Tester le premier élément
      MetroStop premierArret = new MetroStop(1975, 2.33871281165883, 48.8844176451841, "Abbesses", "PARIS-18EME", "metro");
      MetroStop premierParsed = metroStopList.get(0);
      assertEquals(premierParsed, premierArret);
      
      // Tester le dernier élément
      MetroStop dernierArret = new MetroStop(445422, 2.23014400439772, 48.9137080056912, "VICTOR BASCH", "COLOMBES", "tram");
      MetroStop dernierParsed = metroStopList.get(metroStopList.size() -1);
      assertEquals(dernierParsed, dernierArret);

      // Tester la réinitialisation des variables
      assertNull(monParser.getBufferedReader());
      assertNull(monParser.getFileReader());
      assertNull(monParser.getFilename());
   }

}
