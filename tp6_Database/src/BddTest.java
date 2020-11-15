import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.sql.Statement;

import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sqlite.SQLiteException;

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
      Bdd testDb = new Bdd(mockDbFileName, true);
      assert(mockDbFileName.equals(testDb.dbName));

      testDb.connect(mockDbFileName);
      assertNotNull(testDb.connection);
   }
   
   @Test
   public void testInitDatabase() {
      String resetQuery = "DROP TABLE IF EXISTS Mesures;";
      String testTableExistsQuery = "SELECT * FROM Mesures;";
      Statement st;

      // Vide la base
      try {
         st = db.connection.createStatement();
         st.executeUpdate(resetQuery);
      } catch (SQLException e) {
         System.out.println(e);
         assert(false);
      }
      
      // Vérifie que la base est bien vide
      try {
         st = db.connection.createStatement();
         st.executeQuery(testTableExistsQuery);
         assert(false); // Ne doit pas arriver jusqu'ici
      } catch (SQLException e) {
         System.out.println(e);
         assert(e instanceof SQLiteException);
         assert(e.getMessage().contains("no such table: Mesures"));
      } 
      
      db.initDatabase(db.dbName);
      
      try {
         st = db.connection.createStatement();
         st.executeQuery(testTableExistsQuery);
         assert(true);     // La table a bien été créee
      } catch (SQLException e) {
         System.out.println(e);
         assert(false);
      }

      
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