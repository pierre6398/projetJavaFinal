package groupe3.projetCalzone;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import groupe3.projetCalzone.entities.Boisson;
import groupe3.projetCalzone.entities.Carte;
import groupe3.projetCalzone.entities.Dessert;
import groupe3.projetCalzone.entities.Entree;
import groupe3.projetCalzone.entities.Pizza;
import groupe3.projetCalzone.entities.Plat;
import groupe3.projetCalzone.services.CarteService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class CarteServiceTest {

	@Autowired
	private CarteService carteSrv;
	
	@Test
	void injectionTest() {
		assertNotNull(carteSrv);
	}
	
	@Test
	void getCarte() {
		Entree e = new Entree("tomate mozza",10.0);
		Plat p = new Plat("pates boloss", 15.0);
		Pizza piz = new Pizza("cacalzone",20.0);
		Boisson b = new Boisson("rome ambré", 25.0,true);
		Dessert d = new Dessert("filou tubz", 100000.0);
		
		Carte carte = new Carte("la carte");
		carteSrv.creation(carte);

		carteSrv.addBoissonToCarte(carte, b);
		carteSrv.addDessertToCarte(carte, d);
		carteSrv.addEntreeToCarte(carte, e);
		carteSrv.addPizzaToCarte(carte, piz);
		carteSrv.addPlatToCarte(carte, p);

		carteSrv.deleteBoissonFromCarte(carte, b);
		carteSrv.deleteDessertFromCarte(carte, d);
		carteSrv.addEntreeToCarte(carte, e);
		carteSrv.addPizzaToCarte(carte, piz);
		carteSrv.addPlatToCarte(carte, p);
	}
}
