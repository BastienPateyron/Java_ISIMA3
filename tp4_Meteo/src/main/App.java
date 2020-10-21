package main;


public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Meteo maMeteo = new Meteo();
        System.out.println(maMeteo.requete("Clermont-Ferrand"));
    }
}
