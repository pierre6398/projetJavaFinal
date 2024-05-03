package groupe3.projetCalzone.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import groupe3.projetCalzone.entities.Pizza;
import groupe3.projetCalzone.enums.BasePizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long>{
	
	List<Pizza> findByNomContaining(String nom);
	
	List<Pizza> findByBase(BasePizza base);

}
