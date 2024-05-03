package groupe3.projetCalzone.entities;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Embeddable
public class ComposantPizzaId {

	@ManyToOne
	@JoinColumn(name = "pizza_id", foreignKey = @ForeignKey(name = "compopizza_pizza_id_fk"))
	private Pizza pizza;
	@ManyToOne
	@JoinColumn(name = "ingredient_id", foreignKey = @ForeignKey(name = "compopizza_ingredient_id_fk"))
	private Ingredient ingredient;
	
	
	public ComposantPizzaId() {
	}


	public ComposantPizzaId(Pizza pizza, Ingredient ingredient) {
		this.pizza = pizza;
		this.ingredient = ingredient;
	}


	public Pizza getPizza() {
		return pizza;
	}


	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}


	public Ingredient getIngredient() {
		return ingredient;
	}


	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}


	@Override
	public int hashCode() {
		return Objects.hash(ingredient, pizza);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComposantPizzaId other = (ComposantPizzaId) obj;
		return Objects.equals(ingredient, other.ingredient) && Objects.equals(pizza, other.pizza);
	}
	
}
