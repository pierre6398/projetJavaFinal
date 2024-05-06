package groupe3.projetCalzone.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import groupe3.projetCalzone.entities.Pizza;
import groupe3.projetCalzone.enums.BasePizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long>{
	
	List<Pizza> findByNomContaining(String nom);
	
	List<Pizza> findByBase(BasePizza base);
	
	List<Pizza> findByPrixBetween(double prixMin, double prixMax);
	
	@Query("select p from Pizza p left join fetch p.composantsPizza where p.id=:id")
	Optional<Pizza> findByIdFetchComposantsPizza(@Param("id") Long id);
}
