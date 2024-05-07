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

import groupe3.projetCalzone.dto.requests.EntreeRequest;
import groupe3.projetCalzone.dto.responses.EntreeResponse;
import groupe3.projetCalzone.dto.responses.JsonViews;
import groupe3.projetCalzone.entities.Entree;
import groupe3.projetCalzone.services.EntreeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/entree")
public class EntreeRestController {
	
	private Logger logger = LoggerFactory.getLogger(PizzaRestController.class);
	
	@Autowired
	public EntreeService entreeSrv;
	
	@GetMapping("")
	@JsonView(JsonViews.Basic.class)
	public List<EntreeResponse> getAll() {
        return entreeSrv.getAll().stream().map(p -> new EntreeResponse(p)).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.Basic.class)
	public EntreeResponse getById(@PathVariable Long id) {
		return new EntreeResponse(entreeSrv.getById(id));
	}
	
	@PostMapping("")
	@JsonView(JsonViews.Basic.class)
	@ResponseStatus(code = HttpStatus.CREATED)
	public EntreeResponse create(@Valid @RequestBody EntreeRequest entreeRequest, BindingResult br) {
		if (br.hasErrors()) {
			logger.info("erreur validation");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Entree e = new Entree();
		BeanUtils.copyProperties(entreeRequest, e);
		return new EntreeResponse(entreeSrv.creation(e));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		entreeSrv.delete(id);
	}

	@PutMapping("/{id}")
	public EntreeResponse update(@PathVariable Long id, @RequestBody Entree entree) {
		entree.setId(id);
		return new EntreeResponse(entreeSrv.update(entree));
	}
	
	// affiche une entrée avec ses ingrédients
		@GetMapping("/{id}/ingredients")
		public EntreeResponse getByIdWithComposantsEntree(@PathVariable Long id) {
			return new EntreeResponse(entreeSrv.getByIdWithComposantsEntree(id), true);
		}
	
}
