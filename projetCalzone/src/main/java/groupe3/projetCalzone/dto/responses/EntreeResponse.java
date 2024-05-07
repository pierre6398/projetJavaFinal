package groupe3.projetCalzone.dto.responses;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import groupe3.projetCalzone.entities.Entree;

public class EntreeResponse {
	
	@JsonView(JsonViews.Basic.class)
	private Long id;
	@JsonView(JsonViews.Basic.class)
	private String nom;
	@JsonView(JsonViews.Basic.class)
	private Double prix;
	@JsonView(JsonViews.Basic.class)
	private Integer tva;
	@JsonView(JsonViews.Basic.class)
	private String photo;

	public EntreeResponse() {
		
	}
	
	public EntreeResponse(Entree entree) {
		BeanUtils.copyProperties(entree, this);
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
	
	
}
