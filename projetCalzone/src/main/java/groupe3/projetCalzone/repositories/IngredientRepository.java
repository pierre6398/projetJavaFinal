package groupe3.projetCalzone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import groupe3.projetCalzone.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
