package view;


import DTO.PedidosDTO;

public abstract class observado {
	
	public static void notificacao(PedidosDTO pedido) {
		observePed ped = new observePed();
		ped.update(pedido);
	}
}
