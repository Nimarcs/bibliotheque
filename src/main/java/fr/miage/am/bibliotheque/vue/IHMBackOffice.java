package fr.miage.am.bibliotheque.vue;

import fr.miage.am.bibliotheque.controller.GestionBackOffice;
import fr.miage.am.bibliotheque.modele.Usager;
import fr.miage.am.bibliotheque.repository.UsagerRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IHMBackOffice {

    @Autowired
    GestionBackOffice gestionBackOffice;

    @Autowired
    private UsagerRepository usagerRepository;

    @GetMapping("/addUsager")
    public String showForm(Model model) {
        model.addAttribute("usager", new Usager());
        return "ajoutUsager";
    }

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
}
