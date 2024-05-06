package groupe3.projetCalzone.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import groupe3.projetCalzone.entities.Ingredient;
import groupe3.projetCalzone.entities.Plat;

public interface PlatRepository extends JpaRepository<Plat, Long> {
	List<Plat> findByNomContainingIgnoreCase(String nom);
	
	@Query("select p from Plat p left join fetch p.composantsPlat where p.id=:id")
	Optional<Plat> findByIdFetchComposantsPlat(@Param("id") Long id);
}
