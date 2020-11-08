package Introspection;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Convertit une classe Java dans son équivalent C++ dans un fichier .cpp et
 * .hpp
 */
public class Converter {
   private Class javaClass;
   private String cppClassName;
   private String hppContent;
   private String cppContent;

   Converter(String javaClassName, String cppClassName) {
      try {
         javaClass = Class.forName(javaClassName); // Ex: java.lang.String
      } catch (ClassNotFoundException e) {
         System.out.println(e.getMessage());
         System.out.println("Echec de la conversion, fin du programme");
         return;
      }

      this.cppClassName = cppClassName;
   }

   /**
    * Effectue la conversion du Java vers le C++ Stock le résultat du fichier .hpp
    * et .cpp dans les variables 'hppContent' et 'cppContent'
    */
   void convert() {

      Field[] fields = null;

      // javaClass.
      // Parcourir les méthodes

      // Parcourir les attributs
      fields = javaClass.getFields();

      for(Field f : fields) {
         System.out.println(f.toString());
      }
      
      // Parcourir les imports

   }

   // TODO Méthode de conversion
   // TODO Méthode d'écriture ?

   /**
    * Ecrit hppContent dans le fichier .hpp et cppContent dans le .cpp
    */
   void write() {

   }

   @Override
   public String toString() {
      return  "--- HPP --\n" +
               hppContent + "\n\n"+
               "--- CPP ---\n" +
               cppContent;
   }
}
