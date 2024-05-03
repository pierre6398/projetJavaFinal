package groupe3.projetCalzone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import groupe3.projetCalzone.entities.ComposantPlat;
import groupe3.projetCalzone.entities.ComposantPlatId;

public interface ComposantPlatRepository extends JpaRepository<ComposantPlat, ComposantPlatId> {

}
