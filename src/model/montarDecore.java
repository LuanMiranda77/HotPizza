package model;


public class montarDecore extends PizzaDecorator {

	public montarDecore(ListaIngred pizza) {
		super(pizza);
		this.setNome(pizza.getNome());
		this.setQuant(pizza.getQuant());
		this.setPreco(pizza.getPreco());
	}
}
