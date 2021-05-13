package model;

import DTO.PizzasDTO;

public class MeiaPizza implements CalculePrecoStartegy {

	@Override
	public float calcular(PizzasDTO pizza) {
		float preco = pizza.getValorcomp()/2;
		return preco;
	}
	

}
