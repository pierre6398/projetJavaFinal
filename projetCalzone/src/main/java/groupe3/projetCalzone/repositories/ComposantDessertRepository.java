package groupe3.projetCalzone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import groupe3.projetCalzone.entities.ComposantDessert;
import groupe3.projetCalzone.entities.ComposantDessertId;

public interface ComposantDessertRepository extends JpaRepository<ComposantDessert, ComposantDessertId> {

}
