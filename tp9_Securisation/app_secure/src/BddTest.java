import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.sqlite.SQLiteException;

public class BddTest {

   private String originalFileName = "databaseBackup.sqlite";
   private String mockDbFileName = "mockDatabase.sqlite";
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
      String content = "Arrondissement de Clermont-Ferrand: humidity=71.0, temp=10.0, temp_max=12.0, temp_min=8.0";
      String query = "SELECT * FROM Mesures WHERE ID = (SELECT MAX(ID) FROM Mesures);";
      String insert = "INSERT INTO Mesures (ville, humidity, temp, temp_max, temp_min, date) VALUES(\"Arrondissement de Clermont-Ferrand\", 71.0, 10.0, 12.0, 8.0, STRFTIME('%s'));";
      Mesure mesureLue = null;
      
      try {
         Statement st = db.connection.createStatement();
         st.executeUpdate(insert);
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
   @Disabled
   /**
    * Not working Yet. 
    * The database test file is not properly recreated between each test
    */
   public void testGetAllMesures() {
      ArrayList<Mesure> mesureList = new ArrayList<>();
      mesureList.add(new Mesure("Paris", 10.0, 12.0, 8.0, 71.0));
      mesureList.add(new Mesure("Paris", 5.0, 10.0, 3.0, 71.0));
      mesureList.add(new Mesure("Marseille", -1.0, 3.0, -3.0, 71.0));
      mesureList.add(new Mesure("Marseille", 28.0, 31.0, 13.0, 71.0));
      mesureList.add(new Mesure("Lyon", 15.0, 20.0, 8.0, 71.0));
      mesureList.add(new Mesure("Paris", 20.0, 25.0, 8.0, 71.0));
      mesureList.add(new Mesure("Marseille", 19.0, 22.0, 9.0, 71.0));
      mesureList.add(new Mesure("Lyon", 1.0, 12.0, -3.0, 71.0));
      
      ArrayList<Mesure> sortedList = new ArrayList<>();
      sortedList.add(new Mesure("Lyon", 15.0, 20.0, 8.0, 71.0));
      sortedList.add(new Mesure("Lyon", 1.0, 12.0, -3.0, 71.0));
      sortedList.add(new Mesure("Marseille", 28.0, 31.0, 13.0, 71.0));
      sortedList.add(new Mesure("Marseille", 19.0, 22.0, 9.0, 71.0));
      sortedList.add(new Mesure("Marseille", -1.0, 3.0, -3.0, 71.0));
      sortedList.add(new Mesure("Paris", 20.0, 25.0, 8.0, 71.0));
      sortedList.add(new Mesure("Paris", 10.0, 12.0, 8.0, 71.0));
      sortedList.add(new Mesure("Paris", 5.0, 10.0, 3.0, 71.0));
      
      // Loop insertion
      for(Mesure m : mesureList) db.insertMesure(m);
      
      ArrayList<Mesure> retrievedList = db.getAllMesures();
      
      System.out.println("Measure Sorted: " + sortedList.size() + "\tMeasure Retrieved: " + retrievedList.size()); 
      for(int i = 0; i < retrievedList.size(); i++){
         System.out.println(i + " " + sortedList.get(i).toString());
         System.out.println(i + " " + retrievedList.get(i).toString());
         assert(sortedList.get(i).toString().equals(retrievedList.get(i).toString()));
      }
   }
   
   @Test
   @Disabled
   /**
    * Not working Yet. 
    * The database test file is not properly recreated between each test
    */
   public void testCleanDatabase() {
      try {
         db = new Bdd(mockDbFileName, false); // Cette base doit déjà contenir des données (périmées)
      } catch (Exception e) {
         System.out.println(e.getMessage());
         assert(false);
      }

      String queryInitialDb = "SELECT * FROM mesures GROUP BY id;";
      try {
         Statement st = db.connection.createStatement();
         ResultSet rs = st.executeQuery(queryInitialDb);
         rs.next();
         int size = 0;
         rs.getInt(size);
         assert(10 == size);
      } catch (Exception e) {
         e.printStackTrace();
         assert(false);
      }

      // Insérer nouvelle ligne à l'instant qui sera donc valide au moment de l'affichage
      Mesure maMesure = new Mesure("Clermont-Ferrand", 9.0, 13.0, 5.0, 71.0);
      db.insertMesure(maMesure);
      db.cleanDatabase(); // Nettoie la bdd

      // Vérifier que c'est la seule de la bdd
      try {
         Statement st = db.connection.createStatement();
         ResultSet rs = st.executeQuery(queryInitialDb);
         rs.next();
         int size = 0;
         rs.getInt(size);
         assert(1 == size);
         String queryCleanedCheck = "SELECT * FROM Mesures WHERE ID = (SELECT MAX(ID) FROM Mesures);";
         rs = st.executeQuery(queryCleanedCheck);
         Mesure mesureRestante = db.readMesure(rs);
         assert(maMesure.toString().equals(mesureRestante.toString()));
      } catch (Exception e) {
         e.printStackTrace();
         assert(false);
      }
   }



}