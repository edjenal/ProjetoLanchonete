package managedbeans.cliente;

import bo.ClienteBO;

public class NovoClienteBeans {

	private int id_cli;
	private String nm_cli;
	private String tel_cli;
	private String cpf_cli;
	
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
	
	public String inserir(){
		try{
			ClienteBO clienteBO = new ClienteBO();
			return clienteBO.insert(nm_cli, tel_cli, cpf_cli) ? null : "/cliente/listar.xhtml";
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
}
