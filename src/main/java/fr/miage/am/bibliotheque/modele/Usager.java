package fr.miage.am.bibliotheque.modele;

import jakarta.el.MethodNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usager {

    //avec Getter/Setter
    private String identifiant;

    private String nom;

    private String prenom;

    private String numTel;

    private String mail;

    private String adresse;

    //sans Getter/setter
    private List<Emprunt> emprunts;
    private Reservation reservation;

    /**
     * Constructeur sans identifiant, gènere un identifiant pour l'usager
     *
     * @param nom     nom de l'usager
     * @param prenom  prenom de l'usager
     * @param numTel  numero de telephone de l'usager
     * @param mail    adresse email de l'usager
     * @param adresse adresse de l'usager
     */
    public Usager(String nom, String prenom, String numTel, String mail, String adresse) {
        //TODO gerer ça en DB
        this(nom.toLowerCase().split(" ")[0] + /*DB.getNbIdentifiantSimilaire() +*/1, nom, prenom, numTel, mail, adresse);

    }

    /**
     * Contructeur avec un identifiant donnée pour l'usager
     *
     * @param identifiant identifiant de l'usager
     * @param nom         nom de l'usager
     * @param prenom      prenom de l'usager
     * @param numTel      numero de telephone de l'usager
     * @param mail        adresse email de l'usager
     * @param adresse     adresse de l'usager
     */
    public Usager(String identifiant, String nom, String prenom, String numTel, String mail, String adresse) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.numTel = numTel;
        this.mail = mail;
        this.adresse = adresse;
        this.emprunts = new ArrayList<>();
        //TODO créer l'usager en DB aussi
    }

    /**
     * Recupere l'usager avec son identifiant en base de données
     *
     * @param identifiant identifiant de l'usager
     * @return Usager trouvé ou null
     */
    public static Usager identifier(String identifiant) {
        //TODO DB.getUsager()
        throw new MethodNotFoundException("Methode pas encore définie");
    }

    public void reserverOeuvre(Oeuvre oeuvre) {
        //todo
    }

    public List<Emprunt> getEmprunts() {
        return Collections.unmodifiableList(emprunts);
    }

    public void ajouterEmprunt(Emprunt emprunt) {
        if (emprunt == null) throw new IllegalArgumentException("L'emprunt ne doit pas être null");
        this.emprunts.add(emprunt);
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void definirReservation(Reservation reservation) {
        if (reservation == null) throw new IllegalArgumentException("La reservation ne doit pas être nulle");
        this.reservation = reservation;
    }


    //getter setter

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


}
