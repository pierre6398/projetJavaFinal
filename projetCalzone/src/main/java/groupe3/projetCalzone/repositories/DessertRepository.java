package groupe3.projetCalzone.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import groupe3.projetCalzone.entities.Dessert;

public interface DessertRepository  extends JpaRepository<Dessert, Long> {
	List<Dessert> findByNomContainingIgnoreCase(String nom);
}
