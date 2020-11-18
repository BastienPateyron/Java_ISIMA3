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
         try {
            Thread.sleep(0, 1); // 0 Miliseconds + 1 nanosecond
         } catch (Exception exception) {
            exception.printStackTrace();
         }
      }
   }
}
