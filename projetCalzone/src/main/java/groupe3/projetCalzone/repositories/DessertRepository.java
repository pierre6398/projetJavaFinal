package groupe3.projetCalzone.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import groupe3.projetCalzone.entities.Dessert;
import groupe3.projetCalzone.entities.Plat;

public interface DessertRepository  extends JpaRepository<Dessert, Long> {
	List<Dessert> findByNomContainingIgnoreCase(String nom);
}
