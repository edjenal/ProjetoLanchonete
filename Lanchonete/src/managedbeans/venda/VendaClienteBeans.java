package managedbeans.venda;

import java.util.List;
import javax.faces.event.ActionEvent;

import to.ClienteTO;
import bo.ClienteBO;

public class VendaClienteBeans {
	
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
	
	public String buscar(){
		ClienteBO clienteBO = new ClienteBO();
		clienteTOs = clienteBO.findByNm_cli(nome);
		mostrarTabela = true;
		nome = null;
		return "/venda/cliente.xhtml";
	}
	
	public void ocultar(ActionEvent e){
		nome = null;
		mostrarTabela = false;
	}

}
