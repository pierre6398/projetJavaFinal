package groupe3.projetCalzone;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import groupe3.projetCalzone.entities.Pizza;
import groupe3.projetCalzone.exceptions.NotFoundException;
import groupe3.projetCalzone.exceptions.PizzaException;
import groupe3.projetCalzone.exceptions.ReferenceNullException;
import groupe3.projetCalzone.services.PizzaService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class PizzaServiceTest {
	
	@Autowired
	PizzaService pizzaSrv;

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
}
