package dev.controller.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dev.domain.Role;
import dev.domain.Utilisateur;

public class ProduitVM {

    private String nom;
    private String reference;
    private String marque;
    private String discipline;
    private List<Role> roles = new ArrayList<>();

    public UtilisateurVM(Utilisateur user) {
        this.email = user.getEmail();
        this.nom = user.getNom();
        this.prenom = user.getPrenom();
        this.roles = user.getRoles().stream().map(roleUtilisateur -> roleUtilisateur.getRole()).collect(Collectors.toList());
    }
}
