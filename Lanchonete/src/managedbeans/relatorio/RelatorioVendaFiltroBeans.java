package managedbeans.relatorio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import to.VendaTO;

import bo.VendaBO;

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
}
