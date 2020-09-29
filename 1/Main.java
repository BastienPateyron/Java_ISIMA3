import java.io.*;

public class Main {
   public static void main(String [] args) {
      System.out.println("Hello There !");
      for (String arg : args) {
	 System.out.println(arg);
      }
      
      System.out.println("");

      for (int i = args.length - 1; i >= 0; i--) {
	 System.out.println(args[i]);
      }

      System.out.println("");

      for (String arg : args) {
	 System.out.println(arg.toUpperCase());
      }

      for (String arg : args) {
	 System.out.println(arg.length());
      }
   }
}
