package main;

import java.sql.*;
import java.io.*;

public class Database {
   
   public Connection connection;

   public Database() {
      connect();
      initDatabase();
   }

   private void connect() {
      try {
         // Class.forName("com.sqlite.jdbc.Driver");
         Class.forName("org.sqlite.JDBC");
         connection = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
      } catch (Exception e) {
         e.printStackTrace ();
      }
   }

   private void initDatabase() {

   }
}