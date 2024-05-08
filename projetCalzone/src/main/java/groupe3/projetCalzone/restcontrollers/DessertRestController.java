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

import groupe3.projetCalzone.dto.requests.DessertRequest;
import groupe3.projetCalzone.dto.responses.DessertResponse;
import groupe3.projetCalzone.dto.responses.JsonViews;
import groupe3.projetCalzone.entities.Dessert;
import groupe3.projetCalzone.entities.Ingredient;
import groupe3.projetCalzone.repositories.IngredientRepository;
import groupe3.projetCalzone.services.DessertService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/dessert")
public class DessertRestController {
	
private Logger logger = LoggerFactory.getLogger(DessertRestController.class);
	
	@Autowired
	private DessertService dessertSrv;
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	// affiche tous les desserts
	@GetMapping("")
	@JsonView(JsonViews.Dessert.class)
	public List<DessertResponse> getAll() {
		return dessertSrv.getAll().stream().map(p -> new DessertResponse(p,true)).collect(Collectors.toList());
	}
	
	// affiche un dessert
	@GetMapping("/{id}")
	@JsonView(JsonViews.Dessert.class)
	public DessertResponse getById(@PathVariable Long id) {
		return new DessertResponse(dessertSrv.getById(id),true);
	}
	
	// crée un dessert
	@PostMapping("")
	@ResponseStatus(code=HttpStatus.CREATED)
	public DessertResponse create(@Valid @RequestBody DessertRequest dessertRequest, BindingResult br) {
		if(br.hasErrors()) {
			logger.info("erreur validation");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		logger.info("validation ok");
		Dessert  dessert = new Dessert();
		BeanUtils.copyProperties(dessertRequest, dessert);
		dessert=dessertSrv.creation(dessert);
		return new DessertResponse(dessert);
	}

	// supprime un dessert
	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		dessertSrv.delete(dessertSrv.getById(id));
	}
	
	// modifier un dessert existant
	@PutMapping("/{id}")
	public DessertResponse update(@PathVariable Long id, @RequestBody Dessert dessert) {
		dessert.setId(id);
		return new DessertResponse(dessertSrv.update(dessert));
	}
	
	// affiche un dessert avec ses ingrédients
	@GetMapping("/{id}/ingredients")
	public DessertResponse getByIdWithComposantsDessert(@PathVariable Long id) {
		return new DessertResponse(dessertSrv.getByIdWithComposantsDessert(id), true);
	}
	
	// affiche tous les desserts possédant un certain ingrédient
	@GetMapping("/desserts/{nom_ingredient}")
	public List<DessertResponse> getDessertsWithIngredient(@PathVariable String nom_ingredient) {
		Ingredient ingredient = ingredientRepository.findByNom(nom_ingredient).get();
		return dessertSrv.convertListDesserts(dessertSrv.getByIngredient(ingredient));
	}
}
