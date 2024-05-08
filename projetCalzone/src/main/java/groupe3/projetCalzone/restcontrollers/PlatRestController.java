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

import groupe3.projetCalzone.dto.requests.PlatRequest;
import groupe3.projetCalzone.dto.responses.PlatResponse;
import groupe3.projetCalzone.dto.responses.JsonViews;
import groupe3.projetCalzone.entities.Ingredient;
import groupe3.projetCalzone.entities.Plat;
import groupe3.projetCalzone.repositories.IngredientRepository;
import groupe3.projetCalzone.services.PlatService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/plat")
public class PlatRestController {
	
private Logger logger = LoggerFactory.getLogger(PlatRestController.class);
	
	@Autowired
	private PlatService platSrv;
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	// affiche tous les plats
	@GetMapping("")
	@JsonView(JsonViews.Plat.class)
	public List<PlatResponse> getAll() {
		return platSrv.getAll().stream().map(p -> new PlatResponse(p,true)).collect(Collectors.toList());
	}
	
	// affiche un plat
	@GetMapping("/{id}")
	@JsonView(JsonViews.Plat.class)
	public PlatResponse getById(@PathVariable Long id) {
		return new PlatResponse(platSrv.getById(id),true);
	}
	
	// crée un plat
	@PostMapping("")
	@ResponseStatus(code=HttpStatus.CREATED)
	public PlatResponse create(@Valid @RequestBody PlatRequest platRequest, BindingResult br) {
		if(br.hasErrors()) {
			logger.info("erreur validation");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		logger.info("validation ok");
		Plat  plat = new Plat();
		BeanUtils.copyProperties(platRequest, plat);
		plat=platSrv.creation(plat);
		return new PlatResponse(plat);
	}

	// supprime un plat
	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		platSrv.delete(platSrv.getById(id));
	}
	
	// modifier un plat existant
	@PutMapping("/{id}")
	public PlatResponse update(@PathVariable Long id, @RequestBody Plat plat) {
		plat.setId(id);
		return new PlatResponse(platSrv.update(plat));
	}
	
	@GetMapping("/{id}/ingredients")
	public PlatResponse getByIdWithComposantsPlat(@PathVariable Long id) {
		return new PlatResponse(platSrv.getByIdWithComposantsPlat(id),true);
	}
	
	// affiche tous les plats possédant un certain ingrédient
	@GetMapping("/plats/{nom_ingredient}")
	public List<PlatResponse> getPlatsWithIngredient(@PathVariable String nom_ingredient) {
		Ingredient ingredient = ingredientRepository.findByNom(nom_ingredient).get();
		return platSrv.convertListPlats(platSrv.getByIngredient(ingredient));
	}
}
