package groupe3.projetCalzone.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupe3.projetCalzone.entities.ComposantPizza;
import groupe3.projetCalzone.entities.ComposantPizzaId;
import groupe3.projetCalzone.entities.Ingredient;
import groupe3.projetCalzone.entities.Pizza;
import groupe3.projetCalzone.enums.BasePizza;
import groupe3.projetCalzone.exceptions.NotFoundException;
import groupe3.projetCalzone.exceptions.PizzaException;
import groupe3.projetCalzone.exceptions.ReferenceNullException;
import groupe3.projetCalzone.repositories.ComposantPizzaRepository;
import groupe3.projetCalzone.repositories.PizzaRepository;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepository pizzaRepository;

	@Autowired
	private ComposantPizzaRepository compoPizzaRepository;

	@Autowired
	private IngredientService ingredientSrv;

	private Logger logger = LoggerFactory.getLogger(PizzaService.class);

	// creation d'une pizza
	public Pizza creation(String nom, Double prix) {
		Pizza pizza = new Pizza();
		pizza.setNom(nom);
		pizza.setPrix(prix);
		return creation(pizza);
	}

	// creation d'une pizza
	public Pizza creation(Pizza pizza) {
		if (pizza == null) {
			throw new ReferenceNullException();
		}
		if (pizza.getNom() == null || pizza.getNom().isBlank()) {
			throw new PizzaException("nom obligatoire");
		}
		return pizzaRepository.save(pizza);
	}

	// liste de toutes les pizza
	public List<Pizza> getAll() {
		return pizzaRepository.findAll();
	}

	// pizza par id
	public Pizza getById(Long id) {
		if (id == null) {
			throw new ReferenceNullException();
		}
		Optional<Pizza> opt = pizzaRepository.findById(id);
		if (opt.isEmpty()) {
			throw new NotFoundException("pizza " + id + " non trouvée");
		}
		return opt.get();
	}

	// pizza par nom
	public List<Pizza> getByNom(String nom) {
		if (nom == null) {
			throw new ReferenceNullException();
		}
		List<Pizza> list = pizzaRepository.findByNomContaining(nom);
		if (list.isEmpty()) {
			throw new NotFoundException("pizza " + nom + " non trouvée");
		}
		return list;
	}

	// pizza par base
	public List<Pizza> getByBase(BasePizza base) {
		if (base == null) {
			throw new ReferenceNullException();
		}
		List<Pizza> list = pizzaRepository.findByBase(base);
		if (list.isEmpty()) {
			throw new NotFoundException("base " + base + " non trouvée");
		}
		return list;
	}

	// suppr pizza
	public void delete(Long id) {
		if (id == null) {
			throw new ReferenceNullException();
		}
		logger.trace("pizza supprimée (pas bon appetit)");
		pizzaRepository.deleteById(id);
	}

	// modif pizza
	public void update(Pizza pizza, String nvNom, Double nvPrix, Integer nvTva, BasePizza nvBase, String nvPhoto) {
		pizza.setNom(nvNom);
		pizza.setPrix(nvPrix);
		pizza.setTva(nvTva);
		pizza.setBase(nvBase);
		pizza.setPhoto(nvPhoto);
		pizzaRepository.save(pizza);
	}

	public Pizza update(Pizza pizza) {
		return pizzaRepository.save(pizza);
	}

	// check existance de pizza et ingredient
	public void checkCompo(Pizza pizza, Ingredient ingredient) {
		if (pizza == null || ingredient == null) {
			throw new ReferenceNullException();
		}
		ingredientSrv.getById(ingredient.getId());
		this.getById(pizza.getId());
	}

	// ajouter un ingrédient dans une pizza
	public void addIngredient(Ingredient ingredient, Pizza pizza) {
		checkCompo(pizza, ingredient);
		ComposantPizza composantPizza = new ComposantPizza();
		composantPizza.setId(new ComposantPizzaId(pizza, ingredient));
		compoPizzaRepository.save(composantPizza);
	}

	// supprimer un ingrédient dans une pizza
	public void deleteIngredient(Ingredient ingredient, Pizza pizza) {
		checkCompo(pizza, ingredient);
		compoPizzaRepository.deleteById(new ComposantPizzaId(pizza, ingredient));
	}
	
		
	public Pizza getByIdWithComposantsPizza(Long id) {
		if (id == null) {
			throw new ReferenceNullException();
		}
		return pizzaRepository.findByIdFetchComposantsPizza(id).orElseThrow(() -> {
			throw new NotFoundException("pizza " + id + " inexistante");
		});
	}
	
	public boolean checkIngredientInComposantsPizza(Ingredient ingredient, Set<ComposantPizza> composantsPizza){
		for (ComposantPizza composantPizza: composantsPizza) {
			if (composantPizza.getId().getIngredient().equals(ingredient)) {
				return true;
			}
		}
		return false;
	}
	
	public List<Pizza> getByIngredient(Ingredient ingredient){
		Long idIngredient = ingredient.getId();
		if (idIngredient == null) {
			throw new ReferenceNullException();
		}
		List<Pizza> all_pizzas = getAll();
		List<Pizza> pizzas = new ArrayList<Pizza>();
		for(int i = 0; i<all_pizzas.size(); i++) {
			Pizza pizza = all_pizzas.get(i);
			if (checkIngredientInComposantsPizza(ingredient,pizza.getComposantsPizza())) {
				pizzas.add(pizza);
			}
		}
		return pizzas;
	}
}