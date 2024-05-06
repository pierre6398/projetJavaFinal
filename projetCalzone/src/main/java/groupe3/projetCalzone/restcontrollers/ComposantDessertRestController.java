package groupe3.projetCalzone.restcontrollers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import groupe3.projetCalzone.dto.requests.ComposantDessertRequest;
import groupe3.projetCalzone.dto.responses.DessertResponse;
import groupe3.projetCalzone.services.DessertService;
import groupe3.projetCalzone.services.IngredientService;

@RestController
@RequestMapping("/composant_dessert")
public class ComposantDessertRestController {
	
private Logger logger = LoggerFactory.getLogger(ComposantDessertRestController.class);
	
	@Autowired
	private DessertService dessertSrv;
	
	@Autowired
	private IngredientService ingredientSrv;
	
	@PostMapping("/add")
	public DessertResponse addIngredient(@RequestBody ComposantDessertRequest request) {
		dessertSrv.addIngredient(ingredientSrv.getById(request.getIdIngredient()),
				dessertSrv.getById(request.getIdDessert()));
		return new DessertResponse(dessertSrv.getByIdWithComposantsDessert(request.getIdDessert()), true);
	}

	@PutMapping("/remove")
	public DessertResponse deleteIngredient(@RequestBody ComposantDessertRequest request) {
		dessertSrv.deleteIngredient(ingredientSrv.getById(request.getIdIngredient()),
				dessertSrv.getById(request.getIdDessert()));
		return new DessertResponse(dessertSrv.getByIdWithComposantsDessert(request.getIdDessert()), true);
	}
}
