package groupe3.projetCalzone.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupe3.projetCalzone.entities.Pizza;
import groupe3.projetCalzone.enums.BasePizza;
import groupe3.projetCalzone.exceptions.NotFoundException;
import groupe3.projetCalzone.exceptions.PizzaException;
import groupe3.projetCalzone.exceptions.ReferenceNullException;
import groupe3.projetCalzone.repositories.PizzaRepository;

@Service
public class PizzaService {
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	private Logger logger = LoggerFactory.getLogger(PizzaService.class);
	
	// creation d'une pizza
		public Pizza creation(String nom) {
			Pizza pizza = new Pizza();
			pizza.setNom(nom);
			return creation(pizza);
		}
		
	// creation d'une pizza
		public Pizza creation(Pizza pizza) {
			if (pizza == null) {
				throw new ReferenceNullException();
			}
			if (pizza.getNom() == null || pizza.getNom().isBlank()) {
				throw new PizzaException("nom obligatoire");
			}
			return pizzaRepository.save(pizza);
		}
		
	// liste de toutes les pizza
		public List<Pizza> getAll() {
			return pizzaRepository.findAll();
		}

	//pizza par id
		public Pizza getById(Long id) {
			if (id == null) {
				throw new ReferenceNullException();
			}
			Optional<Pizza> opt = pizzaRepository.findById(id);
			if (opt.isEmpty()) {
				throw new NotFoundException("pizza " + id + " non trouvée");
			}
			return opt.get();
		}
	//pizza par nom
				public List<Pizza> getByNom(String nom) {
					if (nom == null) {
						throw new ReferenceNullException();
					}
					List<Pizza> list = pizzaRepository.findByNomContaining(nom);
					if (list.isEmpty()) {
						throw new NotFoundException("pizza " + nom + " non trouvée");
					}
					return list;
				}
	//pizza par base
				public List<Pizza> getByBase(BasePizza base) {
					if (base == null) {
						throw new ReferenceNullException();
					}
					List<Pizza> list = pizzaRepository.findByBase(base);
					if (list.isEmpty()) {
						throw new NotFoundException("base " + base + " non trouvée");
					}
					return list;
				}	
	//suppr pizza
				public void delete(Long id) {
					if (id == null) {
						throw new ReferenceNullException();
					}
					logger.trace("pizza supprimée (pas bon appetit)");
					pizzaRepository.deleteById(id);
				}
				
	//modif pizza
				public void update(Pizza pizza, String nvNom, Double nvPrix, Integer nvTva, BasePizza nvBase, String nvPhoto) {
					pizza.setNom(nvNom);
					pizza.setPrix(nvPrix);
					pizza.setTva(nvTva);
					pizza.setBase(nvBase);
					pizza.setPhoto(nvPhoto);
					pizzaRepository.save(pizza);
				}
}
