package groupe3.projetCalzone.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "boisson")
public class Boisson{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "boisson_id")
	private Long id;
	@Column(name = "boisson_nom")
	private String nom;
	@Column(name = "boisson_prix")
	private Double prix;
	@Column(name = "boisson_tva")
	private Integer tva;
	@Column(name = "boisson_alcool")
	private Boolean alcool;
	
	public Boisson() {
	}

	public Boisson(Long id, String nom, Double prix, Integer tva, Boolean alcool) {
		this.id = id;
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

	public Integer getTva() {
		return tva;
	}

	public void setTva(Integer tva) {
		this.tva = tva;
	}

	public Boolean getAlcool() {
		return alcool;
	}

	public void setAlcool(Boolean alcool) {
		this.alcool = alcool;
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
