package fr.miage.am.bibliotheque.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Pour générer l'identifiant automatiquement
    private Long id;

    @ManyToOne
    private Usager usager;

    @ManyToOne
    private Oeuvre oeuvre;

    @Column
    private Date jourReservation;

    @Column
    private StatusReservation status;


}
