package groupe3.projetCalzone.dto.responses;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import groupe3.projetCalzone.entities.Carte;

public class CarteResponse {

	@JsonView(JsonViews.Basic.class)
	private Long id;
	@JsonView(JsonViews.Basic.class)
	private String nom;
	@JsonView(JsonViews.Basic.class)
	private List<EntreeResponse> entrees;
	@JsonView(JsonViews.Basic.class)
	private List<PlatResponse> plats;
	@JsonView(JsonViews.Basic.class)
	private List<PizzaResponse> pizzas;
	@JsonView(JsonViews.Basic.class)
	private List<BoissonResponse> boissons;
	@JsonView(JsonViews.Basic.class)
	private List<DessertResponse> desserts;

	public CarteResponse() {

	}

	public CarteResponse(Carte carte) {
		BeanUtils.copyProperties(carte, this);
	}

	public CarteResponse(Carte carte, boolean b) {
		BeanUtils.copyProperties(carte, this);
		if (b) {
			entrees = carte.getEntrees().stream().map(entree -> new EntreeResponse(entree,true))
					.collect(Collectors.toList());
			plats = carte.getPlats().stream().map(plat -> new PlatResponse(plat,true))
					.collect(Collectors.toList());
			pizzas = carte.getPizzas().stream().map(pizza -> new PizzaResponse(pizza,true))
					.collect(Collectors.toList());
			boissons = carte.getBoissons().stream().map(boisson -> new BoissonResponse(boisson))
					.collect(Collectors.toList());
			desserts = carte.getDesserts().stream().map(dessert -> new DessertResponse(dessert,true))
					.collect(Collectors.toList());
		}
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

	public List<EntreeResponse> getEntrees() {
		return entrees;
	}

	public void setEntrees(List<EntreeResponse> entrees) {
		this.entrees = entrees;
	}

	public List<PlatResponse> getPlats() {
		return plats;
	}

	public void setPlats(List<PlatResponse> plats) {
		this.plats = plats;
	}

	public List<PizzaResponse> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<PizzaResponse> pizzas) {
		this.pizzas = pizzas;
	}

	public List<BoissonResponse> getBoissons() {
		return boissons;
	}

	public void setBoissons(List<BoissonResponse> boissons) {
		this.boissons = boissons;
	}

	public List<DessertResponse> getDesserts() {
		return desserts;
	}

	public void setDesserts(List<DessertResponse> desserts) {
		this.desserts = desserts;
	}

}
