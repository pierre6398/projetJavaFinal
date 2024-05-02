package groupe3.projetCalzone;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import groupe3.projetCalzone.entities.Boisson;
import groupe3.projetCalzone.exceptions.NotFoundException;
import groupe3.projetCalzone.exceptions.BoissonException;
import groupe3.projetCalzone.exceptions.ReferenceNullException;
import groupe3.projetCalzone.services.BoissonService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class BoissonServiceTest {
	
	@Autowired
	BoissonService boissonSrv;

	@Test
	void injectionTest() {
		assertNotNull(boissonSrv);
	}
	
	@Test
	void insertTest() {
		Boisson b = new Boisson("coca", 2d);
		boissonSrv.creationBoisson(b);
		assertNotNull(b.getId());
		Boisson boissonEnBase = boissonSrv.getById(b.getId());

		assertNotNull(boissonEnBase);
		assertEquals(b, boissonEnBase);
		assertEquals(b.getNom(), boissonEnBase.getNom());
		assertEquals(b.getPrix(), boissonEnBase.getPrix());
	}

	@Test
	void exceptionTest() {
		// @formatter:off
		assertAll("test exception",
						()->assertThrows(ReferenceNullException.class, () -> {
								boissonSrv.creationBoisson(null);
						}),
						()->assertThrows(BoissonException.class, () -> {
								Boisson b=new Boisson();
								boissonSrv.creationBoisson(b);
						}),
						()->assertThrows(NotFoundException.class, ()->{
								boissonSrv.getById(99999999L);
						}));
		// @formatter:on

	}

	@Test
	void deleteSimpleTest() {
		// faire une insertion
		Boisson b = boissonSrv.creationBoisson("cocktail maison", 4d);
		// delete du code generer
		Long id = b.getId();
		boissonSrv.deleteBoisson(id);
		// verifier en demandant le code =>NotFoundException
		assertThrows(NotFoundException.class, () -> boissonSrv.getById(id));
	}
	
	@Test
	void updateSimpleTest() {
		Boisson b = new Boisson("ice tea", 2d);
		boissonSrv.creationBoisson(b);

		b.setPrix(3d);
		boissonSrv.modifierBoissonInfo(b);
	}
}
