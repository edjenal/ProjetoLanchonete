package managedbeans.cliente;

import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import bo.ClienteBO;

import to.ClienteTO;

public class ListarClienteBeans {
	
	private List<ClienteTO> clienteTOs;
	private String nome;
	private int id_cli;
	private boolean mostrarTabela = false;

	
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
	public int getId_cli() {
		return id_cli;
	}
	public void setId_cli(int id_cli) {
		this.id_cli = id_cli;
	}
	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	public String deletar(){
		String vazia  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_cli");
		if(vazia!=null){
			id_cli = Integer.parseInt(vazia);
		}
		ClienteBO clienteBO = new ClienteBO();
		clienteBO.remove(id_cli);
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
}
