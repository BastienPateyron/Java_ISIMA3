// import java.io.*;

public class Exemple {
   public static void main(String[] args) {
      // foreach
      for (String arg : args) {
         System.out.println(arg);;
      }

      String s = "";
      for (int i = 0; i < 65536; i++) {
         s += "coucou";
      }
      System.out.println((s.length()));
   }
}

