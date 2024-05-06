package groupe3.projetCalzone.services;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupe3.projetCalzone.entities.ComposantPlat;
import groupe3.projetCalzone.entities.ComposantPlatId;
import groupe3.projetCalzone.entities.Ingredient;
import groupe3.projetCalzone.entities.Plat;
import groupe3.projetCalzone.exceptions.NotFoundException;
import groupe3.projetCalzone.exceptions.PlatException;
import groupe3.projetCalzone.exceptions.ReferenceNullException;
import groupe3.projetCalzone.repositories.ComposantPlatRepository;
import groupe3.projetCalzone.repositories.PlatRepository;

@Service
public class PlatService {
	
	@Autowired
	private PlatRepository platRepository;
	
	@Autowired
	private ComposantPlatRepository compoPlatRepository;
	
	@Autowired
	private IngredientService ingredientSrv;
	
	private Logger logger = LoggerFactory.getLogger(PlatService.class);
	
	
	// test de l'existence d'un plat
	private boolean platNotNull(Plat plat) {
		if (plat == null) {
			logger.error("plat null");
			throw new ReferenceNullException("reference null");
		}
		return true;
	}
	
	
	// création d'un plat grâce au constructeur Plat(nom, prix)
	public Plat creation(String nom, Double prix) {
		logger.trace("creation plat avec String, Double");
		Plat plat = new Plat();
		plat.setNom(nom);
		plat.setPrix(prix);
		return creation(plat);
	}
	
	
	// création d'un plat grâce à un plat
	public Plat creation(Plat plat) {
		logger.trace("creation plat avec Plat");
		if (platNotNull(plat) && plat.getNom() == null || plat.getNom().isBlank()) {
			logger.debug("nom vide");
			// probleme=>RunTimeException
			throw new PlatException("nom plat obligatoire");
		}
		if(plat.getPrix() == null) {
			logger.debug("prix vide");
			throw new PlatException("prix plat obligatoire");
		}
		logger.debug(plat.getNom());
		
		return platRepository.save(plat);
	}
	
	
	// supprime un plat
	public void delete(Plat plat) {
		if (plat == null) {
			throw new ReferenceNullException();
		}
		platRepository.deleteById(plat.getId());
	}
	
	
	// modification d'un plat
	public Plat update(Plat plat) {
		logger.trace("modification de plat");
		if (platNotNull(plat) && plat.getNom() == null || plat.getNom().isBlank()) {
			logger.debug("nom vide");
			// probleme=>RunTimeException
			throw new PlatException("nom plat obligatoire");
		}
		if(plat.getPrix() == null) {
			logger.debug("prix vide");
			throw new PlatException("prix plat obligatoire");
		}
		logger.debug(plat.getNom());
		
		return platRepository.save(plat);
	}
	
	
	// récupérer la liste des plats qui contiennent un certain nom
	public List<Plat> getByNom(String nom){
		if(nom==null || nom.isBlank()) {
			throw new PlatException("nom obligatoire");
		}
		return platRepository.findByNomContainingIgnoreCase(nom);
	}
	
	//plat par id
	public Plat getById(Long id) {
		if (id == null) {
			throw new ReferenceNullException();
		}
		Optional<Plat> opt = platRepository.findById(id);
		if (opt.isEmpty()) {
			throw new NotFoundException("plat " + id + " non trouvé");
		}
		return opt.get();
	}
	
	//tous les plats
	public List<Plat> getAll() {
		return platRepository.findAll();
	}
	
	//check existance de plat et ingredient 
	public void checkCompo(Plat plat, Ingredient ingredient){
		if (plat == null || ingredient == null) { 
			throw new ReferenceNullException();
	} ingredientSrv.getById(ingredient.getId());
		this.getById(plat.getId());
	}
	
	//ajouter un ingrédient dans une plat
	public void addIngredient(Ingredient ingredient, Plat plat) {
        checkCompo(plat, ingredient);
        ComposantPlat composantPlat = new ComposantPlat();
        composantPlat.setId(new ComposantPlatId(plat, ingredient));
        compoPlatRepository.save(composantPlat);
	}

	//supprimer un ingrédient dans une plat			
	public void deleteIngredient(Ingredient ingredient, Plat plat) {
        checkCompo(plat, ingredient);
        compoPlatRepository.deleteById(new ComposantPlatId(plat, ingredient));	
	}
	
	
	public Plat getByIdWithComposantsPlat(Long id) {
		if (id == null) {
			throw new ReferenceNullException();
		}
		return platRepository.findByIdFetchComposantsPlat(id).orElseThrow(() -> {
			throw new NotFoundException("plat " + id + " inexistant");
		});
	}
}
