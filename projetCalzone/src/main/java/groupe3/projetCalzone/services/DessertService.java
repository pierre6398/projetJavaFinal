package groupe3.projetCalzone.services;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import groupe3.projetCalzone.entities.Dessert;
import groupe3.projetCalzone.exceptions.DessertException;
import groupe3.projetCalzone.exceptions.ReferenceNullException;
import groupe3.projetCalzone.repositories.DessertRepository;

public class DessertService {
	
	@Autowired
	private DessertRepository dessertRepository;
	
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
	public Dessert creationDessert(String nom, Double prix) {
		logger.trace("creation dessert avec String, Double");
		Dessert dessert = new Dessert();
		dessert.setNom(nom);
		dessert.setPrix(prix);
		return creationDessert(dessert);
	}
	
	
	// création d'un dessert grâce à un dessert
	public Dessert creationDessert(Dessert dessert) {
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
}