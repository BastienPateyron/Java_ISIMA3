import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Etudiant monEtudiant = new Etudiant();
        System.out.println(monEtudiant);
        
        ArrayList<Etudiant> etudiants = new ArrayList<>();
        int nombreEtudiants = 150000;
        for(int i = 0; i < nombreEtudiants; i++) {
            Etudiant e = new Etudiant();
            etudiants.add(e);
            // System.out.println(e);
        }


    }
}
