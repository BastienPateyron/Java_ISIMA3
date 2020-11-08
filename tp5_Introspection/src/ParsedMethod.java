import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ParsedMethod extends ClassParser {
   public String params = null;

   public ParsedMethod(Method method)  {
      super(method);
      type = method.getReturnType().toString();
      params = method.getParameterTypes().toString();
   }

   public ParsedMethod(Constructor constructor)  {
      super(constructor);
      params = constructor.getParameterTypes().toString();
   }


   public String toString() {
      StringBuilder s = new StringBuilder();
      s.append(params);
      return s.toString();
   }

   public String toHpp() {
      return modifiers + " " + type + " " + name;
   }
   
   public String toCpp(String className) {
      StringBuilder s = new StringBuilder();
      s.append(portee).append(' ').append(type).append(' ').append(className).append("::").append(name).append('(');
      // TODO: append params
      return s.toString();
   }

   public Boolean isPrivate() {
      return portee.equals("private");
   }

}
