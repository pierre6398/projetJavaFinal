package groupe3.projetCalzone;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import groupe3.projetCalzone.entities.Ingredient;
import groupe3.projetCalzone.entities.Plat;
import groupe3.projetCalzone.entities.TypeIngredient;
import groupe3.projetCalzone.services.IngredientService;
import groupe3.projetCalzone.services.PlatService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class PlatServiceTest {
	
	@Autowired
	PlatService platSrv;
	
	@Autowired 
	IngredientService ingredientSrv;

	/*
	@Test
	void injectionTest() {
		assertNotNull(platSrv);
	}
	
	@Test
	void insertTest() {
		Plat p = new Plat("pâtes carbo", 10d);
		platSrv.creation(p);
		assertNotNull(p.getId());
		Plat platEnBase = platSrv.getById(p.getId());

		assertNotNull(platEnBase);
		assertEquals(p, platEnBase);
		assertEquals(p.getNom(), platEnBase.getNom());
		assertEquals(p.getPrix(), platEnBase.getPrix());
	}

	@Test
	void exceptionTest() {
		// @formatter:off
		assertAll("test exception",
						()->assertThrows(ReferenceNullException.class, () -> {
								platSrv.creation(null);
						}),
						()->assertThrows(PlatException.class, () -> {
								Plat p=new Plat();
								platSrv.creation(p);
						}),
						()->assertThrows(NotFoundException.class, ()->{
								platSrv.getById(99999999L);
						}));
		// @formatter:on

	}

	@Test
	void deleteSimpleTest() {
		// faire une insertion
		Plat p = platSrv.creation("pâtes bolo", 10d);
		// delete du code generer
		Long id = p.getId();
		platSrv.delete(p);
		// verifier en demandant le code =>NotFoundException
		assertThrows(NotFoundException.class, () -> platSrv.getById(id));
	}
	
	@Test
	void updateSimpleTest() {
		Plat p = new Plat("lasagnes", 10d);
		platSrv.creation(p);

		p.setPrix(8d);
		platSrv.update(p);
	}
	
	@Test
	void ajouterIngredientTest() {
		Ingredient tomate = new Ingredient("tomate", TypeIngredient.LEGUME);
		Plat p = new Plat("pates aux tomates",150.0);
		platSrv.creation(p);
		ingredientSrv.creation(tomate);
		platSrv.addIngredient(tomate, p);
	}
	
	@Test
	void deleteIngredientTest() {
		Ingredient tomate = new Ingredient("tomate", TypeIngredient.LEGUME);
		Plat p = new Plat("pates aux tomates",150.0);
		platSrv.creation(p);
		ingredientSrv.creation(tomate);
		platSrv.deleteIngredient(tomate, p);
	}
	*/
	
	@Test
	void getByIngredientTest() {
		Ingredient tomate = new Ingredient("tomate", TypeIngredient.LEGUME);
		Ingredient pates = new Ingredient("pâtes", TypeIngredient.LEGUME);
		Plat p = new Plat("pates aux tomates",150.0);
		platSrv.creation(p);
		ingredientSrv.creation(tomate);
		ingredientSrv.creation(pates);
		platSrv.addIngredient(tomate, p);
		platSrv.addIngredient(pates, p);
		Ingredient lardons = new Ingredient("lardons", TypeIngredient.VIANDE);
		Plat p2 = new Plat("pates carbo",150.0);
		platSrv.creation(p2);
		ingredientSrv.creation(lardons);
		platSrv.addIngredient(lardons, p2);
		platSrv.addIngredient(pates, p2);
		List<Plat> lp = platSrv.getByIngredient(pates);
	}
	
}
