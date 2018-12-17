package dev;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.domain.Role;
import dev.domain.RoleUtilisateur;
import dev.domain.Utilisateur;
import dev.domain.Version;
import dev.model.Discipline;
import dev.model.Marque;
import dev.model.Produit;
import dev.repository.CaracteristiquesRepo;
import dev.repository.ProduitRepo;
import dev.repository.UtilisateurRepo;
import dev.repository.VersionRepo;

/**
 * Code de démarrage de l'application.
 * Insertion de jeux de données.
 */
@Component
public class StartupListener {
	
	@Autowired
    private ProduitRepo produitRepo;
	/*@Autowired
	private CaracteristiquesRepo caracteristiquesRepo;*/
	
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

        Utilisateur user1 = new Utilisateur();
        user1.setNom("Admin");
        user1.setPrenom("DEV");
        user1.setEmail("admin@dev.fr");
        user1.setMotDePasse(passwordEncoder.encode("superpass"));
        user1.setRoles(Arrays.asList(new RoleUtilisateur(user1, Role.ROLE_ADMINISTRATEUR)));
        this.utilisateurRepo.save(user1);

        Utilisateur user2 = new Utilisateur();
        user2.setNom("User");
        user2.setPrenom("DEV");
        user2.setEmail("user@dev.fr");
        user2.setMotDePasse(passwordEncoder.encode("superpass"));
        user2.setRoles(Arrays.asList(new RoleUtilisateur(user2, Role.ROLE_UTILISATEUR)));
        this.utilisateurRepo.save(user2);
        
        Produit prod1 = new Produit("1", "TARAB", 459.9, "a",
        		10, 119, 24, 3900, 81.9, 42 , Marque.LOADED, Discipline.DANCING, true);
        this.produitRepo.save(prod1);

        Produit prod2 = new Produit("2", "BHANGRA", 329.4, "b",
        		10, 123, 24, 2540, 83.2, 65, Marque.LOADED, Discipline.DANCING, true); 
        this.produitRepo.save(prod2);

        Produit prod3 = new Produit("3", "BUTO ODORI ZINGANA", 536,"c",
        2, 125, 23, 2500, 88.00, 65, Marque.MAJUSTUS, Discipline.DANCING, true); 
        this.produitRepo.save(prod3);
        
        Produit prod4 = new Produit("4", "BALLAR", 350, "d",
        5, 121, 24, 2500, 77.83, 65, Marque.LUCALONGBOARD, Discipline.DANCING, true);
        this.produitRepo.save(prod4);
        
        Produit prod5 = new Produit("5", "RUM RUNNER CARBON", 399.95, "e",
        		10, 88, 25, 1900, 59.7, 70, Marque.MOONSHINE, Discipline.DOWNHILL, true); 
        this.produitRepo.save(prod5);

        Produit prod6 = new Produit("6", "OSTEON OBSERVER", 329, "f",
        		10, 90, 23, 2400, 61.5, 70, Marque.LANDYACHTZ, Discipline.DOWNHILL, true); 
        this.produitRepo.save(prod6);

        Produit prod7 = new Produit("7", "TESSERACT CANTELATED", 223.95, "g",
        		30, 84, 24, 1800, 66.00, 70, Marque.LOADED, Discipline.DOWNHILL, true); 
        this.produitRepo.save(prod7);

        Produit prod8 = new Produit("8", "OVERLAND", 201.95, "h",
        		4, 94, 24, 1600, 53.00, 60, Marque.LOADED, Discipline.FREESTYLE, true); 
        this.produitRepo.save(prod8);

        Produit prod9 = new Produit("9", "CI FLYER MERRICK", 159, "i",
        		7, 78, 24, 1600,00.00 , 55, Marque.CARVER, Discipline.CRUISING, true); 
        this.produitRepo.save(prod9);
        
    }

}
