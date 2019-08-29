package dev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.repository.*;
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
import dev.model.Achat;
import dev.model.Commande;
import dev.model.Discipline;
import dev.model.Marque;
import dev.model.Produit;

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
    
    @Autowired
    private ProduitRepo produitRepo;
    @Autowired
    private CommandeRepo commandeRepo;
    @Autowired
    private MarqueRepo marqueRepo;
    
    @Autowired
	private AchatRepo achatRepo;


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

        //Création des marques
        List<Produit> produitsLoaded = new ArrayList<Produit>();
        Marque loaded = new Marque("LOADED", produitsLoaded);
        List<Produit> produitsMajutsu = new ArrayList<Produit>();
        Marque majutsu = new Marque ("MAJUTSU", produitsMajutsu);
        List<Produit> produitsLucalongboard = new ArrayList<Produit>();
        Marque lucalongboard = new Marque ("LUCALONGBOARD", produitsLucalongboard);
        List<Produit> produitsLandyatchz = new ArrayList<Produit>();
        Marque landyatchz = new Marque ("LANDYATCHZ", produitsLandyatchz);
        List<Produit> produitsMoonshine = new ArrayList<Produit>();
        Marque moonshine = new Marque ("MOONSHINE", produitsMoonshine);
        List<Produit> produitsCarver = new ArrayList<Produit>();
        Marque carver = new Marque ("CARVER", produitsCarver);
        this.marqueRepo.save(loaded);
        this.marqueRepo.save(majutsu);
        this.marqueRepo.save(lucalongboard);
        this.marqueRepo.save(landyatchz);
        this.marqueRepo.save(moonshine);
        this.marqueRepo.save(carver);

        // Création des produits

        Produit prod1 = new Produit("1", "TARAB", 459.9, "http://www.hw-shapes.de/bilder/produkte/gross/Loaded-Tarab-Longboard-Komplettboard_b2.jpg",
        		10, 119, 24, 3900, 81.9, 42 , loaded, Discipline.DANCING, true);
        this.produitRepo.save(prod1);

        Produit prod2 = new Produit("2", "BHANGRA", 329.4, "https://www.actionboardshop.com/media/catalog/product/cache/1/image/760x800/9df78eab33525d08d6e5fb8d27136e95/l/o/loaded-bhangra-v2-longboard-new-2019-bamboo-dancer.jpg",
        		10, 123, 24, 2540, 83.2, 65, loaded, Discipline.DANCING, true);
        this.produitRepo.save(prod2);

        Produit prod3 = new Produit("3", "BUTO ODORI ZINGANA", 536,"https://www.majutsu.fr/377-thickbox_default/buto-odori.jpg",
        2, 125, 23, 2500, 88.00, 65, majutsu, Discipline.DANCING, true);
        this.produitRepo.save(prod3);

        Produit prod4 = new Produit("4", "BALLAR", 350, "https://cdn.shoplo.com/6130/products/th640/aaar/248-ballar2018.png",
        5, 121, 24, 2500, 77.83, 65, lucalongboard, Discipline.DANCING, true);
        this.produitRepo.save(prod4);

        Produit prod5 = new Produit("5", "RUM RUNNER CARBON", 399.95, "http://www.moonshinemfg.com/core/media/media.nl/id.7172814/c.429740/.f?h=16cf9d256f8e6a6f9865",
        		10, 88, 25, 1900, 59.7, 70, moonshine, Discipline.DOWNHILL, true);
        this.produitRepo.save(prod5);

        Produit prod6 = new Produit("6", "OSTEON OBSERVER", 329, "https://cdn.skatedeluxe.com/images/product_images/400px2x/117136-0-Landyachtz-OsteonObserver3525895cm.jpg",
        		10, 90, 23, 2400, 61.5, 70, landyatchz, Discipline.DOWNHILL, true);
        this.produitRepo.save(prod6);

        Produit prod7 = new Produit("7", "TESSERACT CANTELATED", 223.95, "https://images-na.ssl-images-amazon.com/images/I/91BTC1pnEyL._SL1500_.jpg",
        		30, 84, 24, 1800, 66.00, 70, loaded, Discipline.DOWNHILL, true);
        this.produitRepo.save(prod7);

        Produit prod8 = new Produit("8", "OVERLAND", 201.95, "https://cdn2.bigcommerce.com/server5800/c708inhv/products/4131/images/11039/overland_complete_yell_4__30176.1438600202.1280.1280.png?c=2",
        		4, 94, 24, 1600, 53.00, 60, loaded, Discipline.FREESTYLE, true);
        this.produitRepo.save(prod8);

        Produit prod9 = new Produit("9", "CI FLYER MERRICK", 159, "https://images-na.ssl-images-amazon.com/images/I/71J%2BJPutCPL._SY450_.jpg",
        		7, 78, 24, 1600, 00.01 , 55, carver, Discipline.CRUISING, true);
        this.produitRepo.save(prod9);

        List<Produit> produits1 = new ArrayList<Produit>();
        produits1.add(prod1);
        produits1.add(prod2);
        produits1.add(prod7);
        produits1.add(prod8);
        loaded.setProduits(produits1);
        marqueRepo.save(loaded);


        // Création des commandes
        Achat a1 = new Achat();
        a1.setArticle(prod1);
        a1.setNombre(10);

        Commande com1 = new Commande(1, user2);
        a1.setCommande(com1);
        com1.getAchats().add(a1);

        this.commandeRepo.save(com1);
        this.achatRepo.save(a1);
    }

}
