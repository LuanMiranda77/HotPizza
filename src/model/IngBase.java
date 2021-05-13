package model;


public class IngBase extends ListaIngred {
	
	public IngBase(String tamanho) {
		
		if(tamanho.equals("P")) {
			this.setNome("DISCO DE MASSA - P");
			this.setQuant((float) 0.100);
			this.setPreco((float) 2.00);
			
		}
		else if(tamanho.equals("M")) {
			this.setNome("DISCO DE MASSA - M");
			this.setQuant((float) 0.200);
			this.setPreco((float) 3.00);
			
		}
		else if(tamanho.equals("G")) {
			this.setNome("DISCO DE MASSA - G");
			this.setQuant((float) 0.250);
			this.setPreco((float) 3.00);
			
		}
		else {
			this.setNome("DISCO DE MASSA - GG");
			this.setQuant((float) 0.300);
			this.setPreco((float) 4.00);
		}
	}
}
