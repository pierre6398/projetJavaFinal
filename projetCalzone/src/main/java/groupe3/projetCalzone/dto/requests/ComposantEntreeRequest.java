package groupe3.projetCalzone.dto.requests;



public class ComposantEntreeRequest {
	private Long idEntree;
	private Long idIngredient;
	
	public ComposantEntreeRequest() {
		
	}

	public Long getIdIngredient() {
		return idIngredient;
	}

	public void setIdIngredient(Long idIngredient) {
		this.idIngredient = idIngredient;
	}


	public Long getIdEntree() {
		return idEntree;
	}


	public void setIdEntree(Long idEntree) {
		this.idEntree = idEntree;
	}

	
}
