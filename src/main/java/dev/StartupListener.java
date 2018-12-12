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
import dev.model.Caracteristiques;
import dev.model.Discipline;
import dev.model.Marques;
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
	@Autowired
	private CaracteristiquesRepo caracteristiquesRepo;
	
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
        user1.setRoles(Arrays.asList(new RoleUtilisateur(user1, Role.ROLE_ADMINISTRATEUR), new RoleUtilisateur(user1, Role.ROLE_UTILISATEUR)));
        this.utilisateurRepo.save(user1);

        Utilisateur user2 = new Utilisateur();
        user2.setNom("User");
        user2.setPrenom("DEV");
        user2.setEmail("user@dev.fr");
        user2.setMotDePasse(passwordEncoder.encode("superpass"));
        user2.setRoles(Arrays.asList(new RoleUtilisateur(user2, Role.ROLE_UTILISATEUR)));
        this.utilisateurRepo.save(user2);
        
        Produit prod1 = new Produit("1", "TARAB", 459.9, new String[]{"a", "b"},
        		10, this.caracteristiquesRepo.save(new Caracteristiques(119, 24, 3900, new double[]{81.9}, 42 )), Marques.LOADED, Discipline.DANCING, new int[]{1});
        this.produitRepo.save(prod1);

        Produit prod2 = new Produit("2", "BHANGRA", 329.4, new String[]{},
        		10, this.caracteristiquesRepo.save(new Caracteristiques(123, 24, 2540, new double[] {83.2}, 65)), Marques.LOADED, Discipline.DANCING, new int[] {1,2,3}); 
        this.produitRepo.save(prod2);

        Produit prod3 = new Produit("3", "BUTO ODORI ZINGANA", 536,new String[]{},
        2, this.caracteristiquesRepo.save(new Caracteristiques(125, 23, 2500, new double[] {88}, 65)), Marques.MAJUSTUS, Discipline.DANCING, new int[]{1,2,3}); 
        this.produitRepo.save(prod3);
        
        Produit prod4 = new Produit("4", "BALLAR", 350, new String[]{},
        5, this.caracteristiquesRepo.save(new Caracteristiques(121, 24, 2500, new double[]{77,83}, 65)), Marques.LUCALONGBOARD, Discipline.DANCING, new int[]{1,2}); 
        this.produitRepo.save(prod4);
        
        Produit prod5 = new Produit("5", "RUM RUNNER CARBON", 399.95, new String[]{},
        		10, this.caracteristiquesRepo.save(new Caracteristiques(88, 25, 1900, new double[]{59.7,67.3}, 70)), Marques.MOONSHINE, Discipline.DOWNHILL, new int[] {1}); 
        this.produitRepo.save(prod5);

        Produit prod6 = new Produit("6", "OSTEON OBSERVER", 329, new String[]{},
        		10, this.caracteristiquesRepo.save(new Caracteristiques(90, 23, 2400, new double[]{61.5}, 70)), Marques.LANDYACHTZ, Discipline.DOWNHILL, new int[] {1}); 
        this.produitRepo.save(prod6);

        Produit prod7 = new Produit("7", "TESSERACT CANTELATED", 223.95, new String[]{},
        		30, this.caracteristiquesRepo.save(new Caracteristiques(84, 24, 1800, new double[]{62,66}, 70)), Marques.LOADED, Discipline.DOWNHILL, new int[] {1}); 
        this.produitRepo.save(prod7);

        Produit prod8 = new Produit("8", "OVERLAND", 201.95, new String[]{},
        		4, this.caracteristiquesRepo.save(new Caracteristiques(94, 24, 1600, new double[]{53,56}, 60)), Marques.LOADED, Discipline.FREESTYLE, new int[] {1}); 
        this.produitRepo.save(prod8);

        Produit prod9 = new Produit("9", "CI FLYER MERRICK", 159, new String[]{},
        		7, this.caracteristiquesRepo.save(new Caracteristiques(78, 24, 1600,new double[]{} , 55)), Marques.CARVER, Discipline.CRUISING, new int[] {1}); 
        this.produitRepo.save(prod9);
        
    }

}
