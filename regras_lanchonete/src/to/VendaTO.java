package to;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class VendaTO {
	//.toString().replaceAll("\\.", ",")
	private int id_venda;
	private Integer id_cli;
	private Integer id_func;
	private Integer id_mesa;
	private Double valor_total_venda;
	private Double valor_desconto_venda;
	private Double valor_debito;
	private Date dt_venda;
	private Date dt_pag_total;
	
	private String nm_cliente;
	private String valor_total_vendaTela;
	private String valor_desconto_vendaTela;
	private String valor_debitoTela;
	private String valor_total_com_descontoTela;
	
	private String dt_pag;
	private String dt_ven;
	
	
	public int getId_venda() {
		return id_venda;
	}
	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}
	public Integer getId_cli() {
		return id_cli;
	}
	public void setId_cli(Integer id_cli) {
		this.id_cli = id_cli;
	}
	public Integer getId_func() {
		return id_func;
	}
	public void setId_func(Integer id_func) {
		this.id_func = id_func;
	}
	public Integer getId_mesa() {
		return id_mesa;
	}
	public void setId_mesa(Integer id_mesa) {
		this.id_mesa = id_mesa;
	}
	public Double getValor_total_venda() {
		return valor_total_venda;
	}
	public void setValor_total_venda(Double valor_total_venda) {
		this.valor_total_venda = valor_total_venda;
	}
	public Double getValor_desconto_venda() {
		return valor_desconto_venda;
	}
	public void setValor_desconto_venda(Double valor_desconto_venda) {
		this.valor_desconto_venda = valor_desconto_venda;
	}
	public Double getValor_debito() {
		return valor_debito;
	}
	public void setValor_debito(Double valor_debito) {
		this.valor_debito = valor_debito;
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
	public String getValor_total_vendaTela() {
		return valor_total_vendaTela = getValor_total_venda().toString().replaceAll("\\.", ",");
	}
	public void setValor_total_vendaTela(String valor_total_vendaTela) {
		this.valor_total_vendaTela = valor_total_vendaTela;
	}
	public String getValor_desconto_vendaTela() {
		return valor_desconto_vendaTela = getValor_desconto_venda().toString().replaceAll("\\.", ",");
	}
	public void setValor_desconto_vendaTela(String valor_desconto_vendaTela) {
		this.valor_desconto_vendaTela = valor_desconto_vendaTela;
	}
	public String getValor_debitoTela() {
		return valor_debitoTela = getValor_debito().toString().replaceAll("\\.", ",");
	}
	public void setValor_debitoTela(String valor_debitoTela) {
		this.valor_debitoTela = valor_debitoTela;
	}
	public String getValor_total_com_descontoTela() {
		Double resultado = getValor_total_venda()-getValor_desconto_venda();
		return valor_total_com_descontoTela = resultado.toString().replaceAll("\\.", ",");
	}
	public void setValor_total_com_descontoTela(String valor_total_com_descontoTela) {
		this.valor_total_com_descontoTela = valor_total_com_descontoTela;
	}
	
	public String getDt_pag() {
		if(getDt_pag_total()!=null){
			java.util.Date data = getDt_pag_total();
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd-MM-yyyy");
			return format.format(data.getTime());
		} else {
			return null;
		}
		
	    
	}
	public void setDt_pag(String dt_pag) {
		this.dt_pag = dt_pag;
	}
	
	public String getDt_ven() {
		if(getDt_venda()!=null){
			java.util.Date data = getDt_venda();
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd-MM-yyyy");
			return format.format(data.getTime());
		} else {
			return null;
		}
	}
	
	public void setDt_ven(String dt_ven) {
		this.dt_ven = dt_ven;
	}
	
	
}
