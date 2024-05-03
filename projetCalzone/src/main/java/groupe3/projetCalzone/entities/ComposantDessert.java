package groupe3.projetCalzone.entities;

import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "compo_dessert")
public class ComposantDessert {
	@EmbeddedId
	private ComposantDessertId id;

	public ComposantDessert() {
	}
	
	public ComposantDessert(ComposantDessertId id) {
		this.id = id;
	}

	public ComposantDessertId getId() {
		return id;
	}

	public void setId(ComposantDessertId id) {
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
		ComposantDessert other = (ComposantDessert) obj;
		return Objects.equals(id, other.id);
	}

}
