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

import groupe3.projetCalzone.dto.requests.IngredientRequest;
import groupe3.projetCalzone.dto.responses.DessertResponse;
import groupe3.projetCalzone.dto.responses.EntreeResponse;
import groupe3.projetCalzone.dto.responses.IngredientResponse;
import groupe3.projetCalzone.dto.responses.PizzaResponse;
import groupe3.projetCalzone.dto.responses.PlatResponse;
import groupe3.projetCalzone.entities.Ingredient;
import groupe3.projetCalzone.services.DessertService;
import groupe3.projetCalzone.services.EntreeService;
import groupe3.projetCalzone.services.IngredientService;
import groupe3.projetCalzone.services.PizzaService;
import groupe3.projetCalzone.services.PlatService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ingredient")
public class IngredientRestController {
	
	private Logger logger = LoggerFactory.getLogger(IngredientRestController.class);
	
	@Autowired
	private IngredientService ingredientSrv;
	
	@Autowired
	private PizzaService pizzaSrv;
	
	@Autowired
	private PlatService platSrv;
	
	@Autowired
	private EntreeService entreeSrv;
	
	@Autowired
	private DessertService dessertSrv;
	
	// affiche tous les ingrédients
	@GetMapping("")
	public List<IngredientResponse> getAll() {
		return ingredientSrv.getAll().stream().map(i -> new IngredientResponse(i)).collect(Collectors.toList());
	}
	
	// affiche un ingredient
	@GetMapping("/{id}")
	public IngredientResponse getById(@PathVariable Long id) {
		return new IngredientResponse(ingredientSrv.getById(id));
	}
	
	// crée un ingredient
	@PostMapping("")
	@ResponseStatus(code=HttpStatus.CREATED)
	public IngredientResponse create(@Valid @RequestBody IngredientRequest ingredientRequest, BindingResult br) {
		if(br.hasErrors()) {
			logger.info("erreur validation");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		logger.info("validation ok");
		Ingredient  ingredient = new Ingredient();
		BeanUtils.copyProperties(ingredientRequest, ingredient);
		ingredient=ingredientSrv.creation(ingredient);
		return new IngredientResponse(ingredient);
	}

	// supprime un ingredient
	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		ingredientSrv.delete(id);
	}
	
	// modifier un ingredient existant
	@PutMapping("/{id}")
	public IngredientResponse update(@PathVariable Long id, @RequestBody Ingredient ingredient) {
		ingredient.setId(id);
		return new IngredientResponse(ingredientSrv.update(ingredient));
	}
	
	@GetMapping("/pizza")
	public List<PizzaResponse> getPizzasByIngredient(@RequestBody Ingredient ingredient){
		return pizzaSrv.getByIngredient(ingredient).stream().map(i -> new PizzaResponse(i)).collect(Collectors.toList());
	}
	
	@GetMapping("/plat")
	public List<PlatResponse> getPlatByIngredient(@RequestBody Ingredient ingredient){
		return platSrv.getByIngredient(ingredient).stream().map(i -> new PlatResponse(i)).collect(Collectors.toList());
	}
	
	@GetMapping("/entree")
	public List<EntreeResponse> getEntreeByIngredient(@RequestBody Ingredient ingredient){
		return entreeSrv.getByIngredient(ingredient).stream().map(i -> new EntreeResponse(i)).collect(Collectors.toList());
	}
	
	@GetMapping("/dessert")
	public List<DessertResponse> getDessertByIngredient(@RequestBody Ingredient ingredient){
		return dessertSrv.getByIngredient(ingredient).stream().map(i -> new DessertResponse(i)).collect(Collectors.toList());
	}
}
