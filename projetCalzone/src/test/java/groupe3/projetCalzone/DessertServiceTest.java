package groupe3.projetCalzone;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import groupe3.projetCalzone.entities.Dessert;
import groupe3.projetCalzone.exceptions.NotFoundException;
import groupe3.projetCalzone.exceptions.DessertException;
import groupe3.projetCalzone.exceptions.ReferenceNullException;
import groupe3.projetCalzone.services.DessertService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class DessertServiceTest {
	
	@Autowired
	DessertService dessertSrv;

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
}
