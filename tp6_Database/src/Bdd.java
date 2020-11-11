import java.sql.*;
import java.util.ArrayList;

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
         Statement st = connection.createStatement(); 
         st.execute(initQuery);
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   /**
    * Insert la mesure passée en argument dans la bdd
    */
   public void insertMesure(Mesure maMesure) {
      StringBuilder query = new StringBuilder();
      query.append("INSERT INTO Mesures (ville, humidity, temp, temp_max, temp_min) VALUES(\"");
      query.append(maMesure.name + "\", ");
      query.append(maMesure.main.humidity + ", ");
      query.append(maMesure.main.temp + ", ");
      query.append(maMesure.main.temp_max + ", ");
      query.append(maMesure.main.temp_min + ");");

      try {
         Statement st = connection.createStatement();
         int nb = st.executeUpdate(query.toString());
         System.out.println(nb + " ligne insérée.");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * Récupère l'ensemble des mesures présentes en base
    * @return Collection d'objets Mesures parsés depuis le résultat de la requête
    */
   public ArrayList<Mesure> getAllMesures() {
      ArrayList<Mesure> mesureList = new ArrayList<>();
      String query = "SELECT * FROM Mesures ORDER BY ville, temp;";

      try {
         Statement st = connection.createStatement();
         ResultSet rs = st.executeQuery(query);
         while (rs.next()) {
            mesureList.add(new Mesure(
               rs.getString(2),
               rs.getDouble(4),
               rs.getDouble(5),
               rs.getDouble(6),
               rs.getDouble(3)
            ));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return mesureList;
   }
}