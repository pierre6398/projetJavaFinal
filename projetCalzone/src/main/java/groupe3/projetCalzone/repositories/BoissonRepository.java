package groupe3.projetCalzone.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import groupe3.projetCalzone.entities.Boisson;

public interface BoissonRepository extends JpaRepository<Boisson, Long> {
	
	List<Boisson> findByNomContaining(String nom);
	
	List<Boisson> findByAlcool(Boolean alcool);
}
