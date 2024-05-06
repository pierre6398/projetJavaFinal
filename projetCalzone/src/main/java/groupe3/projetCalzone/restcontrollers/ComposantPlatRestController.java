package groupe3.projetCalzone.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import groupe3.projetCalzone.dto.requests.ComposantPlatRequest;
import groupe3.projetCalzone.dto.responses.PlatResponse;
import groupe3.projetCalzone.services.IngredientService;
import groupe3.projetCalzone.services.PlatService;


@RestController
@RequestMapping("/composant_plat")
public class ComposantPlatRestController {
	@Autowired
	private PlatService platSrv;
	
	@Autowired
	private IngredientService ingredientSrv;
	
	@PostMapping("/add")
	public PlatResponse addIngredient(@RequestBody ComposantPlatRequest request) {
		platSrv.addIngredient(ingredientSrv.getById(request.getIdIngredient()),platSrv.getById(request.getIdPlat()));
		
	return new PlatResponse(platSrv.getByIdWithComposantsPlat(request.getIdPlat()),true);
	}
	
	@PutMapping("/remove")
	public PlatResponse deleteIngredient(@RequestBody ComposantPlatRequest request) {
		platSrv.deleteIngredient(ingredientSrv.getById(request.getIdIngredient()),platSrv.getById(request.getIdPlat()));
		
	return new PlatResponse(platSrv.getByIdWithComposantsPlat(request.getIdPlat()),true);
	}
}
