import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ParsedField extends ClassParser{

   public ParsedField(Field field) {
      super(field);
      type = field.getType().getSimpleName();
   }

   public String toString() {
      return portee + " " + modifiers + " " + type + " " + name;
   }
}
