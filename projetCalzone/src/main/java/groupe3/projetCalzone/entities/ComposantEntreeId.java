package groupe3.projetCalzone.entities;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Embeddable
public class ComposantEntreeId {

	@ManyToOne
	@JoinColumn(name = "entree_id", foreignKey = @ForeignKey(name = "compoentree_entree_id_fk"))
	private Entree entree;
	@ManyToOne
	@JoinColumn(name = "ingredient_id", foreignKey = @ForeignKey(name = "compoentree_ingredient_id_fk"))
	private Ingredient ingredient;
	
	
	public ComposantEntreeId() {
	}


	public ComposantEntreeId(Entree entree, Ingredient ingredient) {
		this.entree = entree;
		this.ingredient = ingredient;
	}


	public Entree getEntree() {
		return entree;
	}


	public void setEntree(Entree entree) {
		this.entree = entree;
	}


	public Ingredient getIngredient() {
		return ingredient;
	}


	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}


	@Override
	public int hashCode() {
		return Objects.hash(ingredient, entree);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComposantEntreeId other = (ComposantEntreeId) obj;
		return Objects.equals(ingredient, other.ingredient) && Objects.equals(entree, other.entree);
	}
	
}
