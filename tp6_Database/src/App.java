public class App {
    public static void main(String[] args) throws Exception {
        Meteo maMeteo = new Meteo();
        Bdd db = new Bdd("database.sqlite");
        String ville = args.length == 1 ? args[0] : "Clermont-Ferrand";

        Mesure maMesure = maMeteo.request(ville);
        // System.out.println(maMesure.toString());
        db.insertMesure(maMesure);


    }
}
