package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.JUnitException;

import main.Parser;
import main.MetroStop;

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
   }

   @Test
   @Disabled
   public void testOpenFileEmpty() {
      try {
         monParser.openFile(empty_file);
         // TODO: Assertion sur ... ?

      } catch (Exception e) {
         throw new JUnitException(e.getMessage());
      }
      
   }

   @Test
   @Disabled
   public void testOpenFileBadFormat() {


   
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

      // Tester une ligne mal formatée
   }

   @Test
   public void testParseFile() {
      // Tester le nombre de lignes

      // Tester les erreurs de formatage
   }

}
