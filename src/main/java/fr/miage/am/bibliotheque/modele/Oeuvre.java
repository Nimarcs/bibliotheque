package fr.miage.am.bibliotheque.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "oeuvre")
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Oeuvre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Pour générer l'identifiant automatiquement
    private Long id;

    @OneToMany
    private List<Reservation> reservationList;

    @Column
    private String nom;

    @Column
    private Date datePremiereEdition;

    @Column
    private String ISBN;

    protected Oeuvre(String nom, Date datePremiereEdition) {
        this.nom = nom;
        this.datePremiereEdition = datePremiereEdition;
        this.reservationList = new LinkedList<>();
    }

    public static Oeuvre identifier(String nom) {
        //TODO
        return null;
    }

    public int getNbResa() {
        return reservationList.size();
    }

    public void ajouterReservation(Reservation reservation) {
        if (reservation == null) return;
        if (reservationList.contains(reservation)) return;
        reservationList.addLast(reservation);
    }

    public void annulerReservation(Reservation reservation) {
        List<Reservation> reservationsFound = reservationList.stream().filter(reservation1 -> reservation1 == reservation).toList();
        if (reservationsFound.size() > 1) throw new IllegalStateException("Multiple reservation found");
        if (reservationsFound.size() == 0) return;
        reservationList.remove(reservation);
    }

    public Reservation getProchaineReservation() {
        return reservationList.removeFirst();
    }

    public Exemplaire trouverExemplaireDisponible() {
        return null;
        //return Exemplaire.identifier(this);
    }
}
