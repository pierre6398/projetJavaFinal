package groupe3.projetCalzone.entities;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Embeddable
public class ComposantPlatId {

	@ManyToOne
	@JoinColumn(name = "plat_id", foreignKey = @ForeignKey(name = "compoplat_plat_id_fk"))
	private Plat plat;
	@ManyToOne
	@JoinColumn(name = "ingredient_id", foreignKey = @ForeignKey(name = "compoplat_ingredient_id_fk"))
	private Ingredient ingredient;
	
	
	public ComposantPlatId() {
	}


	public ComposantPlatId(Plat plat, Ingredient ingredient) {
		this.plat = plat;
		this.ingredient = ingredient;
	}


	public Plat getPlat() {
		return plat;
	}


	public void setPlat(Plat plat) {
		this.plat = plat;
	}


	public Ingredient getIngredient() {
		return ingredient;
	}


	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}


	@Override
	public int hashCode() {
		return Objects.hash(ingredient, plat);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComposantPlatId other = (ComposantPlatId) obj;
		return Objects.equals(ingredient, other.ingredient) && Objects.equals(plat, other.plat);
	}
	
}
