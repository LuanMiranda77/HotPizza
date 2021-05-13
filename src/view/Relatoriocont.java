package view;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;



import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controller.ControlContabil;
import controller.ControlEmpresa;

public class Relatoriocont {
	
		
	
	
	public Relatoriocont(int cod) throws DocumentException, IOException{
		Document historico=new Document();
    	String arquivoPdf="relatorio.pdf";
    	
	
		 PdfWriter.getInstance(historico, new FileOutputStream(arquivoPdf));
		 historico.open();
	     
	     Paragraph p  =new Paragraph("Relatorio.pdf");
	     p.setAlignment(0);
	     historico.add(p);
	     p=new Paragraph("\n");
	     historico.add(p);
	     
	     Image logo = Image.getInstance("Icon/logo_pequena.png");
	     logo.scaleToFit(150, 150);
	     logo.setAlignment(1);
	     historico.add(logo);
	     
	     
	     
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
	     historico.add(titulo);
	     
	     //Nome da Empresa CNPJ
	     PdfPTable tabEmpresa=new PdfPTable(2);
	     float[] t = {0.6f,0.4f};
	     tabEmpresa.setWidths(t);
	     tabEmpresa.addCell(new PdfPCell(new Paragraph("Empresa: " + ControlEmpresa.retEmpresa().getNome())));
	     tabEmpresa.addCell(new PdfPCell(new Paragraph("CNPJ: "+ControlEmpresa.retEmpresa().getCNPJ())));
	     historico.add(tabEmpresa);
	     
	     //Cp da Empresa
	     PdfPTable endEmpresa=new PdfPTable(2);
	     float[] t1 = {0.6f,0.4f};
	     endEmpresa.setWidths(t1);
	     endEmpresa.addCell(new PdfPCell(new Paragraph("Eendereço: "+ControlEmpresa.retEmpresa().getEnd())));
	     endEmpresa.addCell(new PdfPCell(new Paragraph("Cidade: "+ControlEmpresa.retEmpresa().getCidade())));
	     historico.add(endEmpresa);
	     
	     PdfPTable cepEmpresa=new PdfPTable(2);
	     float[] t4= {0.6f,0.4f};
	     cepEmpresa.setWidths(t4);
	     cepEmpresa.addCell(new PdfPCell(new Paragraph("Bairro: "+ControlEmpresa.retEmpresa().getBairro())));
	     cepEmpresa.addCell(new PdfPCell(new Paragraph("Cep: "+ControlEmpresa.retEmpresa().getCep())));
	     historico.add(cepEmpresa);
	     
	     p=new Paragraph("\n");
	     historico.add(p);
	     
	     PdfPTable tabela=new PdfPTable(1);
	     PdfPCell celula1=new PdfPCell(new Paragraph("Relatorio de Contabilistico     "     + "Data: "+hoje));
	     celula1.setBackgroundColor(new BaseColor(139, 137, 137));
	     celula1.setBorder(5);
	     celula1.setHorizontalAlignment(1);
	     tabela.addCell(celula1);
	     historico.add(tabela);
	     
	     
	     
	     PdfPTable tabela2=new PdfPTable(5);
	     float[] tams = {0.1f,0.2f,0.15f,0.3f,0.3f};
	     tabela2.setWidths(tams);
	     tabela2.addCell(new PdfPCell(new Paragraph("COD")));
	     tabela2.addCell(new PdfPCell(new Paragraph("QUANT VENDIDADA")));  
	     tabela2.addCell(new PdfPCell(new Paragraph("LUCRO DO MES"))); 
	     tabela2.addCell(new PdfPCell(new Paragraph("PIZZA MAS VENDIDA")));
	     tabela2.addCell(new PdfPCell(new Paragraph("DATA DO RELATORIO")));
	     historico.add(tabela2);
	     
	     //Cp da Empresa
			   float[] tam = {0.1f,0.2f,0.15f,0.3f,0.3f};
	     	    
	     	  
	     	    
	     	     PdfPTable tabela3=new PdfPTable(5); 
	     	     
	     	     tabela3.setWidths(tam);
	    	     tabela3.addCell(new PdfPCell(new Paragraph(""+ControlContabil.pesContab(cod).getCod())));
	    	     tabela3.addCell(new PdfPCell(new Paragraph(""+ControlContabil.pesContab(cod).getQunat())));  
	   	         tabela3.addCell(new PdfPCell(new Paragraph(""+ControlContabil.pesContab(cod).getLucro())));
	   	         tabela3.addCell(new PdfPCell(new Paragraph(ControlContabil.pesContab(cod).getSabor())));
	 	         tabela3.addCell(new PdfPCell(new Paragraph(ControlContabil.pesContab(cod).getData())));  
	 	         historico.add(tabela3); 
	     	   
	     	    
  	
	     
	         
	     	   historico.close();
	         Desktop.getDesktop().open(new File("relatorio.pdf"));
	         
	
	
	
}
	}
	    	
