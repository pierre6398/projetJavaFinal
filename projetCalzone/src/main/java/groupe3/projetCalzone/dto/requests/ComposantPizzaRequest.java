package groupe3.projetCalzone.dto.requests;

public class ComposantPizzaRequest {
	private Long idPizza;
	private Long idIngredient;
	
	public ComposantPizzaRequest() {
		
	}

	public Long getIdPizza() {
		return idPizza;
	}

	public void setIdPizza(Long idPizza) {
		this.idPizza = idPizza;
	}

	public Long getIdIngredient() {
		return idIngredient;
	}

	public void setIdIngredient(Long idIngredient) {
		this.idIngredient = idIngredient;
	}
}
