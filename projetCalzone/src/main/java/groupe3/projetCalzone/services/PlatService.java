package groupe3.projetCalzone.services;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import groupe3.projetCalzone.entities.Plat;
import groupe3.projetCalzone.exceptions.PlatException;
import groupe3.projetCalzone.exceptions.ReferenceNullException;
import groupe3.projetCalzone.repositories.PlatRepository;

public class PlatService {
	
	@Autowired
	private PlatRepository platRepository;
	
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
	public Plat creationPlat(String nom, Double prix) {
		logger.trace("creation plat avec String, Double");
		Plat plat = new Plat();
		plat.setNom(nom);
		plat.setPrix(prix);
		return creationPlat(plat);
	}
	
	
	// création d'un plat grâce à un plat
	public Plat creationPlat(Plat plat) {
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
}
