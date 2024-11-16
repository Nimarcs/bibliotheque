package fr.miage.am.bibliotheque.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Pour générer l'identifiant automatiquement
    private Long id;

    @ManyToOne
    private Exemplaire exemplaire;
    @ManyToOne
    private Usager usager;
    @Column
    private Date dateRetourReel;

    @Column
    private Date dateRetourPrevu;

    public Emprunt(Date dateRetourPrevu, Exemplaire exemplaire, Usager usager) {
        this.dateRetourPrevu = dateRetourPrevu;
        this.exemplaire = exemplaire;
        this.usager = usager;
        exemplaire.ajouterEmprunt(this);
        usager.ajouterEmprunt(this);
    }

    public void rendre() {
        if (dateRetourReel != null) throw new IllegalStateException("L'emprunt est deja rendu");
        dateRetourReel = new Date();
    }


}
