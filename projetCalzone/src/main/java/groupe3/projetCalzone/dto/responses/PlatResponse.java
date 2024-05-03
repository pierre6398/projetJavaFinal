package groupe3.projetCalzone.dto.responses;

import java.util.Objects;

import org.springframework.beans.BeanUtils;

import groupe3.projetCalzone.entities.Plat;


public class PlatResponse {
	private Long id;
	private String nom;
	private Double prix;
	private Double tva = 10.0;
	private String photo;
	
	public PlatResponse() {
		
	}
	
	public PlatResponse(Plat plat) {
		BeanUtils.copyProperties(plat, this);
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
		PlatResponse other = (PlatResponse) obj;
		return Objects.equals(id, other.id);
	}
}
