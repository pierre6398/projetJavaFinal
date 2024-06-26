package groupe3.projetCalzone.restcontrollers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import groupe3.projetCalzone.dto.requests.PizzaRequest;
import groupe3.projetCalzone.dto.responses.PizzaResponse;
import groupe3.projetCalzone.dto.responses.JsonViews;
import groupe3.projetCalzone.entities.Ingredient;
import groupe3.projetCalzone.entities.Pizza;
import groupe3.projetCalzone.repositories.IngredientRepository;
import groupe3.projetCalzone.services.PizzaService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/pizza")
public class PizzaRestController {

	private Logger logger = LoggerFactory.getLogger(PizzaRestController.class);
	
	@Autowired
	public PizzaService pizzaSrv;
	
	@Autowired
	public IngredientRepository ingredientRepository;
	
	@GetMapping("")
	@JsonView(JsonViews.Pizza.class)
	public List<PizzaResponse> getAll() {
        return pizzaSrv.getAll().stream().map(p -> new PizzaResponse(p,true)).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.Pizza.class)
	public PizzaResponse getById(@PathVariable Long id) {
		return new PizzaResponse(pizzaSrv.getById(id),true);
	}
	
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PizzaResponse create(@Valid @RequestBody PizzaRequest pizzaRequest, BindingResult br) {
		if (br.hasErrors()) {
			logger.info("erreur validation");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Pizza p = new Pizza();
		BeanUtils.copyProperties(pizzaRequest, p);
		return new PizzaResponse(pizzaSrv.creation(p));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		pizzaSrv.delete(id);
	}
	
	@PutMapping("/{id}")
	public PizzaResponse update(@PathVariable Long id, @RequestBody Pizza pizza) {
		pizza.setId(id);
		return new PizzaResponse(pizzaSrv.update(pizza));
	}
	
	@GetMapping("/{id}/ingredients")
	public PizzaResponse getByIdWithComposantsPlat(@PathVariable Long id) {
		return new PizzaResponse(pizzaSrv.getByIdWithComposantsPizza(id),true);
	}
	
	// affiche toutes les pizzas possédant un certain ingrédient
	@GetMapping("/pizzas/{nom_ingredient}")
	public List<PizzaResponse> getPizzasWithIngredient(@PathVariable String nom_ingredient) {
		Ingredient ingredient = ingredientRepository.findByNom(nom_ingredient).get();
		return pizzaSrv.convertListPizzas(pizzaSrv.getByIngredient(ingredient));
	}
}
