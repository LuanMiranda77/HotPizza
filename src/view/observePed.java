package view;

import DTO.PedidosDTO;
import controller.ControlPedidos;

public class observePed implements observador {
    
	private PedidosDTO listaEspera = new PedidosDTO();
	
    @Override
	public void update(PedidosDTO ped) {
		
		if(ped.getStatus().equals("espera")) {
			listaEspera.adicionar(ped);
		}
	}

	@Override
	public void  comparar() {
		for(int i= 0; i<listaEspera.getListaPedidos().size()-1;i++) {
			 int cont=0;
			 if(listaEspera.getListaPedidos().get(i).getClientes().getCPF().equals
					 (listaEspera.getListaPedidos().get(i+1).getClientes().getCPF())) {
				      ControlPedidos.contStatus(i, "Pronto", 0);
				      ControlPedidos.contStatus(i+1, "Pronto", 0);
			 }
			 
		}
	}

}
