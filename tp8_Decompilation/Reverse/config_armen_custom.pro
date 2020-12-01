-injars 2021_F5_pateyron_TP6.jar
-outjars 2021_F5_pateyron_TP6_Obfuscated.jar

-libraryjars 'C:\Program Files\Java\jdk-13.0.1\jmods\java.base.jmod'

-dontnote
-dontwarn



-keep public class Main {
    public static void main(java.lang.String[]);
}
