package to;

public class MesaTO {
	
	private int idMesa;
	private int idArea;
	private String dsMesa;
	private boolean flAtivoMesa;
	private boolean flAbertaMesa;
	
	private String dsArea;
	
	private String aberta;
	private String ativa;
	
	public int getIdMesa() {
		return idMesa;
	}
	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}
	public int getIdArea() {
		return idArea;
	}
	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}
	public String getDsMesa() {
		return dsMesa;
	}
	public void setDsMesa(String dsMesa) {
		this.dsMesa = dsMesa;
	}
	public boolean isFlAtivoMesa() {
		return flAtivoMesa;
	}
	public void setFlAtivoMesa(boolean flAtivoMesa) {
		this.flAtivoMesa = flAtivoMesa;
	}
	public boolean isFlAbertaMesa() {
		return flAbertaMesa;
	}
	public void setFlAbertaMesa(boolean flAbertaMesa) {
		this.flAbertaMesa = flAbertaMesa;
	}
	
	public String getDsArea() {
		return dsArea;
	}
	public void setDsArea(String dsArea) {
		this.dsArea = dsArea;
	}
	public String getAberta() {
		return isFlAbertaMesa() ? "Sim" : "Não";
	}
	public void setAberta(String aberta) {
		this.aberta = aberta;
	}
	public String getAtiva() {
		return isFlAtivoMesa() ? "Sim" : "Não";
	}
	public void setAtiva(String ativa) {
		this.ativa = ativa;
	}
	
	
}
