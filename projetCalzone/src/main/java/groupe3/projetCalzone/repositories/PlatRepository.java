package groupe3.projetCalzone.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import groupe3.projetCalzone.entities.Plat;

public interface PlatRepository  extends JpaRepository<Plat, Long> {
	List<Plat> findByNomContainingIgnoreCase(String nom);
}
