package groupe3.projetCalzone.entities;

import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "compo_plat")
public class ComposantPlat {
	@EmbeddedId
	private ComposantPlatId id;

	public ComposantPlat() {
	}
	
	public ComposantPlat(ComposantPlatId id) {
		this.id = id;
	}

	public ComposantPlatId getId() {
		return id;
	}

	public void setId(ComposantPlatId id) {
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
		ComposantPlat other = (ComposantPlat) obj;
		return Objects.equals(id, other.id);
	}

}
