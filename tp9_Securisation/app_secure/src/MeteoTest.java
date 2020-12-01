import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class MeteoTest {

   static private String filename = "response.json";
   static private File responseMockingFile = null;
   static private FileInputStream mockFileInputStream = null;
   private Meteo maMeteo = null;
   final Gson gson = new GsonBuilder().setLenient().create();

   @BeforeEach
   public void setUp() {
      try {
         responseMockingFile = new File(filename);
         mockFileInputStream = new FileInputStream(responseMockingFile);
         System.out.println(mockFileInputStream);
         maMeteo = new Meteo();
      } catch (IOException e) {
         System.out.println(e.getMessage());
         assert(false);
      }
   }

   @AfterEach
   public void tearDown() {
      try {
         mockFileInputStream.close();
         maMeteo = null;
      } catch (IOException e) {
         System.out.println(e.getMessage());
         assert(false);
      }
   }

   @Test
   public void testReadStream() {
      String readStream = maMeteo.readStream(mockFileInputStream);
      assertEquals(readStream, "{\"coord\":{\"lon\":3,\"lat\":45.67},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"base\":\"stations\",\"main\":{\"temp\":23,\"feels_like\":20.77,\"temp_min\":23,\"temp_max\":23,\"pressure\":1020,\"humidity\":35},\"visibility\":10000,\"wind\":{\"speed\":2.1,\"deg\":170},\"clouds\":{\"all\":0},\"dt\":1604154198,\"sys\":{\"type\":1,\"id\":6496,\"country\":\"FR\",\"sunrise\":1604125604,\"sunset\":1604162179},\"timezone\":3600,\"id\":3024634,\"name\":\"Arrondissement de Clermont-Ferrand\",\"cod\":200}");
   }

   @Test
   public void gsonMockTest() {
      String response = maMeteo.readStream(mockFileInputStream);
      Mesure maMesure =  gson.fromJson(response, Mesure.class);    
      assertEquals(maMesure.name, "Arrondissement de Clermont-Ferrand");
      assertEquals(maMesure.main.temp, 23);
      assertEquals(maMesure.main.temp_max, 23);
      assertEquals(maMesure.main.temp_min, 23);
      assertEquals(maMesure.main.humidity, 35);
   }

   @Test
   public void requestTest() {
      try {
         assertNotNull(maMeteo.request("Guéret"), "Should return non null object which is a successfull call");
      } catch (FileNotFoundException e) {
         e.printStackTrace();
         System.out.println("Unexpected exception occured during the test");
         assert(false);
      } catch (IOException e) {
         e.printStackTrace();
         System.out.println("Unexpected exception occured during the test");
         assert(false);
      }
      try {
         maMeteo.request("uneVilleImaginaire");
         assert(false);
      } catch (FileNotFoundException e) {
         assertEquals("{\"cod\":\"404\",\"message\":\"city not found\"}", e.getMessage(), "Should return a 404 error");
      } catch (IOException e) {
         e.printStackTrace();
         System.out.println("Unexpected exception occured during the test");
         assert(false);
      }
   }
}
