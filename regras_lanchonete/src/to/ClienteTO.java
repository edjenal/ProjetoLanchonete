package to;

public class ClienteTO {
	
	private int id_cli;
	private String nm_cli;
	private String tel_cli;
	private String cpf_cli;
	
	private Double valor_debito;
	
	private String valor_debito_tela;
	
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
	public Double getValor_debito() {
		return valor_debito;
	}
	public void setValor_debito(Double valor_debito) {
		this.valor_debito = valor_debito;
	}
	public String getValor_debito_tela() {
		valor_debito_tela = valor_debito!=null ? valor_debito.toString().replaceAll("\\.", ",") : "0,0";
		return valor_debito_tela;
	}
	public void setValor_debito_tela(String valor_debito_tela) {
		this.valor_debito_tela = valor_debito_tela;
	}
	
	
}
