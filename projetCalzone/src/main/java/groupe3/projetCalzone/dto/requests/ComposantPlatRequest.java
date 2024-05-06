package groupe3.projetCalzone.dto.requests;

public class ComposantPlatRequest {
	private Long idPlat;
	private Long idIngredient;
	
	public ComposantPlatRequest() {
		
	}

	public Long getIdPlat() {
		return idPlat;
	}

	public void setIdPlat(Long idPlat) {
		this.idPlat = idPlat;
	}

	public Long getIdIngredient() {
		return idIngredient;
	}

	public void setIdIngredient(Long idIngredient) {
		this.idIngredient = idIngredient;
	}
}
