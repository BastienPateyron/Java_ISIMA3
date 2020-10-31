package main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.annotations.SerializedName;
import com.google.gson.*;


public class Meteo {
   
   final Gson gson = new GsonBuilder().setLenient().create();

   public String requete(String ville) {

      String response = "";
      Mesure maMesure = null;

      HttpURLConnection urlConnection = null;
      try {
         URL url = new URL(
               "http://api.openweathermap.org/data/2.5/weather?q=" + 
               ville +
               "&units=metric&appid=fdec5664d19caf22895d3aaf207d3d43");
         urlConnection = (HttpURLConnection) url.openConnection();
         response = readStream(urlConnection.getInputStream());

         // System.out.println(response);
             
         maMesure = gson.fromJson(response, Mesure.class);
      } catch (IOException e) {
         System.out.println("Erreur dans la requete: " + e.getMessage());
      } finally {
         if (urlConnection != null) {
            urlConnection.disconnect();
         }
      }

      return maMesure.toString();
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
         }
         bufferedReader.close();
      } catch (IOException e) {
         System.out.println(e.getMessage());
      }

      return text.toString();
   }
}
