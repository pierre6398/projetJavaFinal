package groupe3.projetCalzone.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import groupe3.projetCalzone.entities.Entree;

public interface EntreeRepository extends JpaRepository<Entree, Long>{
	
	List<Entree> findByNomContaining(String nom);

}
