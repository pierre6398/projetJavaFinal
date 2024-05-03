package groupe3.projetCalzone.entities;

import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "compo_entree")
public class ComposantEntree {
	@EmbeddedId
	private ComposantEntreeId id;

	public ComposantEntree() {
	}
	
	public ComposantEntree(ComposantEntreeId id) {
		this.id = id;
	}

	public ComposantEntreeId getId() {
		return id;
	}

	public void setId(ComposantEntreeId id) {
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
		ComposantEntree other = (ComposantEntree) obj;
		return Objects.equals(id, other.id);
	}

}
