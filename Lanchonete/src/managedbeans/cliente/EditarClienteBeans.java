package managedbeans.cliente;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import to.ClienteTO;

import bo.ClienteBO;

public class EditarClienteBeans {
	
	private int id_cli;
	private String nm_cli;
	private String tel_cli;
	private String cpf_cli;
	
	private ClienteTO clienteTO;
	
	@PostConstruct
	public void init(){
		String vazia  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_cli");
		if(vazia!=null){
			id_cli = Integer.parseInt(vazia);
			ClienteBO clienteBO = new ClienteBO();
			clienteTO = clienteBO.findByPrimaryKey(id_cli);
			id_cli = clienteTO.getId_cli();
			nm_cli = clienteTO.getNm_cli();
			tel_cli = clienteTO.getTel_cli();
			cpf_cli = clienteTO.getCpf_cli();
		}
	}
	
	public int getId_cli() {
		return id_cli;
	}
	public void setId_cli(int id_cli) {
		this.id_cli = id_cli;
	}
	public String getNm_cli() {
		return nm_cli;
	}
	public void setNm_cli(String nm_cli) {
		this.nm_cli = nm_cli;
	}
	public String getTel_cli() {
		return tel_cli;
	}
	public void setTel_cli(String tel_cli) {
		this.tel_cli = tel_cli;
	}
	public String getCpf_cli() {
		return cpf_cli;
	}
	public void setCpf_cli(String cpf_cli) {
		this.cpf_cli = cpf_cli;
	}
	public String update(){
		ClienteBO clienteBO = new ClienteBO();
		return clienteBO.update(nm_cli, tel_cli, cpf_cli, id_cli) ? null : "/cliente/listar.xhtml";
	}
	
}
