package groupe3.projetCalzone.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupe3.projetCalzone.entities.Ingredient;
import groupe3.projetCalzone.entities.TypeIngredient;
import groupe3.projetCalzone.exceptions.IngredientException;
import groupe3.projetCalzone.exceptions.NotFoundException;
import groupe3.projetCalzone.exceptions.ReferenceNullException;
import groupe3.projetCalzone.repositories.IngredientRepository;

@Service
public class IngredientService {
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	//creation d'un ingredient
			public Ingredient creation(String nom, TypeIngredient type) {
				Ingredient ingredient = new Ingredient();
				ingredient.setNom(nom);
				ingredient.setType(type);
				return creation(ingredient);
			}
			
			public Ingredient creation(Ingredient ingredient) {
				if (ingredient == null) {
					throw new ReferenceNullException();
				}
				if (ingredient.getNom() == null || ingredient.getNom().isBlank()) {
					throw new IngredientException("nom obligatoire");
				}
				return ingredientRepository.save(ingredient);
			}
	
	
	//ingredient par id
			public Ingredient getById(Long id) {
				if (id == null) {
					throw new ReferenceNullException();
				}
				Optional<Ingredient> opt = ingredientRepository.findById(id);
				if (opt.isEmpty()) {
					throw new NotFoundException("ingrédient " + id + " non trouvé");
				}
				return opt.get();
			}
}
