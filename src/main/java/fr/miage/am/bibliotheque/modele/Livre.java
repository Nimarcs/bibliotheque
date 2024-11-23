package fr.miage.am.bibliotheque.modele;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Livre extends Oeuvre {

    @Column
    private String auteur;

    public Livre(String nom, String isbn, String auteur, Date datePremiereEdition) {
        super(nom, datePremiereEdition, isbn);
        this.auteur = auteur;
    }
}
