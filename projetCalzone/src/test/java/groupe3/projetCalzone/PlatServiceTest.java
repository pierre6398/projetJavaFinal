package groupe3.projetCalzone;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import groupe3.projetCalzone.entities.Plat;
import groupe3.projetCalzone.exceptions.NotFoundException;
import groupe3.projetCalzone.exceptions.PlatException;
import groupe3.projetCalzone.exceptions.ReferenceNullException;
import groupe3.projetCalzone.services.PlatService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class PlatServiceTest {
	
	@Autowired
	PlatService platSrv;

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
}
