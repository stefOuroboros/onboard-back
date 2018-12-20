package dev.controller.vm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dev.model.Achat;
import dev.model.Commande;

/**
 * Structure modèlisant une commande servant à communiquer avec l'extérieur (WEB API).
 */
public class CommandeVM {

	    private Integer numero;
	    private UtilisateurVM client;
	    private List<AchatVM> achats = new ArrayList<AchatVM>();
	    private LocalDate achatDate;
	    
	    public CommandeVM() {};
	    
	    public CommandeVM(Commande c) {
	    	this.numero = c.getNumero();
	    	this.client = new UtilisateurVM(c.getClient());	    	
	    	for (Achat a : c.getAchats())
	    	{
	    		this.achats.add(new AchatVM(a));
	    	}
	    	this.achatDate = c.getAchatDate();
	    }

	    /**
	     * @return Long return the numero
	     */
	    public Integer getNumero() {
	        return numero;
	    }

	    /**
	     * @param numero the numero to set
	     */
	    public void setNumero(Integer numero) {
	        this.numero = numero;
	    }

	    /**
	     * @return Utilisateur return the client
	     */
	    public UtilisateurVM getClient() {
	        return client;
	    }

	    /**
	     * @param client the client to set
	     */
	    public void setClient(UtilisateurVM client) {
	        this.client = client;
	    }

	    /**
	     * @return Date return the achatDate
	     */
	    public LocalDate getAchatDate() {
	        return achatDate;
	    }

	    /**
	     * @param achatDate the achatDate to set
	     */
	    public void setAchatDate(LocalDate achatDate) {
	        this.achatDate = achatDate;
	    }

		/**
		 * @return the achats
		 */
		public List<AchatVM> getAchats() {
			return achats;
		}

		/**
		 * @param achats the achats to set
		 */
		public void setAchats(List<AchatVM> achats) {
			this.achats = achats;
		}
}
