import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
   private String mainCppContent;
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
      // for (ParsedField f : parsedFieldList) System.out.println(f.toHpp());
      
      System.out.println("-- Methods --");
      for (Constructor c : constructors) parsedMethodList.add(new ParsedMethod(c)); 
      for (Method m : methods) parsedMethodList.add(new ParsedMethod(m));
      // for (ParsedMethod m : parsedMethodList) System.out.println(m.toHpp() + '\t' + m.toCpp(cppClassName));

      hppContent = toHpp().replace("String ", "std::string ").replace("Boolean", "bool");
      cppContent = toCpp().replace("String ", "std::string ").replace("Boolean", "bool");
      mainCppContent = "#include \"" + cppClassName + ".hpp\"\n\nint main() {\n\treturn EXIT_SUCCESS;\n}";

      System.out.println(hppContent + "\n\n" + cppContent);
   }

   public String toHpp() {
      StringBuilder hpp = new StringBuilder();

      hpp.append("#include <iostream>\n\nclass ").append(cppClassName).append(" {\n")
         .append("\tprivate:\n");
      
      for (ParsedField f : parsedFieldList) {
         if(f.portee.equals("private")) {
            hpp.append("\t\t").append(f.toHpp()).append('\n');
         }
      }

      for (ParsedMethod m : parsedMethodList) {
         if(m.portee.equals("private")) {
            hpp.append("\t\t").append(m.toHpp()).append('\n');
         }
      }

      hpp.append("\n\tpublic:\n");

      for (ParsedField f : parsedFieldList) {
         if(f.portee.equals("public")) {
            hpp.append("\t\t").append(f.toHpp()).append('\n');
         }
      }

      for (ParsedMethod m : parsedMethodList) {
         if(m.portee.equals("public")) {
            hpp.append("\t\t").append(m.toHpp()).append('\n');
         }
      }

      hpp.append("};");
         
      return hpp.toString();
   }
   
   public String toCpp() {
      StringBuilder cpp = new StringBuilder();

      cpp.append("#include \"").append(cppClassName).append(".hpp\"\n\n");

      for (ParsedMethod m : parsedMethodList) {
         if(m.portee.equals("public")) {
            cpp.append(m.toCpp(cppClassName)).append('\n');
         }
      }

      return cpp.toString();
   }

   // TODO Méthode de conversion
   // TODO Méthode d'écriture ?

   /**
    * Ecrit hppContent dans le fichier .hpp et cppContent dans le .cpp
    */
   public void writeFiles() {
      BufferedWriter writer;
      try {
         writer = new BufferedWriter(new FileWriter(cppClassName + ".hpp"));
         writer.write(hppContent);
         writer.close();

         writer = new BufferedWriter(new FileWriter(cppClassName + ".cpp"));
         writer.write(cppContent);
         writer.close();

         writer = new BufferedWriter(new FileWriter("main.cpp"));
         writer.write(mainCppContent);
         writer.close();
      } catch (IOException e) {
         System.out.println(e.getMessage());
      }
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

   /**
    * Remplace toutes les occurences de la classe "Boolean" par "bool" dans
    * le contenu de hppContent et cppContent
    */
    public void boolReplace() {

      // TODO
   }

}
