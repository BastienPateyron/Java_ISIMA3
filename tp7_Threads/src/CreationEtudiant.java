import java.util.ArrayList;

public class CreationEtudiant implements Runnable {
   public Thread monThread;
   public int studentNumber;
   public ArrayList<Etudiant> liste;

   public CreationEtudiant(int studentNumber, ArrayList<Etudiant> liste) {
      this.studentNumber = studentNumber;
      this.liste = liste;
      monThread = new Thread(this);
      monThread.start();
   }

   public void run() {
      for (int i = 0; i < studentNumber; i++) {
         synchronized (liste) {
            liste.add(new Etudiant());
         }

         // Attends 10 ms pour chaque création
         long start = System.nanoTime();
         while(System.nanoTime() - start < 10000);
      }
   }
}
