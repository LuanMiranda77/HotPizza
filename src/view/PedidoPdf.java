package view;

import java.awt.Desktop;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;

import java.util.Date;



import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import DTO.PedidosDTO;
import controller.ControlEmpresa;
import controller.ControlPedidos;
import model.Pedidos;


public class PedidoPdf {
	
	
	public static void gerarPedido(int cod, Pedidos ped) throws DocumentException, ParseException, IOException{
		
		
	    	Document boleto=new Document();
	    	String arquivoPdf="relatorio.pdf";
	    	
    	
    		 PdfWriter.getInstance(boleto, new FileOutputStream(arquivoPdf));
    	     boleto.open();
    	     
    	     Paragraph p  =new Paragraph("Relatorio.pdf");
    	     p.setAlignment(0);
    	     boleto.add(p);
    	     p=new Paragraph("\n");
    	     boleto.add(p);
    	     
    	     Image logo = Image.getInstance("Icon/logo_pequena.png");
    	     logo.scaleToFit(150, 150);
    	     logo.setAlignment(1);
    	     boleto.add(logo);
    	     
    	     
    	     //adata do pedido
    	     Date d = new Date();
   	         String hoje = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
    	     
    	     //Titulo
    	     PdfPTable titulo=new PdfPTable(1);
    	     PdfPCell celEmp=new PdfPCell(new Paragraph("DADOS DA EMPRESA:"));
    	     celEmp.setBackgroundColor(new BaseColor(139, 137, 137));
    	     celEmp.setBorder(5);
    	     celEmp.setHorizontalAlignment(1);
    	     titulo.addCell(celEmp);
    	     boleto.add(titulo);
    	     
    	     //Nome da Empresa CNPJ
    	     PdfPTable tabEmpresa=new PdfPTable(2);
    	     float[] t = {0.6f,0.4f};
    	     tabEmpresa.setWidths(t);
    	     tabEmpresa.addCell(new PdfPCell(new Paragraph("Empresa: " + ControlEmpresa.retEmpresa().getNome())));
    	     tabEmpresa.addCell(new PdfPCell(new Paragraph("CNPJ: "+ControlEmpresa.retEmpresa().getCNPJ())));
    	     boleto.add(tabEmpresa);
    	     
    	     //Cp da Empresa
    	     PdfPTable endEmpresa=new PdfPTable(2);
    	     float[] t1 = {0.6f,0.4f};
    	     endEmpresa.setWidths(t1);
    	     endEmpresa.addCell(new PdfPCell(new Paragraph("Eendereço: "+ControlEmpresa.retEmpresa().getEnd())));
    	     endEmpresa.addCell(new PdfPCell(new Paragraph("Cidade: "+ControlEmpresa.retEmpresa().getCidade())));
    	     boleto.add(endEmpresa);
    	     
    	     PdfPTable cepEmpresa=new PdfPTable(2);
    	     float[] t4= {0.6f,0.4f};
    	     cepEmpresa.setWidths(t4);
    	     cepEmpresa.addCell(new PdfPCell(new Paragraph("Bairro: "+ControlEmpresa.retEmpresa().getBairro())));
    	     cepEmpresa.addCell(new PdfPCell(new Paragraph("Cep: "+ControlEmpresa.retEmpresa().getCep())));
    	     boleto.add(cepEmpresa);
    	     
    	     p=new Paragraph("\n");
    	     boleto.add(p);
    	     
    	     //Titulo Cliente
    	     PdfPTable tiCliente=new PdfPTable(1);
    	     PdfPCell celCliente=new PdfPCell(new Paragraph("DADOS DO CLIENTE:"));
    	     celCliente.setBackgroundColor(new BaseColor(139, 137, 137));
    	     celCliente.setBorder(5);
    	     celCliente.setHorizontalAlignment(1);
    	     tiCliente.addCell(celCliente);
    	     boleto.add(tiCliente);
    	     
    	     //Nome da cliente
    	     PdfPTable tabCliente=new PdfPTable(3);
    	     float[] t3 = {0.5f,0.4f,0.4F};
    	     tabCliente.setWidths(t3);
    	     tabCliente.addCell(new PdfPCell(new Paragraph("Cliente: " + ped.getClientes().getNome())));
    	     tabCliente.addCell(new PdfPCell(new Paragraph("CPF: "+ped.getClientes().getCPF())));
    	     tabCliente.addCell(new PdfPCell(new Paragraph("TEL:: "+ped.getClientes().getTelefone())));
    	     boleto.add(tabCliente);
    	     
    	     //Cp da cliente
    	     PdfPTable endCliente=new PdfPTable(1);
    	     endCliente.addCell(new PdfPCell(new Paragraph("Eendereço: "+ped.getClientes().getEnd())));
    	     boleto.add(endCliente);
    	     
    	     //pular linha
    	     p=new Paragraph("\n");
    	     boleto.add(p);
    	    
    	     
    	    
    	     
    	     //tabela 
    	    
    	     PdfPTable tabela=new PdfPTable(1);
    	     PdfPCell celula1=new PdfPCell(new Paragraph("Pedido Nº-"+ cod +"      Data: "+hoje));
    	     celula1.setBackgroundColor(new BaseColor(139, 137, 137));
    	     celula1.setBorder(5);
    	     celula1.setHorizontalAlignment(1);
    	     tabela.addCell(celula1);
    	     boleto.add(tabela);
    	     
    	     PdfPTable tabela2=new PdfPTable(6);
    	     float[] tams = {0.07f,0.36f,0.08f,0.1f,0.12f,0.16f};
    	     tabela2.setWidths(tams);
    	     tabela2.addCell(new PdfPCell(new Paragraph("ITEM")));
    	     tabela2.addCell(new PdfPCell(new Paragraph("PRODUTO")));  
    	     tabela2.addCell(new PdfPCell(new Paragraph("UND"))); 
    	     tabela2.addCell(new PdfPCell(new Paragraph("QUANT")));
    	     tabela2.addCell(new PdfPCell(new Paragraph("PREÇO")));
    	     tabela2.addCell(new PdfPCell(new Paragraph("SUBTOTAL")));
   	         boleto.add(tabela2);
   	         
    	     
   	         
    	    
      	    
      	    // for(Pizzas p1: e.getPizza()) {
    	    
    	    float[] tam = {0.07f,0.36f,0.08f,0.1f,0.12f,0.16f};
     	    
  /*   	    for(PedidosDTO ped1 :ControlPedidos.pesPedido().getListaPedidos()) {
     	      if(ped1.getCod()==cod) {	
     	    	  for(int cont = 0 ; cont<ped1.getPizza().size();cont++) {
     	     PdfPTable tabela3=new PdfPTable(6); 
     	     tabela3.setWidths(tam);
    	     tabela3.addCell(new PdfPCell(new Paragraph(""+ped.getPizza().get(cont).getCod())));
    	     tabela3.addCell(new PdfPCell(new Paragraph(ped.getPizza().get(cont).getNome())));  
   	         tabela3.addCell(new PdfPCell(new Paragraph(ped.getPizza().get(cont).getTamnaho())));
   	         tabela3.addCell(new PdfPCell(new Paragraph(""+ped.getPizza().get(cont).getQuantvend())));
 	         tabela3.addCell(new PdfPCell(new Paragraph(""+ped.getPizza().get(cont).getValorcomp())));  
	         tabela3.addCell(new PdfPCell(new Paragraph(""+ped.getPizza().get(cont).getQuantvend()*(ped.getPizza().get(cont).getValorcomp()))));
    	     boleto.add(tabela3); 
     	    	  }
     	      }
     	    }
    	     */
    	     
      	    // total
    	     PdfPTable total=new PdfPTable(1);
    	     PdfPCell celtotal=new PdfPCell(new Paragraph("TOTAL------------------------>: R$ "+ped.getValor()));
    	     celtotal.setBackgroundColor(new BaseColor(139, 137, 137));
    	     celtotal.setBorder(5);
    	     celtotal.setHorizontalAlignment(1);
    	     total.addCell(celtotal);
    	     boleto.add(total);
      	
    	     
   	         
    	     boleto.close();
   	         Desktop.getDesktop().open(new File("relatorio.pdf"));
   	         
    	
    	
    	
    }
}
