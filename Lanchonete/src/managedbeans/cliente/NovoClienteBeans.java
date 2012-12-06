package managedbeans.cliente;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import bo.ClienteBO;

public class NovoClienteBeans {

	private int id_cli;
	private String nm_cli;
	private String tel_cli;
	private String cpf_cli;
	
	private UIComponent cpf;
	
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
	
	public UIComponent getCpf() {
		return cpf;
	}
	public void setCpf(UIComponent cpf) {
		this.cpf = cpf;
	}
	public String inserir(){
		try{
			ClienteBO clienteBO = new ClienteBO();
			if(clienteBO.findByCpf(cpf_cli)==null){
				return clienteBO.insert(nm_cli, tel_cli, cpf_cli) ? null : "/cliente/listar.xhtml";
			} else {
				FacesMessage mensagem = new FacesMessage("CPF: " + cpf_cli + " já está cadastrado!");
				FacesContext.getCurrentInstance().addMessage("form", mensagem);
				return null;
			}
			
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
}
