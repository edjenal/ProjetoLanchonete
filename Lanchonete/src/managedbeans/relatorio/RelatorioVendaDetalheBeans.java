package managedbeans.relatorio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import bo.VendaBO;
import bo.VendaProdutoBO;

import to.VendaProdutoTO;
import to.VendaTO;

public class RelatorioVendaDetalheBeans {
	private int id_venda;
	
	private String valor_desconto_tela;
	private String recebido;
	private boolean editavel = false;
	private Double valor_total_venda;
	private String nm_cliente;
	private Date dt_venda;
	private Date dt_pag_total;
	private String valor_total_vendaTela;
	private String valor_total_com_descontoTela;
	
	private VendaTO vendaTO;
	
	private List<VendaProdutoTO> produtos = new ArrayList<VendaProdutoTO>();

	public int getId_venda() {
		String vazia  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_venda");
		if(vazia!=null){
			id_venda = Integer.parseInt(vazia);
			VendaBO vendaBO = new VendaBO();
			vendaTO = vendaBO.findByPrimaryKey(id_venda);
			valor_desconto_tela = vendaTO.getValor_desconto_vendaTela();
			editavel = vendaTO.getDt_pag_total() != null && !vendaTO.getDt_pag_total().equals("") ? false : true; 
			valor_total_venda = vendaTO.getValor_total_venda();
			nm_cliente = vendaTO.getNm_cliente();
			dt_venda = vendaTO.getDt_venda();
			dt_pag_total = vendaTO.getDt_pag_total();
			valor_total_vendaTela = vendaTO.getValor_total_vendaTela();
			valor_total_com_descontoTela = vendaTO.getValor_total_com_descontoTela();
			VendaProdutoBO vendaProdutoBO = new VendaProdutoBO();
			produtos = vendaProdutoBO.findById_venda(id_venda);
		}
		return id_venda;
	}

	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}

	public String getValor_desconto_tela() {
		return valor_desconto_tela;
	}

	public void setValor_desconto_tela(String valor_desconto_tela) {
		this.valor_desconto_tela = valor_desconto_tela;
	}

	public String getRecebido() {
		return recebido;
	}

	public void setRecebido(String recebido) {
		this.recebido = recebido;
	}

	public boolean isEditavel() {
		return editavel;
	}

	public void setEditavel(boolean editavel) {
		this.editavel = editavel;
	}
	
	public Double getValor_total_venda() {
		return valor_total_venda;
	}

	public void setValor_total_venda(Double valor_total_venda) {
		this.valor_total_venda = valor_total_venda;
	}
	
	public String getNm_cliente() {
		return nm_cliente;
	}

	public void setNm_cliente(String nm_cliente) {
		this.nm_cliente = nm_cliente;
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

	public String getValor_total_vendaTela() {
		return valor_total_vendaTela;
	}

	public void setValor_total_vendaTela(String valor_total_vendaTela) {
		this.valor_total_vendaTela = valor_total_vendaTela;
	}

	public String getValor_total_com_descontoTela() {
		return valor_total_com_descontoTela;
	}

	public void setValor_total_com_descontoTela(String valor_total_com_descontoTela) {
		this.valor_total_com_descontoTela = valor_total_com_descontoTela;
	}

	public VendaTO getVendaTO() {
		return vendaTO;
	}

	public void setVendaTO(VendaTO vendaTO) {
		this.vendaTO = vendaTO;
	}

	public List<VendaProdutoTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<VendaProdutoTO> produtos) {
		this.produtos = produtos;
	}
	
	//funções xD
	public String update(){
		valor_desconto_tela = valor_desconto_tela.replaceAll("\\.", "").replaceAll(",", ".");
		Double valor_desconto_venda = valor_desconto_tela == null || valor_desconto_tela.equals("") ? 0.0 : Double.parseDouble(valor_desconto_tela);
		
		recebido = recebido.replaceAll("\\.", "").replaceAll(",", ".");
		Double rece = recebido==null || recebido.equals("") ? 0.0 : Double.parseDouble(recebido);
		
		Double debito = valor_total_venda - (rece + valor_desconto_venda);
		
		java.util.Date data = new java.util.Date();
		Date dt_pag_total = debito > 0 ? null : new Date(data.getTime());
		VendaBO vendaBO = new VendaBO();
		cleanBeans();
		return !vendaBO.update(debito, valor_desconto_venda, dt_pag_total, id_venda) ? "/relatorio/balcao/filtro.xhtml" : null;
	}
	
	private void cleanBeans(){
		valor_desconto_tela = null;
		recebido = null;
		editavel = false;
		valor_total_venda = null;
		nm_cliente =  null;
		dt_venda =  null;
		dt_pag_total =  null;
		valor_total_vendaTela =  null;
		valor_total_com_descontoTela =  null;
		vendaTO = null;
		produtos = new ArrayList<VendaProdutoTO>();
	}
}
