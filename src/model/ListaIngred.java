package model;

public abstract class ListaIngred {
	String nome;
	float quant;
	float preco;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getQuant() {
		return quant;
	}
	public void setQuant(float quant) {
		this.quant = quant;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
}
