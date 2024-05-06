package groupe3.projetCalzone.dto.responses;

import org.springframework.beans.BeanUtils;

import groupe3.projetCalzone.entities.Ingredient;

public class IngredientResponse {
	private Long id;
	private String nom;
	
	public IngredientResponse() {
		
	}
	
	public IngredientResponse(Ingredient ingredient) {
		BeanUtils.copyProperties(ingredient, this);
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
}
