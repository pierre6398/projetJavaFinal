package groupe3.projetCalzone.dto.responses;


import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import groupe3.projetCalzone.entities.Ingredient;
import groupe3.projetCalzone.entities.TypeIngredient;

public class IngredientResponse {
	private Long id;
	@JsonView({JsonViews.Basic.class,JsonViews.Entree.class})
	private String nom;

	private TypeIngredient type;

	
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

	public TypeIngredient getType() {
		return type;
	}

	public void setType(TypeIngredient type) {
		this.type = type;
	}
}

