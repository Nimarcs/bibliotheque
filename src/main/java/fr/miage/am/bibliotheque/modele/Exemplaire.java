package fr.miage.am.bibliotheque.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Pour générer l'identifiant automatiquement
    private Long id;

    @Column
    private Integer numero;
    @Column
    private String editeur;
    @ManyToOne
    private Oeuvre oeuvre;
    @Column
    private Date dateCreation;
    @Column
    private Date dateMiseHorsService;
    @Column
    private Etat etat;
    @Column
    private String ISBN;

    @OneToMany
    private List<Emprunt> empruntList;


    public Exemplaire(Oeuvre oeuvre, Integer numero, String editeur) {
        this.oeuvre = oeuvre;
        this.numero = numero;
        this.editeur = editeur;
        this.empruntList = new ArrayList<>();
    }

    public boolean isDisponible() {
        return trouverEmpruntEnCours().isEmpty();
    }

    public void ajouterEmprunt(Emprunt emprunt) {
        if (emprunt == null) return;
        if (!isDisponible()) throw new IllegalStateException("L'exemplaire est deja emprunte");
        empruntList.add(emprunt);
    }

    private Optional<Emprunt> trouverEmpruntEnCours() {
        return empruntList.stream().filter(emprunt -> emprunt.getDateRetourReel() != null).findFirst();
    }

    public List<Emprunt> getEmprunts() {
        return Collections.unmodifiableList(empruntList);
    }
}
