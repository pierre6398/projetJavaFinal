package groupe3.projetCalzone.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupe3.projetCalzone.entities.Boisson;
import groupe3.projetCalzone.entities.Carte;
import groupe3.projetCalzone.entities.Dessert;
import groupe3.projetCalzone.entities.Entree;
import groupe3.projetCalzone.entities.Pizza;
import groupe3.projetCalzone.entities.Plat;
import groupe3.projetCalzone.exceptions.CarteException;
import groupe3.projetCalzone.exceptions.NotFoundException;
import groupe3.projetCalzone.exceptions.ReferenceNullException;
import groupe3.projetCalzone.repositories.BoissonRepository;
import groupe3.projetCalzone.repositories.CarteRepository;
import groupe3.projetCalzone.repositories.DessertRepository;
import groupe3.projetCalzone.repositories.EntreeRepository;
import groupe3.projetCalzone.repositories.PizzaRepository;
import groupe3.projetCalzone.repositories.PlatRepository;

@Service
public class CarteService {

	@Autowired
	private CarteRepository carteRepository;
	@Autowired
	private PizzaRepository pizzaRepository;
	@Autowired
	private EntreeRepository entreeRepository;
	@Autowired
	private PlatRepository platRepository;
	@Autowired
	private BoissonRepository boissonRepository;
	@Autowired
	private DessertRepository dessertRepository;



	private Logger logger = LoggerFactory.getLogger(CarteService.class);

	// creation d'une carte
	public Carte creation(String nom) {
		Carte carte = new Carte(nom);
		return creation(carte);
	}

	// creation d'une carte
	public Carte creation(Carte carte) {
		if (carte == null) {
			throw new ReferenceNullException();
		}
		if (carte.getNom() == null || carte.getNom().isBlank()) {
			throw new CarteException("nom obligatoire");
		}
		return carteRepository.save(carte);
	}

	// liste de toutes les cartes
	public List<Carte> getAll() {
		return carteRepository.findAll();
	}

	// carte par id
	public Carte getById(Long id) {
		if (id == null) {
			throw new ReferenceNullException();
		}
		Optional<Carte> opt = carteRepository.findById(id);
		if (opt.isEmpty()) {
			throw new NotFoundException("carte " + id + " non trouvée");
		}
		return opt.get();
	}

	// carte par nom
	public List<Carte> getByNom(String nom) {
		if (nom == null) {
			throw new ReferenceNullException();
		}
		List<Carte> list = carteRepository.findByNomContaining(nom);
		if (list.isEmpty()) {
			throw new NotFoundException("carte " + nom + " non trouvée");
		}
		return list;
	}

	// suppr carte
	public void delete(Long id) {
		if (id == null) {
			throw new ReferenceNullException();
		}
		logger.trace("carte supprimée");
		carteRepository.deleteById(id);
	}

	// modif carte
	public void update(Carte carte, String nvNom) {
		carte.setNom(nvNom);
		carteRepository.save(carte);
	}

	public Carte update(Carte carte) {
		return carteRepository.save(carte);
	}

	// afficher la carte des entrees
	public List<Entree> getAllEntrees() {
		return entreeRepository.findAll();
	}

	// afficher la carte des plats
	public List<Plat> getAllPlats() {
		return platRepository.findAll();
	}

	// afficher la carte des pizzas
	public List<Pizza> getAllPizzas() {
		return pizzaRepository.findAll();
	}

	// afficher la carte des boissons
	public List<Boisson> getAllBoissons() {
		return boissonRepository.findAll();
	}

	// afficher la carte des dessert
	public List<Dessert> getAllDessert() {
		return dessertRepository.findAll();
	}

	// check existence carte
	public void checkCarte(Carte carte) {
		if (carte == null) {
			throw new ReferenceNullException();
		}
		this.getById(carte.getId());
	}

	// ajouter entree dans carte
	public void addEntreeToCarte(Carte carte, Entree entree) {
		checkCarte(carte);
		entree.setCarte(carte);
		carte.addEntree(entree);
		carteRepository.save(carte);
	}

	// ajouter boisson dans carte
	public void addBoissonToCarte(Carte carte, Boisson boisson) {
		checkCarte(carte);
		boisson.setCarte(carte);
		carte.addBoisson(boisson);
		carteRepository.save(carte);
	}

	// ajouter plat dans carte
	public void addPlatToCarte(Carte carte, Plat plat) {
		checkCarte(carte);
		plat.setCarte(carte);
		carte.addPlat(plat);
		carteRepository.save(carte);
	}

	// ajouter pizza dans carte
	public void addPizzaToCarte(Carte carte, Pizza pizza) {
		checkCarte(carte);
		pizza.setCarte(carte);
		carte.addPizza(pizza);
		carteRepository.save(carte);
	}

	// ajouter dessert dans carte
	public void addDessertToCarte(Carte carte, Dessert dessert) {
		checkCarte(carte);
		dessert.setCarte(carte);
		carte.addDessert(dessert);
		carteRepository.save(carte);
	}

	// supp entree dans carte
	public void deleteEntreeFromCarte(Carte carte, Entree entree) {
		checkCarte(carte);
		entree.setCarte(null);
		carte.deleteEntree(entree);
		carteRepository.save(carte);
	}

	// supp plat dans carte
	public void deletePlatFromCarte(Carte carte, Plat plat) {
		checkCarte(carte);
		plat.setCarte(null);
		carte.deletePlat(plat);
		carteRepository.save(carte);
	}

	// supp pizza dans carte
	public void deletePizzaFromCarte(Carte carte, Pizza pizza) {
		checkCarte(carte);
		pizza.setCarte(null);
		carte.deletePizza(pizza);
		carteRepository.save(carte);
	}

	// supp boisson dans carte
	public void deleteBoissonFromCarte(Carte carte, Boisson boisson) {
		checkCarte(carte);
		boisson.setCarte(null);
		carte.deleteBoisson(boisson);
		carteRepository.save(carte);
	}

	// supp dessert dans carte
	public void deleteDessertFromCarte(Carte carte, Dessert dessert) {
		checkCarte(carte);
		dessert.setCarte(null);
		carte.deleteDessert(dessert);
		carteRepository.save(carte);
	}

}
