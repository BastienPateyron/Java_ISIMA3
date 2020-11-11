import java.sql.*;

public class Bdd {
   
   public Connection connection;

   public Bdd() {
      connect("database.sqlite");
      initDatabase("database.sqlite");
   }

   public Bdd(String dbName) {
      connect(dbName);
      initDatabase(dbName);
   }

   /**
    * Etablit la connexion avec la base de données
    */
   private void connect(String dbName) {
      try {
         // Class.forName("com.sqlite.jdbc.Driver");
         Class.forName("org.sqlite.JDBC");
         connection = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
      } catch (Exception e) {
         e.printStackTrace ();
      }
   }

   /**
    * Initialise la base de données en créant les tables si elles n'existent pas
    */
   private void initDatabase(String dbName) {
      // String initQuery = "CREATE DATABASE IF NOT EXISTS" + dbName.split(".")[0] + "CREATE USER 'user1' IDENTIFIED BY 'pass'; GRANT ALL on myDb.* TO 'user1'";
      String initQuery = "CREATE TABLE IF NOT EXISTS Mesures (id INTEGER PRIMARY KEY AUTOINCREMENT, ville TEXT, humidity REAL, temp REAL, temp_max REAL, temp_min REAL);";
      
      try {
         Statement stmt = connection.createStatement(); 
         stmt.execute(initQuery);
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}