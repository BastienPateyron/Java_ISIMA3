public class PersonneExemple {
    private String nom;
    public String prenom;
    private int age;

    static private Boolean test;

    public PersonneExemple(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public PersonneExemple() {
        prenom = "John";
        nom = "Doe";
    }

    public String toString() {
        return prenom + " " + nom;
    }

    public Boolean estMajeure() {
        return (age < 18) ? false : true;
    }
}
