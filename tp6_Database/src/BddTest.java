import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BddTest {

   private String mockDbFileName = "mockDatabase.sqlite";
   private String originalFileName = "databaseBackup.sqlite";
   private Bdd db;

   @BeforeEach
   public void setUp() {
      
      Path original = Paths.get(originalFileName);
      Path copy = Paths.get(mockDbFileName);
      try {
         // Copy the backup file to a mock database file
         Files.copy(original, copy, StandardCopyOption.REPLACE_EXISTING);   
      } catch (Exception e) {
         e.printStackTrace();
         assert(false);
      }

      db = new Bdd(mockDbFileName);    // Open the mock database
   }

   @AfterEach
   public void tearDown() {
      
      db.close();                      // Close the database
      db = null;                       // Set the database object to null
      
      Path fileToDelete = Paths.get(mockDbFileName);
      try {
         Files.delete(fileToDelete);   // Remove the copy
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   
   @Test
   public void testConnect() {
      // TODO: Test creation
      // TODO: Test initialisation
   }
   
   @Test
   public void testInitDatabase() {
      
   }
   
   @Test
   public void testInsertMesure() {
      
   }

   @Test
   public void testGetAllMesures() {

   }

   @Test
   public void testCleanDatabase() {

   }



}