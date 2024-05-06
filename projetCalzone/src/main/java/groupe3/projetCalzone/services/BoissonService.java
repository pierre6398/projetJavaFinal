package groupe3.projetCalzone.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupe3.projetCalzone.entities.Boisson;
import groupe3.projetCalzone.exceptions.BoissonException;
import groupe3.projetCalzone.exceptions.DessertException;
import groupe3.projetCalzone.exceptions.NotFoundException;
import groupe3.projetCalzone.exceptions.ReferenceNullException;
import groupe3.projetCalzone.repositories.BoissonRepository;

@Service
public class BoissonService {
	@Autowired
	private BoissonRepository boissonRepository;

	private Logger logger = LoggerFactory.getLogger(BoissonService.class);

	// creation d'une boisson
	public Boisson creation(String nom, Double prix) {
		Boisson boisson = new Boisson();
		boisson.setNom(nom);
		boisson.setPrix(prix);
		return creation(boisson);
	}

	// creation d'une boisson
	public Boisson creation(Boisson boisson) {
		if (boisson == null) {
			throw new ReferenceNullException();
		}
		if (boisson.getNom() == null || boisson.getNom().isBlank()) {
			throw new BoissonException("nom obligatoire");
		}
		return boissonRepository.save(boisson);
	}

	// test de l'existence d'un boisson
	private boolean boissonNotNull(Boisson boisson) {
		if (boisson == null) {
			logger.error("boisson null");
			throw new ReferenceNullException("reference null");
		}
		return true;
	}

	// liste de toutes les boissons
	public List<Boisson> getAll() {
		return boissonRepository.findAll();
	}

	// boisson par id
	public Boisson getById(Long id) {
		if (id == null) {
			throw new ReferenceNullException();
		}
		Optional<Boisson> opt = boissonRepository.findById(id);
		if (opt.isEmpty()) {
			throw new NotFoundException("boisson " + id + " non trouvée");
		}
		return opt.get();
	}

	// boisson par nom
	public List<Boisson> getByNom(String nom) {
		if (nom == null) {
			throw new ReferenceNullException();
		}
		List<Boisson> list = boissonRepository.findByNomContaining(nom);
		if (list.isEmpty()) {
			throw new NotFoundException("boisson " + nom + " non trouvée");
		}
		return list;
	}

	// boisson alcool ou non
	public List<Boisson> getByAlcool(Boolean alcool) {
		if (alcool == null) {
			throw new ReferenceNullException();
		}
		List<Boisson> list = boissonRepository.findByAlcool(alcool);
		if (list.isEmpty()) {
			throw new NotFoundException("boisson non trouvée");
		}
		return list;
	}

	// suppr boisson
	public void delete(Long id) {
		if (id == null) {
			throw new ReferenceNullException();
		}
		logger.trace("boisson supprimée (???)");
		boissonRepository.deleteById(id);
	}

	// modif boisson
	public void update(Boisson boisson, String nvNom, Double nvPrix, Integer nvTva) {
		boisson.setNom(nvNom);
		boisson.setPrix(nvPrix);
		boisson.setTva(nvTva);
		boissonRepository.save(boisson);
	}

	public Boisson update(Boisson boisson) {
		logger.trace("modification de boisson");
		if (boissonNotNull(boisson) && boisson.getNom() == null || boisson.getNom().isBlank()) {
			logger.debug("nom vide");
			// probleme=>RunTimeException
			throw new BoissonException("nom boisson obligatoire");
		}
		if (boisson.getPrix() == null) {
			logger.debug("prix vide");
			throw new DessertException("prix boisson obligatoire");
		}
		logger.debug(boisson.getNom());

		return boissonRepository.save(boisson);
	}
}
