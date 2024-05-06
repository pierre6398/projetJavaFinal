package groupe3.projetCalzone.dto.requests;



public class ComposantDessertRequest {
	private Long idDessert;
	private Long idIngredient;
	
	public ComposantDessertRequest() {
		
	}

	public Long getIdDessert() {
		return idDessert;
	}

	public void setIdDessert(Long idDessert) {
		this.idDessert = idDessert;
	}

	public Long getIdIngredient() {
		return idIngredient;
	}

	public void setIdIngredient(Long idIngredient) {
		this.idIngredient = idIngredient;
	}

	
}
