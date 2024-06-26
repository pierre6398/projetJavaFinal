package groupe3.projetCalzone.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dessert")
public class Dessert{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dessert_id")
	private Long id;
	@Column(name = "dessert_nom")
	private String nom;
	@Column(name = "dessert_prix")
	private Double prix;
	@Column(name = "dessert_tva")
	private Double tva = 10.0;
	@Column(name = "dessert_photo")
	private String photo;
	@ManyToOne
	@JoinColumn(name = "carte_id")
	private Carte carte;
	
	@OneToMany(mappedBy = "id.dessert")
	private Set<ComposantDessert> composantsDessert;
	
	public Dessert() {
		
	}
	
	public Dessert(String nom, Double prix) {
		this.nom = nom;
		this.prix = prix;
		composantsDessert = new HashSet<ComposantDessert>();
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

	public Double getTva() {
		return tva;
	}

	public void setTva(Double tva) {
		this.tva = tva;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public Set<ComposantDessert> getComposantsDessert() {
		return composantsDessert;
	}

	public void setComposantsDessert(Set<ComposantDessert> composantsDessert) {
		this.composantsDessert = composantsDessert;
	}
	
	public void addComposantDessert(ComposantDessert composantDessert) {
		composantsDessert.add(composantDessert);
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
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
