package main;

public class MetroStop {

    private int id;
    private double longitude;
    private double latitude;
    private String nom;
    private String arrondissement;
    private String type;

    public MetroStop(int id, double longitude, double latitude, String nom, String arrondissement, String type) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.nom = nom;
        this.arrondissement = arrondissement;
        this.type = type;
    }

    @Override
    public String toString() {
        return (
            id              + "\t" +
            nom             + "\t" +
            arrondissement  + "\t" +
            type            + "\t" +
            longitude       + "\t" +
            latitude
        );
    }

    /* Le Code restant ci-dessous a été généré automatiquement via IDE */

    // Accesseurs //

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getArrondissement() {
        return arrondissement;
    }

    public void setArrondissement(String arrondissement) {
        this.arrondissement = arrondissement;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // J'ai voulu tester la "puissance" d'un IDE sur le equals et le hashcode
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((arrondissement == null) ? 0 : arrondissement.hashCode());
        result = prime * result + id;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MetroStop other = (MetroStop) obj;
        if (arrondissement == null) {
            if (other.arrondissement != null)
                return false;
        } else if (!arrondissement.equals(other.arrondissement))
            return false;
        if (id != other.id)
            return false;
        if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
            return false;
        if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
            return false;
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

}
