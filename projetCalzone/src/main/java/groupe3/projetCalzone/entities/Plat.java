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
@Table(name = "plat")
public class Plat{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "plat_id")
	private Long id;
	@Column(name = "plat_nom")
	private String nom;
	@Column(name = "plat_prix")
	private Double prix;
	@Column(name = "plat_tva")
	private Double tva = 10.0;
	@Column(name = "plat_photo")
	private String photo;
	@OneToMany (mappedBy= "id.plat")
	private Set<ComposantPlat> composantsPlat;
	
	public Plat() {
		
	}
	
	public Plat(String nom, Double prix) {
		this.nom = nom;
		this.prix = prix;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public Double getTva() {
		return tva;
	}

	public void setTva(Double tva) {
		this.tva = tva;
	}

	public Set<ComposantPlat> getComposantsPlat() {
		return composantsPlat;
	}

	public void setComposantsPlat(Set<ComposantPlat> composantsPlat) {
		this.composantsPlat = composantsPlat;
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
		Plat other = (Plat) obj;
		return Objects.equals(id, other.id);
	}
}
