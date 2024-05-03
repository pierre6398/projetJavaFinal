package groupe3.projetCalzone;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import groupe3.projetCalzone.entities.Entree;
import groupe3.projetCalzone.exceptions.NotFoundException;
import groupe3.projetCalzone.exceptions.EntreeException;
import groupe3.projetCalzone.exceptions.ReferenceNullException;
import groupe3.projetCalzone.services.EntreeService;
import groupe3.projetCalzone.services.IngredientService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class EntreeServiceTest {
	
	@Autowired
	EntreeService entreeSrv;
	@Autowired 
	IngredientService ingredientSrv;

	@Test
	void injectionTest() {
		assertNotNull(entreeSrv);
	}
	
	@Test
	void insertTest() {
		Entree e = new Entree("salade césar", 7d);
		entreeSrv.creation(e);
		assertNotNull(e.getId());
		Entree entreeEnBase = entreeSrv.getById(e.getId());

		assertNotNull(entreeEnBase);
		assertEquals(e, entreeEnBase);
		assertEquals(e.getNom(), entreeEnBase.getNom());
		assertEquals(e.getPrix(), entreeEnBase.getPrix());
	}

	@Test
	void exceptionTest() {
		// @formatter:off
		assertAll("test exception",
						()->assertThrows(ReferenceNullException.class, () -> {
								entreeSrv.creation(null);
						}),
						()->assertThrows(EntreeException.class, () -> {
								Entree e=new Entree();
								entreeSrv.creation(e);
						}),
						()->assertThrows(NotFoundException.class, ()->{
								entreeSrv.getById(99999999L);
						}));
		// @formatter:on

	}

	@Test
	void deleteSimpleTest() {
		// faire une insertion
		Entree e = entreeSrv.creation("arancini", 5d);
		// delete du code generer
		Long id = e.getId();
		entreeSrv.delete(id);
		// verifier en demandant le code =>NotFoundException
		assertThrows(NotFoundException.class, () -> entreeSrv.getById(id));
	}
	
	@Test
	void updateSimpleTest() {
		Entree e = new Entree("tomate mozza", 6d);
		entreeSrv.creation(e);

		e.setPrix(7d);
		entreeSrv.update(e);
	}
	
//	@Test
//	void ajouterIngredientTest() {
//		Ingredient tomate = new Ingredient("tomate", TypeIngredient.LEGUME);
//		Entree e = new Entree("tomate mozza", 32.0);
//		entreeSrv.creation(e);
//		ingredientSrv.creation(tomate);
//		entreeSrv.ajouterIngredient(tomate, e);
//	}
//	
//	@Test
//	void deleteIngredientTest() {
//		Ingredient tomate = new Ingredient("tomate", TypeIngredient.LEGUME);
//		Entree e = new Entree("tomate mozza", 32.0);
//		entreeSrv.creation(e);
//		ingredientSrv.creation(tomate);
//		entreeSrv.deleteIngredient(tomate, e);
//	}
}
