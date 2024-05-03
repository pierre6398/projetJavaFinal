package groupe3.projetCalzone.entities;

import java.util.Objects;

import groupe3.projetCalzone.enums.BasePizza;
import groupe3.projetCalzone.enums.TaillePizza;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pizza")
public class Pizza{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pizza_id")
	private Long id;
	@Column(name = "pizza_nom")
	private String nom;
	@Column(name = "pizza_prix")
	private Double prix;
	@Column(name = "pizza_tva")
	private Integer tva;
	@Column(name = "pizza_base")
	@Enumerated(EnumType.STRING)
	private BasePizza base;
	@Column(name = "pizza_taille")
	@Enumerated(EnumType.STRING)
	private TaillePizza taille;
	@Column(name = "pizza_photo")
	private String photo;
	
	public Pizza() {
	}
	
	public Pizza(String nom, Double prix) {
		this.nom = nom;
		this.prix = prix;
	}
	
	public Pizza(String nom, Double prix, Integer tva, BasePizza base, TaillePizza taille, String photo) {
		this.nom = nom;
		this.prix = prix;
		this.tva = tva;
		this.base = base;
		this.taille = taille;
		this.photo = photo;
	}
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
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		return Objects.equals(id, other.id);
	}
}
