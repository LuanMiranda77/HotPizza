package controller;

import DTO.IngredientesDTO;
import model.Ingredientes;
import view.ExceptionIngre;


public class ControIngre  {
	public static void  controlIngre(IngredientesDTO novo) throws ExceptionIngre {
		Ingredientes.salvarIngred(novo);

	}
	
	//metodo editar 
		public static void conteditarIngre(IngredientesDTO editado){
			
			Ingredientes.editarIngre(editado);
		}
		
		// metodo excluir
		public static void contExcluirPedido(int index){
		
			Ingredientes.excluirIngre(index);

}
		
		public static IngredientesDTO contPesIngred(int cod) throws ExceptionIngre {
			
			return Ingredientes.pesIngred(cod);
		}
		
		public static int contTamanho() {
			return Ingredientes.tamnaho();
			
		}
		
		public static IngredientesDTO listaIngre(){
			return Ingredientes.listaIngre();
		}
}
