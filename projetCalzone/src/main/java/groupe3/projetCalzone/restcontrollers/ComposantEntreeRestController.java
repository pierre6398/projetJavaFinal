package groupe3.projetCalzone.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import groupe3.projetCalzone.dto.requests.ComposantEntreeRequest;
import groupe3.projetCalzone.dto.responses.EntreeResponse;
import groupe3.projetCalzone.services.EntreeService;
import groupe3.projetCalzone.services.IngredientService;

@RestController
@RequestMapping("/composant_entree")
public class ComposantEntreeRestController {
	
	@Autowired
	private EntreeService entreeSrv;
	
	@Autowired
	private IngredientService ingredientSrv;
	
	@PostMapping("/add")
	public EntreeResponse addIngredient(@RequestBody ComposantEntreeRequest request) {
		entreeSrv.addIngredient(ingredientSrv.getById(request.getIdIngredient()),
				entreeSrv.getById(request.getIdEntree()));
		return new EntreeResponse(entreeSrv.getByIdWithComposantsEntree(request.getIdEntree()), true);
	}

	@PutMapping("/remove")
	public EntreeResponse deleteIngredient(@RequestBody ComposantEntreeRequest request) {
		entreeSrv.deleteIngredient(ingredientSrv.getById(request.getIdIngredient()),
				entreeSrv.getById(request.getIdEntree()));
		return new EntreeResponse(entreeSrv.getByIdWithComposantsEntree(request.getIdEntree()), true);
	}
}
