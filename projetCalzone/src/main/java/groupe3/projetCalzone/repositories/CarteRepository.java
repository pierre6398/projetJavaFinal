package groupe3.projetCalzone.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import groupe3.projetCalzone.entities.Carte;

public interface CarteRepository extends JpaRepository<Carte, Long> {
	List<Carte> findByNomContaining(String nom);
}
