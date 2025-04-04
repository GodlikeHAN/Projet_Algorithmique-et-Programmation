public class Personne {
    private int identifiant;
    private String nom;
    private String adresse;
    private String contact;

    public Personne(int identifiant, String nom, String adresse, String contact) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getContact() {
        return contact;
    }

}
