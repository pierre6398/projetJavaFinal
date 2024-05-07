package groupe3.projetCalzone.entities;

public enum TypeIngredient {
	LEGUME("Légume"),VIANDE("Viande"),FROMAGE("Fromage"),FECULENT("Féculent"),MATIERE_GRASSE("Matière grasse"),
	PRODUIT_LAITIER("Produit laitier"),CONDIMENT("Condiment"), POISSON("Poissson"),FRUITS("Fruits"),AUTRE("Autre");
	
	
    private final String toString;

    private TypeIngredient(String toString) {
         this.toString = toString;
    }
}
