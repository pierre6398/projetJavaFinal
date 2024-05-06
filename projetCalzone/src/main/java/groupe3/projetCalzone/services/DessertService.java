package groupe3.projetCalzone.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupe3.projetCalzone.entities.ComposantDessert;
import groupe3.projetCalzone.entities.ComposantDessertId;
import groupe3.projetCalzone.entities.ComposantDessert;
import groupe3.projetCalzone.entities.Dessert;
import groupe3.projetCalzone.entities.Ingredient;
import groupe3.projetCalzone.entities.Dessert;
import groupe3.projetCalzone.exceptions.DessertException;
import groupe3.projetCalzone.exceptions.NotFoundException;
import groupe3.projetCalzone.exceptions.ReferenceNullException;
import groupe3.projetCalzone.repositories.ComposantDessertRepository;
import groupe3.projetCalzone.repositories.DessertRepository;

@Service
public class DessertService {
	
	@Autowired
	private DessertRepository dessertRepository;
	
	@Autowired
	private ComposantDessertRepository compoDessertRepository;
	
	@Autowired
	private IngredientService ingredientSrv;
	
	private Logger logger = LoggerFactory.getLogger(DessertService.class);
	
	
	// test de l'existence d'un dessert
	private boolean dessertNotNull(Dessert dessert) {
		if (dessert == null) {
			logger.error("dessert null");
			throw new ReferenceNullException("reference null");
		}
		return true;
	}
	
	
	// création d'un dessert grâce au constructeur Dessert(nom, prix)
	public Dessert creation(String nom, Double prix) {
		logger.trace("creation dessert avec String, Double");
		Dessert dessert = new Dessert();
		dessert.setNom(nom);
		dessert.setPrix(prix);
		return creation(dessert);
	}
	
	
	// création d'un dessert grâce à un dessert
	public Dessert creation(Dessert dessert) {
		logger.trace("creation dessert avec Dessert");
		if (dessertNotNull(dessert) && dessert.getNom() == null || dessert.getNom().isBlank()) {
			logger.debug("nom vide");
			// probleme=>RunTimeException
			throw new DessertException("nom dessert obligatoire");
		}
		if(dessert.getPrix() == null) {
			logger.debug("prix vide");
			throw new DessertException("prix dessert obligatoire");
		}
		logger.debug(dessert.getNom());
		
		return dessertRepository.save(dessert);
	}
	
	
	// supprime un dessert
	public void delete(Dessert dessert) {
		if (dessert == null) {
			throw new ReferenceNullException();
		}
		dessertRepository.deleteById(dessert.getId());
	}
	
	
	// modification d'un dessert
	public Dessert update(Dessert dessert) {
		logger.trace("modification de dessert");
		if (dessertNotNull(dessert) && dessert.getNom() == null || dessert.getNom().isBlank()) {
			logger.debug("nom vide");
			// probleme=>RunTimeException
			throw new DessertException("nom dessert obligatoire");
		}
		if(dessert.getPrix() == null) {
			logger.debug("prix vide");
			throw new DessertException("prix dessert obligatoire");
		}
		logger.debug(dessert.getNom());
		
		return dessertRepository.save(dessert);
	}
	
	
	// récupérer la liste des desserts qui contiennent un certain nom
	public List<Dessert> getByNom(String nom){
		if(nom==null || nom.isBlank()) {
			throw new DessertException("nom obligatoire");
		}
		return dessertRepository.findByNomContainingIgnoreCase(nom);
	}
	
	
	//dessert par id
	public Dessert getById(Long id) {
		if (id == null) {
			throw new ReferenceNullException();
		}
		Optional<Dessert> opt = dessertRepository.findById(id);
		if (opt.isEmpty()) {
			throw new NotFoundException("dessert " + id + " non trouvé");
		}
		return opt.get();
	}
	
	//tous les desserts
	public List<Dessert> getAll() {
		return dessertRepository.findAll();
	}
	
	
	//check existance de dessert et ingredient 
	public void checkCompo(Dessert dessert, Ingredient ingredient){
		if (dessert == null || ingredient == null) { 
			throw new ReferenceNullException();
	} ingredientSrv.getById(ingredient.getId());
		this.getById(dessert.getId());
	}
	
	//ajouter un ingrédient dans une dessert
	public void addIngredient(Ingredient ingredient, Dessert dessert) {
        checkCompo(dessert, ingredient);
        ComposantDessert composantDessert = new ComposantDessert();
        composantDessert.setId(new ComposantDessertId(dessert, ingredient));
        compoDessertRepository.save(composantDessert);
	}

	//supprimer un ingrédient dans une dessert			
	public void deleteIngredient(Ingredient ingredient, Dessert dessert) {
        checkCompo(dessert, ingredient);
        compoDessertRepository.deleteById(new ComposantDessertId(dessert, ingredient));	
	}
	
	public boolean checkIngredientInComposantsDessert(Ingredient ingredient, Set<ComposantDessert> composantsDessert){
		for (ComposantDessert composantDessert: composantsDessert) {
			if (composantDessert.getId().getIngredient().equals(ingredient)) {
				return true;
			}
		}
		return false;
	}
	
	public List<Dessert> getByIngredient(Ingredient ingredient){
		Long idIngredient = ingredient.getId();
		if (idIngredient == null) {
			throw new ReferenceNullException();
		}
		List<Dessert> all_desserts = getAll();
		List<Dessert> desserts = new ArrayList<Dessert>();
		for(int i = 0; i<all_desserts.size(); i++) {
			Dessert dessert = all_desserts.get(i);
			if (checkIngredientInComposantsDessert(ingredient,dessert.getComposantsDessert())) {
				desserts.add(dessert);
			}
		}
		return desserts;
	}
}