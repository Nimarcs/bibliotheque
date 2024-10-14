package fr.miage.am.bibliotheque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    GestionBackOffice gestionBackOffice;

    @GetMapping("/")
    public String helloWorld() {
        String identifiant = gestionBackOffice.creerUsager("Shore", "Jean", "0123456789", "jean.dupont@example.com", "123 Rue Exemple");

        return "<b>Hello, World!</b><br>Usager créé: " + identifiant;
    }
}