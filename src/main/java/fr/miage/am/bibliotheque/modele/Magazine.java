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

    public Magazine(String nom, String isbn, String edition, Integer numero, Date datePremiereEdition) {
        super(nom, datePremiereEdition, isbn);
        this.edition = edition;
        this.numero = numero;
    }
}
