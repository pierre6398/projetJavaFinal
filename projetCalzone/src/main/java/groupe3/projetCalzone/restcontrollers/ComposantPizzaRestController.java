package groupe3.projetCalzone.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import groupe3.projetCalzone.dto.requests.ComposantPizzaRequest;
import groupe3.projetCalzone.dto.responses.PizzaResponse;
import groupe3.projetCalzone.services.IngredientService;
import groupe3.projetCalzone.services.PizzaService;

@RestController
@RequestMapping("/composant_pizza")
public class ComposantPizzaRestController {
	@Autowired
	private PizzaService pizzaSrv;
	
	@Autowired
	private IngredientService ingredientSrv;
	
	@PostMapping("/add")
	public PizzaResponse addIngredient(@RequestBody ComposantPizzaRequest request) {
		pizzaSrv.addIngredient(ingredientSrv.getById(request.getIdIngredient()),pizzaSrv.getById(request.getIdPizza()));
		
	return new PizzaResponse(pizzaSrv.getByIdWithComposantsPizza(request.getIdPizza()),true);
	}
	
	@PutMapping("/remove")
	public PizzaResponse deleteIngredient(@RequestBody ComposantPizzaRequest request) {
		pizzaSrv.deleteIngredient(ingredientSrv.getById(request.getIdIngredient()),pizzaSrv.getById(request.getIdPizza()));
		
	return new PizzaResponse(pizzaSrv.getByIdWithComposantsPizza(request.getIdPizza()),true);
	}
}
