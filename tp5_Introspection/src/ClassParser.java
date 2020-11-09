import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

/**
 * Classe mère pour réaliser le parsing d'attributs ou de méthodes
 */
public class ClassParser {
   public String portee = "";
   public String modifiers = "";
   public String type = "";
   public String name = "";

   public ClassParser(Member membre) {
      name = membre.getName();
      String[] modifiers_list = Modifier.toString(membre.getModifiers()).split(" ");
      portee = modifiers_list[0];

      for(int i = 1; i < modifiers_list.length; i++) {
         modifiers += modifiers_list[i];
      }
   }

   public String toString() {
      return (portee + " " + modifiers + " " + type + " " + name).trim();
   }

   public String toHpp() {
      return (modifiers + " " + type + " " + name + ";").trim();
   }

   public Boolean isPrivate() {
      return portee.equals("private");
   }
}
