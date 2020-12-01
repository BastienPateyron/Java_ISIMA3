import java.sql.*;
import java.util.ArrayList;

public class Bdd {
   
   String dbName;
   public Connection connection;

   public Bdd() {
      this.dbName = "database.sqlite";
      connect(dbName);
      initDatabase(dbName);
   }

   public Bdd(String dbName) {
      this.dbName = dbName;
      connect(dbName);
      initDatabase(dbName);
   }

   /**
    * Surcharge pour tester le méthodes appelées dans les autres constructeurs
    * @param testing
    */
   public Bdd(String dbName, Boolean testing) {
      this.dbName = dbName;
   }

   /**
    * Etablit la connexion avec la base de données
    */
   public void connect(String dbName) {
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
   public void initDatabase(String dbName) {
      String initQuery = "CREATE TABLE IF NOT EXISTS Mesures (id INTEGER PRIMARY KEY AUTOINCREMENT, ville TEXT, humidity REAL, temp REAL, temp_max REAL, temp_min REAL, date INTEGER);";
      
      try {
         // Initialisation de la BDD
         Statement st = connection.createStatement(); 
         st.execute(initQuery);

         // Suppression des données périmées
         int nbCleaned = cleanDatabase();
         System.out.println(nbCleaned + " entrées supprimées.");
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   /**
    * Insert la mesure passée en argument dans la bdd
    */
   public void insertMesure(Mesure maMesure) {
      StringBuilder query = new StringBuilder();
      query.append("INSERT INTO Mesures (ville, humidity, temp, temp_max, temp_min, date) VALUES(\"");
      query.append(maMesure.name + "\", ");
      query.append(maMesure.main.humidity + ", ");
      query.append(maMesure.main.temp + ", ");
      query.append(maMesure.main.temp_max + ", ");
      query.append(maMesure.main.temp_min + ", ");
      query.append("STRFTIME('%s'));");

      try {
         Statement st = connection.createStatement();
         int nb = st.executeUpdate(query.toString());
         System.out.println(nb + " ligne insérée.");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * Crée une Mesure à partir d'un ResultSet retourné par la Bdd
    */
   public Mesure readMesure(ResultSet rs) {
      Mesure m = null;
      try {
         m = new Mesure(
         rs.getString(2),
         rs.getDouble(4),
         rs.getDouble(5),
         rs.getDouble(6),
         rs.getDouble(3));
      } catch (Exception e) {
         e.printStackTrace();
      }
      return m;
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
            mesureList.add(readMesure(rs));
         }
      } catch (Exception e) {
         // e.printStackTrace();
         System.out.println(e.getMessage());
      }

      return mesureList;
   }

   /**
    * Supprime les entrées en base qui datent de plus de 24 h.
    * @return Le nombre de lignes supprimées.
    */
   public int cleanDatabase() {
      int nbCleaned = 0;
      
      // On supprime les mesures qui datent de plus d'un jour (86400 secondes)
      String query = "DELETE FROM Mesures WHERE STRFTIME('%s') - date > 86400;";
      
      try {
         Statement st = connection.createStatement();
         nbCleaned = st.executeUpdate(query);
      } catch (Exception e) {
         e.printStackTrace();
      }

      return nbCleaned;
   }

   public void close() {
      try {
         connection.close();
         connection = null;
      } catch (Exception e) {
         // e.printStackTrace();
         System.out.println(e.getMessage());
      }
   }
}