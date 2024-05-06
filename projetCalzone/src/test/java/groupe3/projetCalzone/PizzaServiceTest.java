package groupe3.projetCalzone;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import groupe3.projetCalzone.entities.Ingredient;
import groupe3.projetCalzone.entities.Pizza;
import groupe3.projetCalzone.entities.TypeIngredient;
import groupe3.projetCalzone.services.IngredientService;
import groupe3.projetCalzone.services.PizzaService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class PizzaServiceTest {
	
	@Autowired
	PizzaService pizzaSrv;
	
	@Autowired 
	IngredientService ingredientSrv;

	/*
	@Test
	void injectionTest() {
		assertNotNull(pizzaSrv);
	}
	
	@Test
	void insertTest() {
		Pizza p = new Pizza("calzone", 7d);
		pizzaSrv.creation(p);
		assertNotNull(p.getId());
		Pizza pizzaEnBase = pizzaSrv.getById(p.getId());

		assertNotNull(pizzaEnBase);
		assertEquals(p, pizzaEnBase);
		assertEquals(p.getNom(), pizzaEnBase.getNom());
		assertEquals(p.getPrix(), pizzaEnBase.getPrix());
	}

	@Test
	void exceptionTest() {
		// @formatter:off
		assertAll("test exception",
						()->assertThrows(ReferenceNullException.class, () -> {
								pizzaSrv.creation(null);
						}),
						()->assertThrows(PizzaException.class, () -> {
								Pizza p=new Pizza();
								pizzaSrv.creation(p);
						}),
						()->assertThrows(NotFoundException.class, ()->{
								pizzaSrv.getById(99999999L);
						}));
		// @formatter:on

	}

	@Test
	void deleteSimpleTest() {
		// faire une insertion
		Pizza p = pizzaSrv.creation("margherita", 7d);
		// delete du code generer
		Long id = p.getId();
		pizzaSrv.delete(id);
		// verifier en demandant le code =>NotFoundException
		assertThrows(NotFoundException.class, () -> pizzaSrv.getById(id));
	}
	
	@Test
	void updateSimpleTest() {
		Pizza p = new Pizza("4 fromages", 7d);
		pizzaSrv.creation(p);

		p.setPrix(8d);
		pizzaSrv.update(p);
	}
	
	@Test
	void ajouterIngredientTest() {
		Ingredient tomate = new Ingredient("tomate", TypeIngredient.LEGUME);
		Pizza p = new Pizza("calzone",150.0);
		pizzaSrv.creation(p);
		ingredientSrv.creation(tomate);
		pizzaSrv.addIngredient(tomate, p);
	}
	
	@Test
	void deleteIngredientTest() {
		Ingredient tomate = new Ingredient("tomate", TypeIngredient.LEGUME);
		Pizza p = new Pizza("calzone",150.0);
		pizzaSrv.creation(p);
		ingredientSrv.creation(tomate);
		pizzaSrv.deleteIngredient(tomate, p);
	}
	*/
	
	@Test
	void getByIngredientTest() {
		Ingredient tomate = new Ingredient("tomate", TypeIngredient.LEGUME);
		Ingredient jambon = new Ingredient("jambon", TypeIngredient.VIANDE);
		Pizza p = new Pizza("margherita",150.0);
		pizzaSrv.creation(p);
		ingredientSrv.creation(tomate);
		ingredientSrv.creation(jambon);
		pizzaSrv.addIngredient(tomate, p);
		pizzaSrv.addIngredient(jambon, p);
		Ingredient fromage = new Ingredient("fromage", TypeIngredient.FROMAGE);
		Pizza p2 = new Pizza("4 fromages",150.0);
		pizzaSrv.creation(p2);
		ingredientSrv.creation(fromage);
		pizzaSrv.addIngredient(fromage, p2);
		pizzaSrv.addIngredient(jambon, p2);
		List<Pizza> lp = pizzaSrv.getByIngredient(jambon);
	}
	
}
