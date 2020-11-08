import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ParsedMethod extends ClassParser {
   public String params = "";

   public ParsedMethod(Method method) {
      super(method);
      type = method.getReturnType().getSimpleName();
      for (Class p : method.getParameterTypes())
         params += p.getSimpleName() + ", ";
      if (params.equals("") == false)
         params = params.substring(0, params.length() - 2);
   }

   public ParsedMethod(Constructor constructor) {
      super(constructor);
      for (Class p : constructor.getParameterTypes())
         params += p.getSimpleName() + ", ";
      if (params.equals("") == false)
         params = params.substring(0, params.length() - 2);
   }

   // public String toString() {
   //    // StringBuilder s = new StringBuilder();
   //    // s.append(params);
   //    // return s.toString();
   // }

   public String toHpp() {
      StringBuilder s = new StringBuilder();
      s.append(type).append(' ')
       .append(name).append('(')
       .append(params).append(");");

      return s.toString().trim();
   }

   public String toCpp(String className) {
      StringBuilder s = new StringBuilder();
      s.append(type).append(' ')
       .append(className).append("::")
       .append(name).append('(')
       .append(params).append(')');
       
       // TODO: Modifier params dans le Cpp pour qu'il ajoute des noms aux arguments
       // TODO: Rajouter les parenthses et un return du type souhaité


      return s.toString().trim();
   }

   public Boolean isPrivate() {
      return portee.equals("private");
   }

}
