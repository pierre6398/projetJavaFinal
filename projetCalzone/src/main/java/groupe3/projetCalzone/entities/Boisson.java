package groupe3.projetCalzone.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "boisson")
public class Boisson {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "boisson_id")
	private Long id;
	@Column(name = "boisson_nom")
	private String nom;
	@Column(name = "boisson_prix")
	private Double prix;
	@Column(name = "boisson_tva")
	private Double tva = 10.0;
	@Column(name = "boisson_alcool")
	private Boolean alcool;
	@ManyToOne
	@JoinColumn(name = "carte_id")
	private Carte carte;

	public Boisson() {
	}

	public Boisson(String nom, Double prix, Boolean alcool) {
		this.nom = nom;
		this.prix = prix;
		this.alcool = alcool;
		if (alcool) {
			tva = 20.0;
		}
	}

	public Boisson(String nom, Double prix, Double tva, Boolean alcool) {
		this.nom = nom;
		this.prix = prix;
		this.tva = tva;
		this.alcool = alcool;
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

	public Boolean getAlcool() {
		return alcool;
	}

	public void setAlcool(Boolean alcool) {
		this.alcool = alcool;
	}

	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
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
		Boisson other = (Boisson) obj;
		return Objects.equals(id, other.id);
	}

}
