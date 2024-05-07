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

import groupe3.projetCalzone.dto.requests.CarteRequest;
import groupe3.projetCalzone.dto.responses.CarteResponse;
import groupe3.projetCalzone.dto.responses.JsonViews;
import groupe3.projetCalzone.entities.Carte;
import groupe3.projetCalzone.services.BoissonService;
import groupe3.projetCalzone.services.CarteService;
import groupe3.projetCalzone.services.DessertService;
import groupe3.projetCalzone.services.EntreeService;
import groupe3.projetCalzone.services.PizzaService;
import groupe3.projetCalzone.services.PlatService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/carte")
public class CarteRestController {

	private Logger logger = LoggerFactory.getLogger(CarteRestController.class);

	@Autowired
	private CarteService carteSrv;
	@Autowired
	private EntreeService entreeSrv;
	@Autowired
	private PlatService platSrv;
	@Autowired
	private PizzaService pizzaSrv;
	@Autowired
	private DessertService dessertSrv;
	@Autowired
	private BoissonService boissonSrv;

	@GetMapping("")
	@JsonView(JsonViews.Basic.class)
	public List<CarteResponse> getAll() {
		return carteSrv.getAll().stream().map(c -> new CarteResponse(c, true)).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	@JsonView(JsonViews.Basic.class)
	public CarteResponse getById(@PathVariable Long id) {
		return new CarteResponse(carteSrv.getById(id),true);
	}

	@PostMapping("")
	@JsonView(JsonViews.Basic.class)
	@ResponseStatus(code = HttpStatus.CREATED)
	public CarteResponse create(@Valid @RequestBody CarteRequest carteRequest, BindingResult br) {
		if (br.hasErrors()) {
			logger.info("erreur validation");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Carte c = new Carte();
		BeanUtils.copyProperties(carteRequest, c);
		return new CarteResponse(carteSrv.creation(c),true);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		carteSrv.delete(id);
	}

	@PutMapping("/{id}")
	@JsonView(JsonViews.Basic.class)
	public CarteResponse update(@PathVariable Long id, @RequestBody Carte carte) {
		carte.setId(id);
		return new CarteResponse(carteSrv.update(carte), true);
	}

	@PostMapping("/add/entree/{id}")
	@JsonView(JsonViews.Basic.class)
	public CarteResponse addEntree(@PathVariable Long id, @RequestBody CarteRequest request) {
		carteSrv.addEntreeToCarte(carteSrv.getById(request.getId()), entreeSrv.getById(id));
		return new CarteResponse(carteSrv.getById(request.getId()),true);
	}

	@PostMapping("/add/plat/{id}")
	@JsonView(JsonViews.Basic.class)
	public CarteResponse addPlat(@PathVariable Long id, @RequestBody CarteRequest request) {
		carteSrv.addPlatToCarte(carteSrv.getById(request.getId()), platSrv.getById(id));
		return new CarteResponse(carteSrv.getById(request.getId()),true);
	}

	@PostMapping("/add/pizza/{id}")
	@JsonView(JsonViews.Basic.class)
	public CarteResponse addPizza(@PathVariable Long id, @RequestBody CarteRequest request) {
		carteSrv.addPizzaToCarte(carteSrv.getById(request.getId()), pizzaSrv.getById(id));
		return new CarteResponse(carteSrv.getById(request.getId()),true);
	}

	@PostMapping("/add/boisson/{id}")
	@JsonView(JsonViews.Basic.class)
	public CarteResponse addBoisson(@PathVariable Long id, @RequestBody CarteRequest request) {
		carteSrv.addBoissonToCarte(carteSrv.getById(request.getId()), boissonSrv.getById(id));
		return new CarteResponse(carteSrv.getById(request.getId()),true);
	}

	@PostMapping("/add/dessert/{id}")
	@JsonView(JsonViews.Basic.class)
	public CarteResponse addDessert(@PathVariable Long id, @RequestBody CarteRequest request) {
		carteSrv.addDessertToCarte(carteSrv.getById(request.getId()), dessertSrv.getById(id));
		return new CarteResponse(carteSrv.getById(request.getId()),true);
	}

	@DeleteMapping("/del/entree/{id}")
	@JsonView(JsonViews.Basic.class)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public CarteResponse deleteEntree(@PathVariable Long id) {
		Carte carte = entreeSrv.getById(id).getCarte();
		carteSrv.deleteEntreeFromCarte(carte, entreeSrv.getById(id));
		return new CarteResponse(carteSrv.getById(carte.getId()),true);
	}

	@DeleteMapping("/del/plat/{id}")
	@JsonView(JsonViews.Basic.class)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public CarteResponse deletePlat(@PathVariable Long id) {
		Carte carte = platSrv.getById(id).getCarte();
		carteSrv.deletePlatFromCarte(carte, platSrv.getById(id));
		return new CarteResponse(carteSrv.getById(carte.getId()),true);
	}

	@DeleteMapping("/del/pizza/{id}")
	@JsonView(JsonViews.Basic.class)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public CarteResponse deletePizza(@PathVariable Long id) {
		Carte carte = pizzaSrv.getById(id).getCarte();
		carteSrv.deletePizzaFromCarte(carte, pizzaSrv.getById(id));
		return new CarteResponse(carteSrv.getById(carte.getId()),true);
	}

	@DeleteMapping("/del/boisson/{id}")
	@JsonView(JsonViews.Basic.class)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public CarteResponse deleteBoisson(@PathVariable Long id) {
		Carte carte = boissonSrv.getById(id).getCarte();
		carteSrv.deleteBoissonFromCarte(carte, boissonSrv.getById(id));
		return new CarteResponse(carteSrv.getById(carte.getId()),true);
	}

	@DeleteMapping("/del/dessert/{id}")
	@JsonView(JsonViews.Basic.class)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public CarteResponse deleteDessert(@PathVariable Long id) {
		Carte carte = dessertSrv.getById(id).getCarte();
		carteSrv.deleteDessertFromCarte(carte, dessertSrv.getById(id));
		return new CarteResponse(carteSrv.getById(carte.getId()),true);
	}
}
