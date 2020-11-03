package Introspection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        String javaClassName = "";
        String cppClassName = "";
        Boolean flagStdout = false;

        if (args.length > 0) {
            javaClassName = cppClassName = args[0];

            if(args.length == 2) {
                if(args[1].equals("--stdout")) {
                    flagStdout = true;
                }
            } 
            if(args.length > 2) {
                cppClassName = args[1];
                if(args[2].equals("--stdout")) {
                    flagStdout = true;
                }
            } 
        } else {
            System.out.println("Please use at least one argument to specify the java class name");
        }
    
        System.out.println(javaClassName + " " + cppClassName + " " + flagStdout);
        



    }
}
