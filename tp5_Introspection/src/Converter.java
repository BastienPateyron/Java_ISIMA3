import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Convertit une classe Java dans son équivalent C++ dans un fichier .cpp et
 * .hpp
 */
public class Converter {
   private Class javaClass;
   private String cppClassName;
   private String hppContent;
   private String cppContent;
   private ArrayList<ParsedField> parsedFieldList = new ArrayList<ParsedField>();
   private ArrayList<ParsedMethod> parsedMethodList = new ArrayList<ParsedMethod>();

   Converter(String javaClassName, String cppClassName) {
      try {
         javaClass = Class.forName(javaClassName); // Ex: java.lang.String
      } catch (ClassNotFoundException e) {
         System.out.println(e.getMessage());
         System.out.println("Echec de la conversion, fin du programme");
         System.exit(1);
      }

      this.cppClassName = cppClassName;
   }

   /**
    * Effectue la conversion du Java vers le C++ Stock le résultat du fichier .hpp
    * et .cpp dans les variables 'hppContent' et 'cppContent'
    */
   void convert() {

      Field[] fields = null;
      Method[] methods = null;
      Constructor[] constructors = null;
      
      // Introspection de la classe
      
      try {
         fields = javaClass.getDeclaredFields();
         constructors = javaClass.getDeclaredConstructors();
         methods = javaClass.getDeclaredMethods();
      } catch (SecurityException e) {
         System.out.println(e.getMessage());
      }

      System.out.println("-- Fields --");
      for (Field f : fields) parsedFieldList.add(new ParsedField(f));
      for(ParsedField f : parsedFieldList) System.out.println(f.toHpp());
      
      System.out.println("-- Methods --");
      for (Constructor c : constructors) parsedMethodList.add(new ParsedMethod(c)); 
      for (Method m : methods) parsedMethodList.add(new ParsedMethod(m));
      for(ParsedMethod m : parsedMethodList) System.out.println(m.toHpp());
      
      // Parcourir les imports
   }

   // TODO Méthode de conversion
   // TODO Méthode d'écriture ?

   /**
    * Ecrit hppContent dans le fichier .hpp et cppContent dans le .cpp
    */
   public void write() {

   }

   @Override
   public String toString() {
      return "--- HPP --\n" + hppContent + "\n\n" + "--- CPP ---\n" + cppContent;
   }

   /**
    * Remplace toutes les occurences de la classe "String" par "std::string" dans
    * le contenu de hppContent et cppContent
    */
   public void stringReplace() {

      // TODO
   }

}
