package dev.controller.vm;

import dev.model.Achat;

/**
 * Structure modèlisant une validation d'achat servant à communiquer avec l'extérieur (WEB API).
 */
public class AchatVM {
	
    private ProduitVM article;
	private Integer nombre;
	
	public AchatVM(Achat a)
	{
		this.article = new ProduitVM(a.getArticle());
		this.nombre = a.getNombre();
	}

	/**
	 * @return the article
	 */
	public ProduitVM getArticle() {
		return article;
	}

	/**
	 * @param article the article to set
	 */
	public void setArticle(ProduitVM article) {
		this.article = article;
	}

	/**
	 * @return the nombre
	 */
	public Integer getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(Integer nombre) {
		this.nombre = nombre;
	}
}
