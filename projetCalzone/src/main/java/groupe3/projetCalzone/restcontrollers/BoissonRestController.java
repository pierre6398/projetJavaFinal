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

import groupe3.projetCalzone.dto.requests.BoissonRequest;
import groupe3.projetCalzone.dto.responses.BoissonResponse;
import groupe3.projetCalzone.entities.Boisson;
import groupe3.projetCalzone.services.BoissonService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/boisson")
public class BoissonRestController {
	
private Logger logger = LoggerFactory.getLogger(BoissonRestController.class);
	
	@Autowired
	private BoissonService boissonSrv;
	
	// affiche tous les boissons
	@GetMapping("")
	public List<BoissonResponse> getAll() {
		return boissonSrv.getAll().stream().map(p -> new BoissonResponse(p)).collect(Collectors.toList());
	}
	
	// affiche un boisson
	@GetMapping("/{id}")
	public BoissonResponse getById(@PathVariable Long id) {
		return new BoissonResponse(boissonSrv.getById(id));
	}
	
	// crée un boisson
	@PostMapping("")
	@ResponseStatus(code=HttpStatus.CREATED)
	public BoissonResponse create(@Valid @RequestBody BoissonRequest boissonRequest, BindingResult br) {
		if(br.hasErrors()) {
			logger.info("erreur validation");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		logger.info("validation ok");
		Boisson  boisson = new Boisson();
		BeanUtils.copyProperties(boissonRequest, boisson);
		boisson=boissonSrv.creation(boisson);
		return new BoissonResponse(boisson);
	}

	// supprime un boisson
	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		boissonSrv.delete(id);
	}
	
	// modifier un boisson existant
	@PutMapping("/{id}")
	public BoissonResponse update(@PathVariable Long id, @RequestBody Boisson boisson) {
		boisson.setId(id);
		return new BoissonResponse(boissonSrv.update(boisson));
	}
}
