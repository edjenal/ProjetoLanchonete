package managedbeans.relatorio;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import to.VendaTO;
import bo.VendaBO;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class RelatorioVendaFiltroBeans {
	
	private Date dt_inicial;
	private Date dt_final = new Date();
	private String nm_cli;
	private boolean tabelaVendas = false;
	private List<VendaTO> vendas = new ArrayList<VendaTO>();
	
	private String total_arecadado;
	private String total_debito;
	
	private int situacao;

	public Date getDt_inicial() {
		return dt_inicial;
	}

	public void setDt_inicial(Date dt_inicial) {
		this.dt_inicial = dt_inicial;
	}

	public Date getDt_final() {
		return dt_final;
	}

	public void setDt_final(Date dt_final) {
		this.dt_final = dt_final;
	}
	public String getNm_cli() {
		return nm_cli;
	}

	public void setNm_cli(String nm_cli) {
		this.nm_cli = nm_cli;
	}
	public boolean isTabelaVendas() {
		return tabelaVendas;
	}

	public void setTabelaVendas(boolean tabelaVendas) {
		this.tabelaVendas = tabelaVendas;
	}

	public List<VendaTO> getVendas() {
		return vendas;
	}

	public void setVendas(List<VendaTO> vendas) {
		this.vendas = vendas;
	}
	
	public String getTotal_arecadado() {
		return total_arecadado;
	}

	public void setTotal_arecadado(String total_arecadado) {
		this.total_arecadado = total_arecadado;
	}
	
	public String getTotal_debito() {
		return total_debito;
	}

	public void setTotal_debito(String total_debito) {
		this.total_debito = total_debito;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	public String filtar(){
		java.sql.Date inicio = dt_inicial!=null ? new java.sql.Date(dt_inicial.getTime()) : null;
		java.sql.Date fim = dt_final!=null ? new java.sql.Date(dt_final.getTime()) : null;
		VendaBO vendaBO = new VendaBO();
		vendas = vendaBO.findByFiltro(inicio, fim, nm_cli, situacao);
		if(!vendas.isEmpty()){
			tabelaVendas = true;
			Double valorT = 0.0;
			Double valorD = 0.0;
			for(int i=0;i<vendas.size();i++){
				valorT += vendas.get(i).getValor_total_venda() - (vendas.get(i).getValor_desconto_venda() + vendas.get(i).getValor_debito());
				total_arecadado = valorT.toString().replaceAll("\\.", ",");
				valorD += vendas.get(i).getValor_debito();
				total_debito = valorD.toString().replaceAll("\\.", ",");
			}
		} else {
			tabelaVendas = false;
		}
		return "/relatorio/balcao/filtro.xhtml";
	}
	
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		
		Document pdf = (Document) document;
		pdf.setMargins(1, 1, 10, 10);
		
	    pdf.setPageSize(PageSize.A4);
	    pdf.setHtmlStyleClass("font-size:12");
	    pdf.open();
	    
	    PdfPTable tabela = new PdfPTable(3); //cria uma tabela com 3 colunas
	    PdfPCell celula1 = new PdfPCell(Image.getInstance("/imagens/sanduiche1.jpg")); //cria uma celula com parametro de Image.getInstance com o caminho da imagem do cabeçalho

	    Paragraph p = new Paragraph("Lista de Vendas"); 
	    PdfPCell celula2 = new PdfPCell(p); //adiciona o paragrafo com o titulo na segunda celula.
	    
	    p = new Paragraph(new SimpleDateFormat("dd/MM/yyyy hh:mm").format(new Date().getTime())); 
	    PdfPCell celula3 = new PdfPCell(p); //adiciona o paragrafo com o titulo na segunda celula.

	    celula1.setBorder(-1); // tira as bordas da celula 
	    
	    celula2.setBorder(-1);
	    celula2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
	    celula2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);//centro da tabela
	    
	    celula3.setBorder(-1);
	    celula3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	    celula3.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);//parte de baixo da tabela

	    tabela.addCell(celula1); //adiciona as celulas na tabela.
	    tabela.addCell(celula2);
	    tabela.addCell(celula3);
	    
	    pdf.add(tabela); // coloca a tabela na pagina do PDF.
	    
	    pdf.add(new Paragraph(" "));
	    
	}
	
	public void postProcessPDF(Object document) throws DocumentException {
		Document pdf = (Document) document;
		
		PdfPTable tabela = new PdfPTable(1); 

	    Paragraph p = new Paragraph("Rodapé \n"); 
	    PdfPCell celula2 = new PdfPCell(p);
	    
	    celula2.setBorder(-1);
	    celula2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
	    celula2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	    
	    tabela.addCell(celula2);
	    
	    pdf.add(tabela);
	    
		pdf.add(new Paragraph(" "));
		
	}
}
