public class App 
{
    static String javaClassName = "";
    static String cppClassName = "";
    static Boolean flagStdout = false;
    
    public static void main( String[] args )
    {        
        if (App.argParse(args)) {   // Vérification de la présence d'un argument
            System.out.println(javaClassName + " " + cppClassName + " " + flagStdout);
    
            Converter converter = new Converter(javaClassName, javaClassName);
            converter.convert();
            converter.writeFiles();
        }

    }


    /**
     * Gère les arguments passés au programme
     * @param args
     */
    static Boolean argParse(String[] args) {

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
            return true;
        } else {
            System.out.println("Please use at least one argument to specify the java class name. End of the program");
            return false;
        }
    }
}
