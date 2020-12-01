import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        Meteo maMeteo = new Meteo();
        Bdd db = new Bdd("database.sqlite");
        String ville = args.length == 1 ? args[0] : "Clermont-Ferrand";

        try {
            Mesure maMesure = maMeteo.request(ville);
            // Mesure maMesure = maMeteo.request("uneVilleImaginaire");
            db.insertMesure(maMesure);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Mesure> mesureList = db.getAllMesures();

        for(Mesure m : mesureList) {
            System.out.println(m.toString());
        }
    }
}
