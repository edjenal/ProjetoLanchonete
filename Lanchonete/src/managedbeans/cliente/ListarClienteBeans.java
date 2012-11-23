package managedbeans.cliente;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import bo.ClienteBO;
import bo.VendaBO;

import to.ClienteTO;
import to.VendaTO;

public class ListarClienteBeans {
	
	private List<ClienteTO> clienteTOs;
	private String nome;
	private Integer id_cli;
	private boolean mostrarTabela = false;
	
	private String valor_recebidoTela;
	
	private boolean mostrarTroco = false;
	
	private String troco;

	
	public List<ClienteTO> getClienteTOs() {
		return clienteTOs;
	}
	public void setClienteTOs(List<ClienteTO> clienteTOs) {
		this.clienteTOs = clienteTOs;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getId_cli() {
		return id_cli;
	}
	public void setId_cli(Integer id_cli) {
		this.id_cli = id_cli;
	}
	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}
	
	public String getValor_recebidoTela() {
		return valor_recebidoTela;
	}
	public void setValor_recebidoTela(String valor_recebidoTela) {
		this.valor_recebidoTela = valor_recebidoTela;
	}
	public boolean isMostrarTroco() {
		return mostrarTroco;
	}
	public void setMostrarTroco(boolean mostrarTroco) {
		this.mostrarTroco = mostrarTroco;
	}
	public String getTroco() {
		return troco;
	}
	public void setTroco(String troco) {
		this.troco = troco;
	}
	
	
	public String deletar(){
		String vazia  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_cli");
		if(vazia!=null){
			id_cli = Integer.parseInt(vazia);
		}
		ClienteBO clienteBO = new ClienteBO();
		clienteBO.remove(id_cli);
		id_cli = null;
		mostrarTabela = false;
		return null;
	}
	
	public String buscar(){
		ClienteBO clienteBO = new ClienteBO();
		clienteTOs = clienteBO.findByNm_cli(nome);
		mostrarTabela = !clienteTOs.isEmpty() ? true : false;
		return "/cliente/listar.xhtml";
	}
	
	public void ocultar(ActionEvent e){
		mostrarTabela = false;
	}
	
	public void setarId(){
		String vazia  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_cli");
		if(vazia!=null){
			id_cli = Integer.parseInt(vazia);
		}
	}
	
	public String quitar(){
		Double valor_recebido = Double.parseDouble(valor_recebidoTela.replaceAll("\\,", "."));
		valor_recebidoTela = "";
		VendaBO vendaBO = new VendaBO();
		List<VendaTO> vendaTOs = new ArrayList<VendaTO>();
		vendaTOs = vendaBO.findByIdCli(id_cli);
		if(vendaTOs!=null){
			for(VendaTO venda : vendaTOs){
				if(valor_recebido>=0.0){
					Double valor_debito = venda.getValor_debito() - valor_recebido;
					valor_recebido = valor_recebido - venda.getValor_debito();
					if(valor_debito<0){
						valor_debito = 0.0;
					}
					java.util.Date data = new java.util.Date();
					Date dt_pag_total = valor_debito > 0 ? null : new Date(data.getTime());
					vendaBO.update(valor_debito, dt_pag_total, venda.getId_venda());
				} 	
			} 
			troco = valor_recebido>=0.0 ? valor_recebido.toString().replaceAll("\\.", ",") : null;
		}
		return "/cliente/sucesso.xhtml";
	}
}
