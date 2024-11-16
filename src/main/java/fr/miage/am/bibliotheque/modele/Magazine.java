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
public class Magazine extends Oeuvre {

    @Column
    private Integer numero;

    @Column
    private String edition;

    protected Magazine(String nom, String edition, Integer numero, Date datePremiereEdition) {
        super(nom, datePremiereEdition);
        this.edition = edition;
        this.numero = numero;
    }
}
