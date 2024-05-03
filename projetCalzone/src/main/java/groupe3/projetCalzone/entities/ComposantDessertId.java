package groupe3.projetCalzone.entities;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Embeddable
public class ComposantDessertId {

	@ManyToOne
	@JoinColumn(name = "dessert_id", foreignKey = @ForeignKey(name = "compodessert_dessert_id_fk"))
	private Dessert dessert;
	@ManyToOne
	@JoinColumn(name = "ingredient_id", foreignKey = @ForeignKey(name = "compodessert_ingredient_id_fk"))
	private Ingredient ingredient;
	
	
	public ComposantDessertId() {
	}


	public ComposantDessertId(Dessert dessert, Ingredient ingredient) {
		this.dessert = dessert;
		this.ingredient = ingredient;
	}


	public Dessert getDessert() {
		return dessert;
	}


	public void setDessert(Dessert dessert) {
		this.dessert = dessert;
	}


	public Ingredient getIngredient() {
		return ingredient;
	}


	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}


	@Override
	public int hashCode() {
		return Objects.hash(ingredient, dessert);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComposantDessertId other = (ComposantDessertId) obj;
		return Objects.equals(ingredient, other.ingredient) && Objects.equals(dessert, other.dessert);
	}
	
}
