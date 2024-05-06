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

import groupe3.projetCalzone.dto.requests.PizzaRequest;
import groupe3.projetCalzone.dto.responses.PizzaResponse;
import groupe3.projetCalzone.entities.Pizza;
import groupe3.projetCalzone.services.PizzaService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/pizza")
public class PizzaRestController {

	private Logger logger = LoggerFactory.getLogger(PizzaRestController.class);
	
	@Autowired
	public PizzaService pizzaSrv;
	
	@GetMapping("")
	public List<PizzaResponse> getAll() {
        return pizzaSrv.getAll().stream().map(p -> new PizzaResponse(p)).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public PizzaResponse getById(@PathVariable Long id) {
		return new PizzaResponse(pizzaSrv.getById(id));
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
}
