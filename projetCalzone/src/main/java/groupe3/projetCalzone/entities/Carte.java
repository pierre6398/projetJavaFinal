package groupe3.projetCalzone.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "carte")
public class Carte {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "carte_id")
	private Long id;
	@Column(name = "carte_nom")
	private String nom;
	@OneToMany(mappedBy = "carte")
	private List<Entree> entrees;
	@OneToMany(mappedBy = "carte")
	private List<Plat> plats;
	@OneToMany(mappedBy = "carte")
	private List<Pizza> pizzas;
	@OneToMany(mappedBy = "carte")
	private List<Boisson> boissons;
	@OneToMany(mappedBy = "carte")
	private List<Dessert> desserts;
	
	
	public Carte() {
	}

	public Carte(String nom) {
		this.nom = nom;
		entrees = new ArrayList<Entree>();
		plats = new ArrayList<Plat>();
		pizzas = new ArrayList<Pizza>();
		boissons = new ArrayList<Boisson>();
		desserts = new ArrayList<Dessert>();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}	

	public List<Entree> getEntrees() {
		return entrees;
	}

	public void setEntrees(List<Entree> entrees) {
		this.entrees = entrees;
	}

	public List<Plat> getPlats() {
		return plats;
	}

	public void setPlats(List<Plat> plats) {
		this.plats = plats;
	}

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public List<Boisson> getBoissons() {
		return boissons;
	}

	public void setBoissons(List<Boisson> boissons) {
		this.boissons = boissons;
	}

	public List<Dessert> getDesserts() {
		return desserts;
	}

	public void setDesserts(List<Dessert> desserts) {
		this.desserts = desserts;
	}
	
	//ajout
	public void addEntree(Entree entree) {
		entrees.add(entree);
	}

	public void addBoisson(Boisson boisson) {
		boissons.add(boisson);
	}
	
	public void addPlat(Plat plat) {
		plats.add(plat);
	}
	
	public void addPizza(Pizza pizza) {
		pizzas.add(pizza);
	}
	
	public void addDessert(Dessert dessert) {
		desserts.add(dessert);
	}
	
	//suppr 
	public void deleteEntree(Entree entree) {
		entrees.remove(entree);
	}
	public void deletePlat(Plat plat) {
		plats.remove(plat);
	}
	public void deletePizza(Pizza pizza) {
		pizzas.remove(pizza);
	}
	public void deleteDessert(Dessert dessert) {
		desserts.remove(dessert);
	}
	public void deleteBoisson(Boisson boisson) {
		boissons.remove(boisson);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carte other = (Carte) obj;
		return Objects.equals(id, other.id);
	}
}
