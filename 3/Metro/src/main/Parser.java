package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {

   private String filename;
   private FileReader fileReader;
   private BufferedReader bufferedReader;

   
   public void openFile(String filename) throws FileNotFoundException {
      this.filename = filename;
      fileReader = new FileReader(filename);
      bufferedReader = new BufferedReader(fileReader);
   }

   public String readLine() {
      String line = "";
      
      try {
         line = bufferedReader.readLine();
         System.out.println(line);
      } catch (IOException e) {
         // Passer à la ligne suivante
         System.out.println(e.getMessage());
         line = "Error";
      }

      return line;
   }

   public MetroStop parseLine() {
      MetroStop metroStop = null;
      String line = readLine();

      // TODO Parsing
      String[] properties = line.split("#");
      for(String s : properties) {
         System.out.println(s.toString());
      }

      metroStop = new MetroStop(
         Integer.parseInt(properties[0]),
         Double.parseDouble(properties[1]),
         Double.parseDouble(properties[2]),
         properties[3],
         properties[4],
         properties[5]
      );

      return metroStop;
   }

   public ArrayList<MetroStop> parseFile(String filename) {

      ArrayList<MetroStop> stopList = new ArrayList<>();
      
      // TODO

      return stopList;
   }

   public String getFilename() {
      return filename;
   }

   public void setFilename(String filename) {
      this.filename = filename;
   }

}