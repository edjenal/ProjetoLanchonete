package to;
//faltam atributos
public class FuncionarioTO {
	private Integer idFunc; 
	private Integer idDepFunc; 
	private boolean flAtivoFunc;
	private String nmFunc; 
	private String cpfFunc; 
	private String telFunc;
	private String dsDepFunc;
	private String ativo;
	
	public Integer getIdFunc() {
		return idFunc;
	}
	public void setIdFunc(Integer idFunc) {
		this.idFunc = idFunc;
	}
	public Integer getIdDepFunc() {
		return idDepFunc;
	}
	public void setIdDepFunc(Integer idDepFunc) {
		this.idDepFunc = idDepFunc;
	}
	public boolean isFlAtivoFunc() {
		return flAtivoFunc;
	}
	public void setFlAtivoFunc(boolean flAtivoFunc) {
		this.flAtivoFunc = flAtivoFunc;
	}
	public String getNmFunc() {
		return nmFunc;
	}
	public void setNmFunc(String nmFunc) {
		this.nmFunc = nmFunc;
	}
	public String getCpfFunc() {
		return cpfFunc;
	}
	public void setCpfFunc(String cpfFunc) {
		this.cpfFunc = cpfFunc;
	}
	public String getTelFunc() {
		return telFunc;
	}
	public void setTelFunc(String telFunc) {
		this.telFunc = telFunc;
	}
	public String getDsDepFunc() {
		return dsDepFunc;
	}
	public void setDsDepFunc(String dsDepFunc) {
		this.dsDepFunc = dsDepFunc;
	}
	public String getAtivo() {
		return isFlAtivoFunc() ? "Sim" : "N�o";
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	

}
