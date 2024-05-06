package groupe3.projetCalzone.dto.requests;

import groupe3.projetCalzone.entities.TypeIngredient;

public class IngredientRequest {
	private Long id;
	private String nom;
	private TypeIngredient type;
	
	public IngredientRequest() {
		
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
