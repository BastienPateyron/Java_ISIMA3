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

   public Parser() {

   }
   
   public void openFile(String filename) throws FileNotFoundException {
      this.filename = filename;
      fileReader = new FileReader(filename);
      bufferedReader = new BufferedReader(fileReader);
   }

   public String readLine() {
      String line = "";
      
      try {
         line = bufferedReader.readLine();
      } catch (IOException e) {
         System.out.println(e.getMessage());
      }

      return line;
   }

   public MetroStop parseLine() {
      MetroStop metroStop;

      String line = readLine();

      if(line != null) {
         String[] properties = line.split("#");
   
         metroStop = new MetroStop(
            Integer.parseInt(properties[0]),
            Double.parseDouble(properties[1]),
            Double.parseDouble(properties[2]),
            properties[3],
            properties[4],
            properties[5]
         );
      } else {
         metroStop = null;
      }

      return metroStop;
   }

   public ArrayList<MetroStop> parseFile() {

      ArrayList<MetroStop> stopList = new ArrayList<>();
      
      MetroStop metroStop;

      while( (metroStop = parseLine()) != null) {
         stopList.add(metroStop);
      }

      // Réinitialisation des variables
      try {
         bufferedReader.close();
      } catch (IOException e) {
         System.out.println(e.getMessage());
      }
      bufferedReader = null;
      filename = null;
      fileReader = null;

      return stopList;
   }

   public ArrayList<MetroStop> parseFile(String filename) {
      try {
         openFile(filename);
      } catch (FileNotFoundException e) {
         System.out.println(e.getMessage());
      }

      return parseFile();
   }

   public String getFilename() {
      return filename;
   }

   public void setFilename(String filename) {
      this.filename = filename;
   }

   public FileReader getFileReader() {
      return fileReader;
   }

   public void setFileReader(FileReader fileReader) {
      this.fileReader = fileReader;
   }

   public BufferedReader getBufferedReader() {
      return bufferedReader;
   }

   public void setBufferedReader(BufferedReader bufferedReader) {
      this.bufferedReader = bufferedReader;
   }

}