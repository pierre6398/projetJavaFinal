package groupe3.projetCalzone.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupe3.projetCalzone.entities.Entree;
import groupe3.projetCalzone.exceptions.EntreeException;
import groupe3.projetCalzone.exceptions.NotFoundException;
import groupe3.projetCalzone.exceptions.ReferenceNullException;
import groupe3.projetCalzone.repositories.EntreeRepository;

@Service
public class EntreeService {

	@Autowired
	private EntreeRepository entreeRepository;
	
	private Logger logger = LoggerFactory.getLogger(EntreeService.class);
	
	// creation d'une entree
			public Entree creationEntree(String nom, Double prix) {
				Entree entree = new Entree();
				entree.setNom(nom);
				entree.setPrix(prix);
				return creationEntree(entree);
			}
			
		// creation d'une entree
			public Entree creationEntree(Entree entree) {
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

		//entree par id
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
		//entree par nom
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

		//suppr entree
					public void deleteEntree(Long id) {
						if (id == null) {
							throw new ReferenceNullException();
						}
						logger.trace("entree supprimée (pas bon appetit)");
						entreeRepository.deleteById(id);
					}
					
		//modif entree
					public void modifierEntreeInfo(Entree entree, String nvNom, Double nvPrix, Integer nvTva, String nvPhoto) {
						entree.setNom(nvNom);
						entree.setPrix(nvPrix);
						entree.setTva(nvTva);
						entree.setPhoto(nvPhoto);
						entreeRepository.save(entree);
					}
					
					public void modifierEntreeInfo(Entree entree) {
						entreeRepository.save(entree);
					}
}
