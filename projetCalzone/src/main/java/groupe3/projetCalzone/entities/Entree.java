package groupe3.projetCalzone.entities;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "entree")
public class Entree{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entree_id")
	private Long id;
	@Column(name = "entree_nom")
	private String nom;
	@Column(name = "entree_prix")
	private Double prix;
	@Column(name = "entree_tva")
	private Integer tva;
	@Column(name = "entree_photo")
	private String photo;
	@OneToMany (mappedBy= "id.entree")
	private Set<ComposantEntree> composantsEntree;
	
	public Entree() {
	}
	
	public Entree(String nom, Double prix) {
		this.nom = nom;
		this.prix = prix;
	}

	public Entree(String nom, Double prix, Integer tva, String photo) {
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
		Entree other = (Entree) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
