package fr.miage.am.bibliotheque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

    @Autowired
    GestionBackOffice gestionBackOffice;

    @GetMapping("/")
    public String helloWorld() {
        return "accueil";
    }
}