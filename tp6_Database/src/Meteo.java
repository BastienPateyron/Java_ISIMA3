import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Meteo {
   
   final Gson gson = new GsonBuilder().setLenient().create();

   /** 
    * Lance la requête HTTP vers l'API pour la ville passée en paramètre.
    * En cas d'erreur, retourne le message d'erreur rendu par l'API.
    * @param ville Nom de la ville ciblée par la requête
    * @return toString de la réponse parsée de l'API
    */
   public Mesure request(String ville) throws IOException, FileNotFoundException {

      String response = "";
      Mesure maMesure = null;

      HttpURLConnection urlConnection = null;
      URL url = new URL(
            "http://api.openweathermap.org/data/2.5/weather?q=" + 
            ville +
            "&units=metric&appid=fdec5664d19caf22895d3aaf207d3d43");
      urlConnection = (HttpURLConnection) url.openConnection();

      if (urlConnection.getResponseCode() == 404) {
         throw new FileNotFoundException(readStream(urlConnection.getErrorStream()));
      } else {
         response = readStream(urlConnection.getInputStream());
         maMesure = gson.fromJson(response, Mesure.class);
      }
      
      if (urlConnection != null) {
         urlConnection.disconnect();
      }

      return maMesure;
   }

   /**
    * Surcharge qui passe Clermont-Ferrand par défaut
    */
   public Mesure request() throws IOException, FileNotFoundException {
      return request("Clermont-Ferrand");
   }

   /**
    * Construit une String en lisant dans le flux inputStream
    * @param inputStream
    * @return toString de la chaîne construite
    */
   public String readStream(InputStream inputStream) {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

      String line = null;
      StringBuilder text = new StringBuilder();
    
      try {
         while ((line = bufferedReader.readLine()) != null) {
            text.append(line);
         }
      } catch (IOException e) {
         System.out.println(e.getMessage());
      }

      return text.toString();
   }
}
