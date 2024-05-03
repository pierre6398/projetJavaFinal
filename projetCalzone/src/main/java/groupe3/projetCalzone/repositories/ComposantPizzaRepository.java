package groupe3.projetCalzone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import groupe3.projetCalzone.entities.ComposantPizza;
import groupe3.projetCalzone.entities.ComposantPizzaId;

public interface ComposantPizzaRepository extends JpaRepository<ComposantPizza, ComposantPizzaId> {

}
