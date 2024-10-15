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
        return "<b>Hello, World!</b>";
    }
}