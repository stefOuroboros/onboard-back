package dev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.UtilisateurVM;
import dev.repository.UtilisateurRepo;

/**
 * WEB API d'authentification.
 *
 * Elle permet de récupérer les informations du collègue connecté.
 */
@RestController
public class AuthentificationController {

    private UtilisateurRepo utilisateurRepo;

    public AuthentificationController(UtilisateurRepo utilisateurRepo) {
        this.utilisateurRepo = utilisateurRepo;
    }

    @GetMapping("/me")
    public ResponseEntity<?> quiSuisJe() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return this.utilisateurRepo.findByEmail(email)
                .map(UtilisateurVM::new)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.badRequest().build());
    }
}
