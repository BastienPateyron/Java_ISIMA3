import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ParsedMethod extends ClassParser {
   public String params = "";
   public ArrayList<String> param_list = new ArrayList<>();
   public String alphabet = "abcdefghijklmnopqrstuvwxyz";

   public ParsedMethod(Method method) {
      super(method);
      type = method.getReturnType().getSimpleName();
      for (Class p : method.getParameterTypes()) {
         param_list.add(p.getSimpleName());
      }
   }

   public ParsedMethod(Constructor constructor) {
      super(constructor);
      for (Class p : constructor.getParameterTypes()) {
         param_list.add(p.getSimpleName());
      }
   }

   // public String toString() {
   // // StringBuilder s = new StringBuilder();
   // // s.append(params);
   // // return s.toString();
   // }

   public String toHpp() {
      StringBuilder s = new StringBuilder();
      s.append(type).append(' ').append(name).append('(');

      for (int i = 0; i < param_list.size(); i++) {
         s.append(param_list.get(i)).append(' ');
         if (i < param_list.size() - 1) {
            s.append(", ");
         }
      }

      s.append(");");

      return s.toString().trim();
   }

   public String toCpp(String className) {
      StringBuilder s = new StringBuilder();
      s.append(type).append(' ').append(className).append("::").append(name).append('(');

      for (int i = 0; i < param_list.size(); i++) {
         s.append(param_list.get(i)); // Type du paramètre
         s.append(' ');
         s.append(alphabet.charAt(i)); // Nom du paramètre
         if (i < param_list.size() - 1) {
            s.append(", ");
         }
      }

      s.append(") { ");

      if (type.equals("void") == false && type.equals("") == false) {
         s.append("return ");

         if (type.equals("int") || type.equals("float") || type.equals("double"))
            s.append("0");
         if (type.equals("Boolean"))
            s.append("false");
         if (type.equals("String"))
            s.append("\"Text\"");

         s.append("; ");
      }

      s.append("}");

      // TODO: Modifier params dans le Cpp pour qu'il ajoute des noms aux arguments
      // TODO: Rajouter les parenthses et un return du type souhaité

      return s.toString().trim();
   }

   public Boolean isPrivate() {
      return portee.equals("private");
   }

}
