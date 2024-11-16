package fr.miage.am.bibliotheque.modele;

import jakarta.el.MethodNotFoundException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "usager") // Nom de la table dans la base de données
@Getter
@Setter
public class Usager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Pour générer l'identifiant automatiquement
    private long id;

    @Column
    private String identifiant;

    @Column
    private String nom;

    @Column
    private String prenom;

    @Column
    private String numTel;

    @Column
    private String mail;

    @Column
    private String adresse;

    @OneToMany(mappedBy = "usager", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter(AccessLevel.NONE)
    private List<Emprunt> emprunts;

    //@OneToOne(mappedBy = "usager", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private Reservation reservation;


    public Usager(String nom, String prenom, String numTel, String mail, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.numTel = numTel;
        this.mail = mail;
        this.adresse = adresse;
        this.emprunts = new ArrayList<>();
    }

    public Usager(String identifiant, String nom, String prenom, String numTel, String mail, String adresse) {
        this(nom, prenom, numTel, mail, adresse);
        this.identifiant = identifiant;
    }

    public Usager() {
    }


    /**
     * Recupere l'usager avec son identifiant en base de données
     *
     * @param identifiant identifiant de l'usager
     * @return Usager trouvé ou null
     */
    public static Usager identifier(String identifiant) {
        //TODO récupérer l'usager
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

//    public Reservation getReservation() {
//        return reservation;
//    }
//
//    public void definirReservation(Reservation reservation) {
//        if (reservation == null) throw new IllegalArgumentException("La reservation ne doit pas être nulle");
//        this.reservation = reservation;
//    }
}
