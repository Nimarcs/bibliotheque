package fr.miage.am.bibliotheque.controller;

import fr.miage.am.bibliotheque.modele.*;
import fr.miage.am.bibliotheque.repository.UsagerRepository;
import fr.miage.am.bibliotheque.service.EmpruntService;
import fr.miage.am.bibliotheque.service.ExemplaireService;
import fr.miage.am.bibliotheque.service.OeuvreService;
import fr.miage.am.bibliotheque.service.UsagerService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ControlleurBackOffice {

    @Autowired
    private UsagerService usagerService;

    @Autowired
    private OeuvreService oeuvreService;

    @Autowired
    private ExemplaireService exemplaireService;

    @Autowired
    private EmpruntService empruntService;

    @Autowired
    private UsagerRepository usagerRepository;

    @GetMapping("/")
    public String acceuil() {
        return "accueil";
    }

    // affiche la page d'ajout d'un usager
    @GetMapping("/addUsager")
    public String showForm(Model model) {
        model.addAttribute("usager", new Usager());
        return "ajoutUsager";
    }

    // affiche la page de recherche d'un usager
    @GetMapping("/searchUsager")
    public String showSearchForm(Model model) {
        model.addAttribute("usager", new Usager());
        return "rechercheUsager";
    }

    // récupère la réponse d'ajout d'un usager
    @PostMapping("/addUsager")
    public String submitForm(@ModelAttribute("usager") Usager usager) {
        try {
            usagerRepository.save(usager);
        } catch (ConstraintViolationException e) {
            System.err.println("Erreur lors de l'ajout de l'usager : " + e.getConstraintName() + " n'est pas respecté");
            return "usagerError";
        }
        System.out.println("Usager ajouté: " + usager.getPrenom() + " " + usager.getNom());
        return "usagerSuccess";
    }

    // récupère la réponse de mise à jour d'un usager
    @PostMapping("/updateUsager")
    public String updateUsager(@ModelAttribute("usager") Usager usager) {
        try {
            usagerRepository.save(usager);
        } catch (ConstraintViolationException e) {
            System.err.println("Erreur lors de la mise à jour de l'usager : " + e.getConstraintName() + " n'est pas respecté");
            return "usagerError";
        }
        System.out.println("Usager mis à jour: " + usager.getPrenom() + " " + usager.getNom());
        return "usagerSuccess";
    }

    // affichage de la page de la liste des usagers
    @GetMapping("/rechercherUsager")
    public String rechercherUsager(
            @RequestParam String terme,
            Model model) {
        List<Usager> usagers = usagerService.rechercherUsagers(terme);

        model.addAttribute("usagers", usagers);
        model.addAttribute("termeRecherche", terme);

        return "resultatsRecherche";
    }

    // affichage de la page des infos d'un usager
    @GetMapping("/usager/{identifiant}")
    public String afficherUsager(@PathVariable String identifiant, Model model) {
        Usager usager = usagerService.getUsagerByIdentifiant(identifiant);

        model.addAttribute("usager", usager);
        return "detailUsager";
    }

    // récupère la réponse de suppression d'un usager
    @PostMapping("/usager/{identifiant}/supprimer")
    public String supprimerUsager(@PathVariable String identifiant) {
        usagerService.supprimerUsager(identifiant);
        return "redirect:/searchUsager";
    }

    // affiche la page de modification d'un usager
    @GetMapping("/modifierUsager/{identifiant}")
    public String afficherFormulaireModification(@PathVariable String identifiant, Model model) {
        Usager usager = usagerService.getUsagerByIdentifiant(identifiant);
        model.addAttribute("usager", usager);
        return "modifierUsager";
    }

    // récupère la réponse de modification d'un usager
    @PostMapping("/modifierUsager/{identifiant}")
    public String modifierUsager(@PathVariable String identifiant, @ModelAttribute Usager usagerModifie) {
        usagerService.mettreAJourUsager(identifiant, usagerModifie);
        return "redirect:/usager/" + identifiant;
    }

    // Afficher le formulaire pour choisir le type d'œuvre
    @GetMapping("/addOeuvre")
    public String afficherPageTypeOeuvre() {
        return "ajouterOeuvre"; // Page de sélection LIVRE ou MAGAZINE
    }

    // récupère la réponse de suppression d'une oeuvre
    @PostMapping("/oeuvre/supprimer")
    public String supprimerOeuvre(@RequestParam String ISBN) {
        try {
            oeuvreService.supprimerOeuvre(ISBN);
        } catch (NullPointerException e) {
            System.err.println("Erreur lors de la suppression de l'oeuvre : " + e.getMessage());
            return "oeuvreError";
        }
        return "redirect:/addOeuvre";
    }

    // Afficher le formulaire pour ajouter un livre
    @GetMapping("/addLivre")
    public String afficherAjouterLivre(@ModelAttribute Livre livre) {
        return "ajouterLivre";
    }

    // Afficher le formulaire pour ajouter un magazine
    @GetMapping("/addMagazine")
    public String afficherAjouterMagazine(@ModelAttribute Magazine magazine) {
        return "ajouterMagazine";
    }

    // récupère la réponse de l'ajout d'un magazine
    @PostMapping("/addMagazine")
    public String addMagazine(@RequestParam String nom,
                              @RequestParam String isbn,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date datePremiereEdition,
                              @RequestParam Integer numero,
                              @RequestParam String edition) {

        Magazine magazine = new Magazine(nom, isbn, edition, numero, datePremiereEdition);
        this.oeuvreService.save(magazine); // Sauvegarder le magazine dans la base de données
        return "oeuvreSuccess";
    }

    @PostMapping("/addLivre")
    public String addLivre(@RequestParam String nom,
                           @RequestParam String isbn,
                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date datePremiereEdition,
                           @RequestParam String auteur) {

        Livre livre = new Livre(nom, isbn, auteur, datePremiereEdition);
        livre.setISBN(isbn);
        this.oeuvreService.save(livre); // Sauvegarde le livre dans la base de données

        return "oeuvreSuccess";
    }

    @PostMapping("/addExemplaire")
    public String addExemplaire(@ModelAttribute Exemplaire exemplaire, Model model) {
        exemplaireService.saveExemplaire(exemplaire);
        return "exemplaireSuccess";
    }

    @GetMapping("/addExemplaire")
    public String showAddExemplaireForm(Model model) {
        List<Oeuvre> oeuvres = oeuvreService.getAllOeuvres();
        model.addAttribute("oeuvres", oeuvres);
        model.addAttribute("exemplaire", new Exemplaire());
        return "addExemplaire";
    }

    @GetMapping("/addEmprunt")
    public String showAddEmpruntForm(Model model) {
        model.addAttribute("oeuvres", oeuvreService.getAllOeuvres());
        model.addAttribute("emprunt", new Emprunt());
        return "addEmprunt";
    }

    @PostMapping("/addEmprunt")
    public String addEmprunt(@RequestParam Long oeuvreId,
                             @RequestParam String usagerIdentifiant,
                             @RequestParam Integer exemplaireNumero,
                             @RequestParam String dateRetourPrevu) {
        // Récupérer l'œuvre
        Oeuvre oeuvre = oeuvreService.getOeuvreById(oeuvreId)
                .orElseThrow(() -> new RuntimeException("Œuvre introuvable avec l'ID : " + oeuvreId));

        // Récupérer l'usager
        Usager usager = usagerService.getUsagerByIdentifiant(usagerIdentifiant);
        if (usager == null) {
            return "redirect:/addEmprunt?error=usager";
        }

        // Récupérer l'exemplaire
        Optional<Exemplaire> optionalExemplaire = exemplaireService.identifier(oeuvre, exemplaireNumero);
        if (optionalExemplaire.isEmpty()) {
            return "redirect:/addEmprunt?error=exemplaire";
        }

        Exemplaire exemplaire = optionalExemplaire.get();

        // Convertir la date prévue
        Date retourPrevuDate;
        try {
            retourPrevuDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateRetourPrevu);
        } catch (ParseException e) {
            return "redirect:/addEmprunt?error=invalidDate";
        }

        // Créer l'emprunt
        Emprunt emprunt = new Emprunt();
        emprunt.setExemplaire(exemplaire); // Exemplaire est bien présent
        emprunt.setUsager(usager);
        emprunt.setDateRetourPrevu(retourPrevuDate);
        empruntService.saveEmprunt(emprunt);

        return "empruntSuccess";
    }

    @GetMapping("/rendreEmprunt")
    public String showRendreEmpruntForm(Model model) {
        model.addAttribute("emprunts", empruntService.getAllEmpruntEnCours());
        model.addAttribute("etats", Etat.NEUF.getDeclaringClass().getEnumConstants());

        return "rendreEmprunt";
    }

    @PostMapping("/rendreEmprunt")
    public String rendreEmprunt(@RequestParam Long empruntId,
                                @RequestParam Etat nouvelEtat) {
        // Récupérer l'emprunt
        Emprunt emprunt = empruntService.getEmpruntById(empruntId)
                .orElseThrow(() -> new RuntimeException("Emprunt introuvable avec l'ID : " + empruntId));

        Exemplaire exemplaire = emprunt.getExemplaire();
        exemplaire.setEtat(nouvelEtat);
        exemplaireService.saveExemplaire(exemplaire);

        empruntService.rendre(emprunt);

        return "rendreSuccess";
    }


    @GetMapping("/empruntSuccess")
    public String showSuccessPage() {
        return "empruntSuccess";
    }

}
