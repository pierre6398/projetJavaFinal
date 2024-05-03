package groupe3.projetCalzone.dto.requests;

import groupe3.projetCalzone.enums.BasePizza;
import groupe3.projetCalzone.enums.TaillePizza;

public class PizzaRequest {
	
	private Long id;
	private String nom;
	private Double prix;
	private Integer tva;
	private BasePizza base;
	private TaillePizza taille;
	private String photo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	public Integer getTva() {
		return tva;
	}
	public void setTva(Integer tva) {
		this.tva = tva;
	}
	public BasePizza getBase() {
		return base;
	}
	public void setBase(BasePizza base) {
		this.base = base;
	}
	public TaillePizza getTaille() {
		return taille;
	}
	public void setTaille(TaillePizza taille) {
		this.taille = taille;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	

}
