package groupe3.projetCalzone.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "desserts")
public class Dessert{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private Double prix;
	private Integer tva;
	private String photo;
	
	
	public Dessert() {
	}

	public Dessert(Long id, String nom, Double prix, Integer tva, String photo) {
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.tva = tva;
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
		Dessert other = (Dessert) obj;
		return Objects.equals(id, other.id);
	}
	
}
