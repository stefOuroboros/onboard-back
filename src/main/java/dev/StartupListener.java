package dev;

import dev.domain.Utilisateur;
import dev.domain.Role;
import dev.domain.RoleUtilisateur;
import dev.domain.Version;
import dev.repository.UtilisateurRepo;
import dev.repository.VersionRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Code de démarrage de l'application.
 * Insertion de jeux de données.
 */
@Component
public class StartupListener {

    private String appVersion;
    private VersionRepo versionRepo;
    private PasswordEncoder passwordEncoder;
    private UtilisateurRepo utilisateurRepo;

    public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo, PasswordEncoder passwordEncoder, UtilisateurRepo utilisateurRepo) {
        this.appVersion = appVersion;
        this.versionRepo = versionRepo;
        this.passwordEncoder = passwordEncoder;
        this.utilisateurRepo = utilisateurRepo;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onStart() {
        this.versionRepo.save(new Version(appVersion));

        // Création de deux utilisateurs

        Utilisateur col1 = new Utilisateur();
        col1.setNom("Admin");
        col1.setPrenom("DEV");
        col1.setEmail("admin@dev.fr");
        col1.setMotDePasse(passwordEncoder.encode("superpass"));
        col1.setRoles(Arrays.asList(new RoleUtilisateur(col1, Role.ROLE_ADMINISTRATEUR), new RoleUtilisateur(col1, Role.ROLE_UTILISATEUR)));
        this.utilisateurRepo.save(col1);

        Utilisateur col2 = new Utilisateur();
        col2.setNom("User");
        col2.setPrenom("DEV");
        col2.setEmail("user@dev.fr");
        col2.setMotDePasse(passwordEncoder.encode("superpass"));
        col2.setRoles(Arrays.asList(new RoleUtilisateur(col2, Role.ROLE_UTILISATEUR)));
        this.utilisateurRepo.save(col2);
    }

}
