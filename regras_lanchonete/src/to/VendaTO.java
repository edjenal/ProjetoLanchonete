package to;

import java.sql.Date;

public class VendaTO {
	
	private int id_venda;
	private int id_cli;
	private double total_venda;
	private double debito;
	private Date dt_venda;
	private Date dt_pag_total;
	
	private String nm_cliente;
	
	public int getId_venda() {
		return id_venda;
	}
	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}
	public int getId_cli() {
		return id_cli;
	}
	public void setId_cli(int id_cli) {
		this.id_cli = id_cli;
	}
	public double getTotal_venda() {
		return total_venda;
	}
	public void setTotal_venda(double total_venda) {
		this.total_venda = total_venda;
	}
	public double getDebito() {
		return debito;
	}
	public void setDebito(double debito) {
		this.debito = debito;
	}
	public Date getDt_venda() {
		return dt_venda;
	}
	public void setDt_venda(Date dt_venda) {
		this.dt_venda = dt_venda;
	}
	public Date getDt_pag_total() {
		return dt_pag_total;
	}
	public void setDt_pag_total(Date dt_pag_total) {
		this.dt_pag_total = dt_pag_total;
	}
	public String getNm_cliente() {
		return nm_cliente;
	}
	public void setNm_cliente(String nm_cliente) {
		this.nm_cliente = nm_cliente;
	}
	
	
}
