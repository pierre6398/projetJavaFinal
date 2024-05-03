package groupe3.projetCalzone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import groupe3.projetCalzone.entities.ComposantEntree;
import groupe3.projetCalzone.entities.ComposantEntreeId;

public interface ComposantEntreeRepository extends JpaRepository<ComposantEntree, ComposantEntreeId> {

}
