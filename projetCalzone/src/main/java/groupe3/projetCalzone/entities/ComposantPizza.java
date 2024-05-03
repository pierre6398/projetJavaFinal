package groupe3.projetCalzone.entities;

import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "compo_pizza")
public class ComposantPizza {
	@EmbeddedId
	private ComposantPizzaId id;

	public ComposantPizza() {
	}
	
	public ComposantPizza(ComposantPizzaId id) {
		this.id = id;
	}

	public ComposantPizzaId getId() {
		return id;
	}

	public void setId(ComposantPizzaId id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComposantPizza other = (ComposantPizza) obj;
		return Objects.equals(id, other.id);
	}

}
