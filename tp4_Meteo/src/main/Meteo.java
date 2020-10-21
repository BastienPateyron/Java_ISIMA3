package main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Meteo {

   public String requete(String ville) {

      String response = "";

      HttpURLConnection urlConnection = null;
      try {
         URL url = new URL(
               "http://api.openweathermap.org/data/2.5/weather?q=" + 
               ville +
               "&units=metric&appid=fdec5664d19caf22895d3aaf207d3d43");
         urlConnection = (HttpURLConnection) url.openConnection();
         // InputStream in = new BufferedInputStream(urlConnection.getInputStream());
         response = readStream(urlConnection.getInputStream());
      } catch (IOException e) {
         System.out.println("Erreur dans la requete: " + e.getMessage());
      } finally {
         if (urlConnection != null) {
            urlConnection.disconnect();
         }
      }
      return response;
   }

   public String requete() {
      return requete("Clermont-Ferrand");
   }

   private String readStream(InputStream inputStream) {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

      String line = null;
      StringBuilder text = new StringBuilder();

      
      try {
         while ((line = bufferedReader.readLine()) != null) {
            text.append(line);
            // System.out.println(line);
         }
   
         bufferedReader.close();
      } catch (IOException e) {
         System.out.println(e.getMessage());
      }

      return text.toString();
   }
}
