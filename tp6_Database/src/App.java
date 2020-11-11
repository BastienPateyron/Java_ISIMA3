public class App {
    public static void main(String[] args) throws Exception {
        Meteo maMeteo = new Meteo();
        String ville = args.length == 1 ? args[0] : "Clermont-Ferrand";
        System.out.println(maMeteo.request(ville));
        Bdd db = new Bdd();
    }
}
