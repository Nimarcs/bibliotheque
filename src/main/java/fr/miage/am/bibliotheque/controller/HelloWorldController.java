package fr.miage.am.bibliotheque.controller;

import fr.miage.am.bibliotheque.modele.Usager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.miage.am.bibliotheque.service.UsagerService;

@RestController
public class HelloWorldController {

    @GetMapping("/")
    public String helloWorld() {
        // Créer un nouvel usager avec des données d'exemple
        Usager usager = new Usager("Dupont", "Jean", "0123456789", "jean.dupont@example.com", "123 Rue Exemple");

        // Sauvegarder l'usager dans la base de données
        UsagerService.createUsager(usager);

        return "<b>Hello, World!</b><br>Usager créé: " + usager.getIdentifiant();
    }
}