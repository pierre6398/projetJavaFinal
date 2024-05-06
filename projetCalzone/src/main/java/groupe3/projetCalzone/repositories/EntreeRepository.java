package groupe3.projetCalzone.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import groupe3.projetCalzone.entities.Entree;

public interface EntreeRepository extends JpaRepository<Entree, Long>{
	
	List<Entree> findByNomContaining(String nom);

	@Query("select e from Entree e left join fetch e.composantsEntree where e.id=:id")
	Optional<Entree> findByIdFetchComposantsEntree(@Param("id") Long id);

}
