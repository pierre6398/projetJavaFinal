package groupe3.projetCalzone;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import groupe3.projetCalzone.entities.Dessert;
import groupe3.projetCalzone.entities.Entree;
import groupe3.projetCalzone.entities.Ingredient;
import groupe3.projetCalzone.entities.TypeIngredient;
import groupe3.projetCalzone.exceptions.NotFoundException;
import groupe3.projetCalzone.exceptions.DessertException;
import groupe3.projetCalzone.exceptions.ReferenceNullException;
import groupe3.projetCalzone.services.DessertService;
import groupe3.projetCalzone.services.IngredientService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class DessertServiceTest {
	
	@Autowired
	DessertService dessertSrv;

	
	@Autowired 
	IngredientService ingredientSrv;
	
	/*
	@Test
	void injectionTest() {
		assertNotNull(dessertSrv);
	}
	
	@Test
	void insertTest() {
		Dessert d = new Dessert("tiramisu", 5d);
		dessertSrv.creation(d);
		assertNotNull(d.getId());
		Dessert dessertEnBase = dessertSrv.getById(d.getId());

		assertNotNull(dessertEnBase);
		assertEquals(d, dessertEnBase);
		assertEquals(d.getNom(), dessertEnBase.getNom());
		assertEquals(d.getPrix(), dessertEnBase.getPrix());
	}

	@Test
	void exceptionTest() {
		// @formatter:off
		assertAll("test exception",
						()->assertThrows(ReferenceNullException.class, () -> {
								dessertSrv.creation(null);
						}),
						()->assertThrows(DessertException.class, () -> {
								Dessert d=new Dessert();
								dessertSrv.creation(d);
						}),
						()->assertThrows(NotFoundException.class, ()->{
								dessertSrv.getById(99999999L);
						}));
		// @formatter:on

	}

	@Test
	void deleteSimpleTest() {
		// faire une insertion
		Dessert d = dessertSrv.creation("panna cotta", 5d);
		// delete du code generer
		Long id = d.getId();
		dessertSrv.delete(d);
		// verifier en demandant le code =>NotFoundException
		assertThrows(NotFoundException.class, () -> dessertSrv.getById(id));
	}
	
	@Test
	void updateSimpleTest() {
		Dessert d = new Dessert("dame blanche", 6d);
		dessertSrv.creation(d);

		d.setPrix(7d);
		dessertSrv.update(d);
	}
	
	@Test
	void ajouterIngredientTest() {
		Ingredient mascarpone = new Ingredient("mascarpone", TypeIngredient.FROMAGE);
		Dessert d = new Dessert("tiramisu",1.0);
		dessertSrv.creation(d);
		ingredientSrv.creation(mascarpone);
		dessertSrv.addIngredient(mascarpone, d);
	}
	
	@Test
	void deleteIngredientTest() {
		Ingredient mascarpone = new Ingredient("mascarpone", TypeIngredient.FROMAGE);
		Dessert d = new Dessert("tiramisu",1.0);
		dessertSrv.creation(d);
		ingredientSrv.creation(mascarpone);
		dessertSrv.deleteIngredient(mascarpone, d);
	}
	*/
	
	@Test
	void getByIngredientTest() {
		Ingredient glace_vanille = new Ingredient("glace vanille", TypeIngredient.PRODUIT_LAITIER);
		Ingredient glace_chocolat = new Ingredient("glace chocolat", TypeIngredient.PRODUIT_LAITIER);
		Dessert d = new Dessert("glace 2 boules vanille chocolat",150.0);
		dessertSrv.creation(d);
		ingredientSrv.creation(glace_vanille);
		ingredientSrv.creation(glace_chocolat);
		dessertSrv.addIngredient(glace_vanille, d);
		dessertSrv.addIngredient(glace_chocolat, d);
		Ingredient chantilly = new Ingredient("chantilly", TypeIngredient.AUTRE);
		Dessert d2 = new Dessert("glace vanille supplément chantilly",150.0);
		dessertSrv.creation(d2);
		ingredientSrv.creation(chantilly);
		dessertSrv.addIngredient(chantilly, d2);
		dessertSrv.addIngredient(glace_vanille, d2);
		List<Dessert> lp = dessertSrv.getByIngredient(chantilly);
	}
}
