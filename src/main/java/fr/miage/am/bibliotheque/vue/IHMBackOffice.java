package fr.miage.am.bibliotheque.vue;

import fr.miage.am.bibliotheque.controller.GestionBackOffice;
import fr.miage.am.bibliotheque.modele.Usager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IHMBackOffice {

    @Autowired
    GestionBackOffice gestionBackOffice;

    @GetMapping("/creerUsager")
    public String get_creerUsager() {
        return """
                  <form:form method="POST" modelAttribute="user">
                      <form:label path="email">Email: </form:label>
                      <form:input path="email" type="text"/>
                      <form:label path="password">Password: </form:label>
                      <form:input path="password" type="password" />
                      <input type="submit" value="Submit" />
                  </form:form>""";
    }

    @PostMapping("/creerUsager")
    public String post_creerUsager(@ModelAttribute Usager usager, Model model) {
        model.addAttribute("usager", usager);
        String identifiant = gestionBackOffice.creerUsager("Shore", "Jean", "0123456789", "jean.dupont@example.com", "123 Rue Exemple");

        return "<b>Hello, World!</b><br>Usager créé: " + identifiant;
    }
}
