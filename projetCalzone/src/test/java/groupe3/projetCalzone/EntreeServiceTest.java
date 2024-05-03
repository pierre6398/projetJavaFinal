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
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class EntreeServiceTest {
	
	@Autowired
	EntreeService entreeSrv;

	@Test
	void injectionTest() {
		assertNotNull(entreeSrv);
	}
	
	@Test
	void insertTest() {
		Entree e = new Entree("salade césar", 7d);
		entreeSrv.creationEntree(e);
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
								entreeSrv.creationEntree(null);
						}),
						()->assertThrows(EntreeException.class, () -> {
								Entree e=new Entree();
								entreeSrv.creationEntree(e);
						}),
						()->assertThrows(NotFoundException.class, ()->{
								entreeSrv.getById(99999999L);
						}));
		// @formatter:on

	}

	@Test
	void deleteSimpleTest() {
		// faire une insertion
		Entree e = entreeSrv.creationEntree("arancini", 5d);
		// delete du code generer
		Long id = e.getId();
		entreeSrv.deleteEntree(id);
		// verifier en demandant le code =>NotFoundException
		assertThrows(NotFoundException.class, () -> entreeSrv.getById(id));
	}
	
	@Test
	void updateSimpleTest() {
		Entree e = new Entree("tomate mozza", 6d);
		entreeSrv.creationEntree(e);

		e.setPrix(7d);
		entreeSrv.modifierEntreeInfo(e);
	}
}
