-injars 2021_F5_pateyron_TP6.jar
-outjars 2021_F5_pateyron_TP6_Obfuscated.jar

-libraryjars 'C:\Program Files\Java\jdk-13.0.1\jmods\java.base.jmod'
-libraryjars 'C:\Users\basti\OneDrive\ISIMA\ZZ3\Java\Java_ISIMA3\lib\gson-2.8.6.jar'
-libraryjars 'C:\Users\basti\OneDrive\ISIMA\ZZ3\Java\Java_ISIMA3\lib\sqlite-jdbc-3.32.3.2.jar'
-libraryjars 'C:\Users\basti\OneDrive\ISIMA\ZZ3\Java\Java_ISIMA3\lib\junit-platform-console-standalone-1.6.0.jar'

-skipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontshrink
-dontoptimize
-dontusemixedcaseclassnames
-dontnote
-dontwarn



# Keep - Applications. Keep all application classes, along with their 'main' methods.
-keepclasseswithmembers public class * {
    public static void main(java.lang.String[]);
}

# Keep - Libraries. Keep all public and protected classes, fields, and methods.
-keep public class * {
    public protected <fields>;
    public protected <methods>;
}

# Also keep - Database drivers. Keep all implementations of java.sql.Driver.
-keep class * extends java.sql.Driver
