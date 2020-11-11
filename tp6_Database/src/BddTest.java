import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BddTest {

   // static private String filename = "response.json";
   // static private File responseMockingFile = null;
   // static private FileInputStream mockFileInputStream = null;
   // private Meteo maMeteo = null;
   // final Gson gson = new GsonBuilder().setLenient().create();

   @BeforeEach
   public void setUp() {
      // try {
      //    responseMockingFile = new File(filename);
      //    mockFileInputStream = new FileInputStream(responseMockingFile);
      //    System.out.println(mockFileInputStream);
      //    maMeteo = new Meteo();
      // } catch (IOException e) {
      //    System.out.println(e.getMessage());
      //    assert(false);
      // }
   }

   @AfterEach
   public void tearDown() {
      // try {
      //    mockFileInputStream.close();
      //    maMeteo = null;
      // } catch (IOException e) {
      //    System.out.println(e.getMessage());
      //    assert(false);
      // }
   }

   @Test
   public void testOpenDb() {
      // TODO: Test creation
      // TODO: Test initialisation
   }



}