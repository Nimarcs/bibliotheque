package fr.miage.am.bibliotheque.vue;

import fr.miage.am.bibliotheque.controller.GestionBackOffice;
import fr.miage.am.bibliotheque.modele.Livre;
import fr.miage.am.bibliotheque.modele.Magazine;
import fr.miage.am.bibliotheque.modele.Usager;
import fr.miage.am.bibliotheque.modele.Exemplaire;
import fr.miage.am.bibliotheque.modele.Oeuvre;
import fr.miage.am.bibliotheque.repository.UsagerRepository;
import fr.miage.am.bibliotheque.service.ExemplaireService;
import fr.miage.am.bibliotheque.service.OeuvreService;
import fr.miage.am.bibliotheque.service.UsagerService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class IHMBackOffice {

    @Autowired
    GestionBackOffice gestionBackOffice;

    @Autowired
    private UsagerService usagerService;

    @Autowired
    private OeuvreService oeuvreService;

    @Autowired
    private ExemplaireService exemplaireService;

    @Autowired
    private UsagerRepository usagerRepository;



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
        try{
            usagerRepository.save(usager);
        } catch (ConstraintViolationException e){
            System.err.println("Erreur lors de l'ajout de l'usager : " + e.getConstraintName() + " n'est pas respecté");
            return "usagerError";
        }
        System.out.println("Usager ajouté: " + usager.getPrenom()+ " " + usager.getNom());
        return "usagerSuccess";
    }

    // récupère la réponse de mise à jour d'un usager
    @PostMapping("/updateUsager")
    public String updateUsager(@ModelAttribute("usager") Usager usager) {
        try{
            usagerRepository.save(usager);
        } catch (ConstraintViolationException e){
            System.err.println("Erreur lors de la mise à jour de l'usager : " + e.getConstraintName() + " n'est pas respecté");
            return "usagerError";
        }
        System.out.println("Usager mis à jour: " + usager.getPrenom()+ " " + usager.getNom());
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

    }
