package groupe3.projetCalzone.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import groupe3.projetCalzone.entities.Dessert;

public interface DessertRepository  extends JpaRepository<Dessert, Long> {
	List<Dessert> findByNomContainingIgnoreCase(String nom);

	@Query("select d from Dessert d left join fetch d.composantsDessert where d.id=:id")
	Optional<Dessert> findByIdFetchComposantsDessert(@Param("id") Long id);

}
