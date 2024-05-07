package groupe3.projetCalzone.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupe3.projetCalzone.entities.ComposantEntree;
import groupe3.projetCalzone.entities.ComposantEntreeId;
import groupe3.projetCalzone.entities.Entree;
import groupe3.projetCalzone.entities.Ingredient;
import groupe3.projetCalzone.exceptions.EntreeException;
import groupe3.projetCalzone.exceptions.NotFoundException;
import groupe3.projetCalzone.exceptions.ReferenceNullException;
import groupe3.projetCalzone.repositories.ComposantEntreeRepository;
import groupe3.projetCalzone.repositories.EntreeRepository;

@Service
public class EntreeService {

	@Autowired
	private EntreeRepository entreeRepository;

	@Autowired
	private ComposantEntreeRepository compoEntreeRepository;

	@Autowired
	private IngredientService ingredientSrv;

	private Logger logger = LoggerFactory.getLogger(EntreeService.class);

	// creation d'une entree
	public Entree creation(String nom, Double prix) {
		Entree entree = new Entree();
		entree.setNom(nom);
		entree.setPrix(prix);
		return creation(entree);
	}

	// creation d'une entree
	public Entree creation(Entree entree) {
		if (entree == null) {
			throw new ReferenceNullException();
		}
		if (entree.getNom() == null || entree.getNom().isBlank()) {
			throw new EntreeException("nom obligatoire");
		}
		return entreeRepository.save(entree);
	}

	// liste de toutes les entree
	public List<Entree> getAll() {
		return entreeRepository.findAll();
	}

	// entree par id
	public Entree getById(Long id) {
		if (id == null) {
			throw new ReferenceNullException();
		}
		Optional<Entree> opt = entreeRepository.findById(id);
		if (opt.isEmpty()) {
			throw new NotFoundException("entree " + id + " non trouvée");
		}
		return opt.get();
	}

	// entree par nom
	public List<Entree> getByNom(String nom) {
		if (nom == null) {
			throw new ReferenceNullException();
		}
		List<Entree> list = entreeRepository.findByNomContaining(nom);
		if (list.isEmpty()) {
			throw new NotFoundException("entree " + nom + " non trouvée");
		}
		return list;
	}

	// suppr entree
	public void delete(Long id) {
		if (id == null) {
			throw new ReferenceNullException();
		}
		logger.trace("entree supprimée (pas bon appetit)");
		entreeRepository.deleteById(id);
	}

	// modif entree
	public void update(Entree entree, String nvNom, Double nvPrix, Double nvTva, String nvPhoto) {
		entree.setNom(nvNom);
		entree.setPrix(nvPrix);
		entree.setTva(nvTva);
		entree.setPhoto(nvPhoto);
		entreeRepository.save(entree);
	}

	public Entree update(Entree entree) {
		return entreeRepository.save(entree);
	}

	// check existance de entree et ingredient
	public void checkCompo(Entree entree, Ingredient ingredient) {
		if (entree == null || ingredient == null) {
			throw new ReferenceNullException();
		}
		ingredientSrv.getById(ingredient.getId());
		this.getById(entree.getId());
	}

	// ajouter un ingrédient dans une pizza
	public void addIngredient(Ingredient ingredient, Entree entree) {
		checkCompo(entree, ingredient);
		ComposantEntree composantEntree = new ComposantEntree();
		composantEntree.setId(new ComposantEntreeId(entree, ingredient));
		compoEntreeRepository.save(composantEntree);
		entree.addComposantEntree(composantEntree);
	}

	// supprimer un ingrédient dans une pizza
	public void deleteIngredient(Ingredient ingredient, Entree entree) {
		checkCompo(entree, ingredient);
		compoEntreeRepository.deleteById(new ComposantEntreeId(entree, ingredient));
	}
	
	// chercher entrée avec cet ingrédient
	public Entree getByIdWithComposantsEntree(Long id) {
		if (id == null) {
			throw new ReferenceNullException();
		}
		return entreeRepository.findByIdFetchComposantsEntree(id).orElseThrow(() -> {
			throw new NotFoundException("Entrée " + id + " inexistante");
			});
	}
	
	public boolean checkIngredientInComposantsEntree(Ingredient ingredient, Set<ComposantEntree> composantsEntree){
		for (ComposantEntree composantEntree: composantsEntree) {
			if (composantEntree.getId().getIngredient().equals(ingredient)) {
				return true;
			}
		}
		return false;
	}
	
	public List<Entree> getByIngredient(Ingredient ingredient){
		Long idIngredient = ingredient.getId();
		if (idIngredient == null) {
			throw new ReferenceNullException();
		}
		List<Entree> all_entrees = getAll();
		List<Entree> entrees = new ArrayList<Entree>();
		for(int i = 0; i<all_entrees.size(); i++) {
			Entree entree = all_entrees.get(i);
			if (checkIngredientInComposantsEntree(ingredient,entree.getComposantsEntree())) {
				entrees.add(entree);
			}
		}
		return entrees;
	}
}
