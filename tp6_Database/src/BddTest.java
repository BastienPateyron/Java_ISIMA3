import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
   public void testReadMesures() {
      String content = "Arrondissement de Clermont-Ferrand: humidity=71.0, temp=10.0, temp_max=10.0, temp_min=10.0";
      String query = "SELECT * FROM Mesures WHERE ID = (SELECT MAX(ID) FROM Mesures);";
      String insert = "INSERT INTO Mesures (ville, humidity, temp, temp_max, temp_min, date) VALUES(\"Arrondissement de Clermont-Ferrand\", 71.0, 10.0, 12.0, 8.0, STRFTIME('%s'));";
      Mesure mesureLue = null;
      
      try {
         Statement st = db.connection.createStatement();
         st.executeQuery(insert);
         ResultSet rs = st.executeQuery(query);
         
         while(rs.next()) {
            mesureLue = db.readMesure(rs);
            assert(mesureLue.toString().equals(content));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   @Test
   public void testInsertMesure() {
      Mesure mesureLue = null;
      Mesure maMesure = new Mesure("Arrondissement de Clermont-Ferrand", 10.0, 12.0, 8.0, 71.0);
      String query = "SELECT * FROM Mesures WHERE ID = (SELECT MAX(ID) FROM Mesures);";
      
      db.insertMesure(maMesure);

      try {
         Statement st = db.connection.createStatement();
         ResultSet rs = st.executeQuery(query);
         while(rs.next()) {
            mesureLue = db.readMesure(rs);
            System.out.println(maMesure);
            System.out.println(mesureLue);
            
            // Il vaudrait mieux ne pas comparer les toString mais,
            // le equals des objets ne marche pas, même redéfini.
            assert(maMesure.toString().equals(mesureLue.toString()));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   @Test
   public void testGetAllMesures() {

   }

   @Test
   public void testCleanDatabase() {

   }



}