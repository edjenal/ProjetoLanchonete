package to;

public class FuncTO {
	
	private int idFunc;
	private int idDepFunc;
	private Boolean flAtivoFunc;
	private String nmFunc;
	private String cpfFunc;
	private String telFunc;
	private String dsDepFunc;
	private String ativo;
	private String dsDepFUnc;
	
	public int getIdFunc() {
		return idFunc;
	}
	public void setIdFunc(int idFunc) {
		this.idFunc = idFunc;
	}
	public int getIdDepFunc() {
		return idDepFunc;
	}
	public void setIdDepFunc(int idDepFunc) {
		this.idDepFunc = idDepFunc;
	}
	public Boolean getFlAtivoFunc() {
		return flAtivoFunc;
	}
	public void setFlAtivoFunc(Boolean flAtivoFunc) {
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
		return getFlAtivoFunc() ? "Sim" : "Não";
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	public String getDsDepFUnc() {
		return dsDepFUnc;
	}
	public void setDsDepFUnc(String dsDepFUnc) {
		this.dsDepFUnc = dsDepFUnc;
	}
	
}
