package groupe3.projetCalzone.entities;

import java.util.Objects;

import groupe3.projetCalzone.enums.BasePizza;
import groupe3.projetCalzone.enums.TaillePizza;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pizzas")
public class Pizza{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private Double prix;
	private Integer tva;
	@Enumerated(EnumType.STRING)
	private BasePizza base;
	@Enumerated(EnumType.STRING)
	private TaillePizza taille;
	private String photo;
	
	public Pizza() {
	}
	public Pizza(Long id, String nom, Double prix, Integer tva, BasePizza base, TaillePizza taille, String photo) {
		this.id = id;
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
