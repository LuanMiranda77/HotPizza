package model;

public  abstract class PizzaDecorator extends ListaIngred{
	
	private ListaIngred pizzas;
	
	public PizzaDecorator(ListaIngred pizza) {
		pizzas=pizza;
	}
	public String getNome() {
		return pizzas.getNome()+"( "+this.nome+" )";
	}
	public float getQuant() {
		return pizzas.getQuant()+this.quant;
	}
	public float getPreco() {
		return pizzas.getPreco()+this.preco;
	}

}
