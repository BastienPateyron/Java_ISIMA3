package main;


public class App {
    public static void main(String[] args) throws Exception {
        Meteo maMeteo = new Meteo();
        System.out.println(maMeteo.request(args[0]));
    }
}
