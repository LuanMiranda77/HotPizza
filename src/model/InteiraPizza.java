package model;

import DTO.PizzasDTO;

public class InteiraPizza implements CalculePrecoStartegy {

	@Override
	public float calcular(PizzasDTO pizza) {
		float preco =pizza.getValorcomp();
		return preco;
	}

}
