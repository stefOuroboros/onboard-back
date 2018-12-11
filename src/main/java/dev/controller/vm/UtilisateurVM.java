package dev.controller.vm;

import dev.domain.Utilisateur;
import dev.domain.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Structure modèlisant un collègue servant à communiquer avec l'extérieur (WEB API).
 */
public class UtilisateurVM {

    private String email;
    private String nom;
    private String prenom;
    private List<Role> roles = new ArrayList<>();

    public UtilisateurVM(Utilisateur user) {
        this.email = user.getEmail();
        this.nom = user.getNom();
        this.prenom = user.getPrenom();
        this.roles = user.getRoles().stream().map(roleUtilisateur -> roleUtilisateur.getRole()).collect(Collectors.toList());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
