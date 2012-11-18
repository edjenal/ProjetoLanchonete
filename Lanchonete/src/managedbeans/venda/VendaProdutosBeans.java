package managedbeans.venda;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import to.CategoriaTO;
import to.ProdutoTO;
import bo.CategoriaBO;
import bo.ClienteBO;
import bo.ProdutoBO;
import bo.VendaBO;
import bo.VendaProdutoBO;

public class VendaProdutosBeans {
	private List<ProdutoTO> produtoTOs;
	private String nome;
	private int id_cat;
	private int id_prod;
	private boolean mostrarTabela = false;
	private boolean mostrarTabelaCompras = false;
	private boolean mostrarFinalizado = false;
	private List<CategoriaTO> categoria;
	
	private Double total = 0.0;
	private String totalTela;
	private String desconto ;
	private String recebido ;
	
	private int id_cli;
	private String nm_cli;
	
	private List<ProdutoTO> produtosSelecionados = new ArrayList<ProdutoTO>();
	private Integer qtd_venda = 1;
	
	
	//gets e sets
	public List<ProdutoTO> getProdutoTOs() {
		return produtoTOs;
	}
	public void setProdutoTOs(List<ProdutoTO> produtoTOs) {
		this.produtoTOs = produtoTOs;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId_cat() {
		return id_cat;
	}
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	public int getId_prod() {
		return id_prod;
	}
	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
	}
	public boolean isMostrarTabela() {
		return mostrarTabela;
	}
	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}
	public List<CategoriaTO> getCategoria() {
		CategoriaBO categoriaBO = new CategoriaBO();
		return categoria = categoriaBO.findAll();
	}
	public void setCategoria(List<CategoriaTO> categoria) {
		this.categoria = categoria;
	}
	public int getId_cli() {
		String vazia  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_cli");
		if(vazia!=null){
			id_cli = Integer.parseInt(vazia);
		}
		return id_cli;
	}
	public void setId_cli(int id_cli) {
		this.id_cli = id_cli;
	}
	public String getNm_cli() {
		String vazia  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nm_cli");
		if(vazia!=null){
			nm_cli = vazia;
		}
		return nm_cli;
	}
	public void setNm_cli(String nm_cli) {
		this.nm_cli = nm_cli;
	}
	public List<ProdutoTO> getProdutosSelecionados() {
		return produtosSelecionados;
	}
	public void setProdutosSelecionados(List<ProdutoTO> produtosSelecionados) {
		this.produtosSelecionados = produtosSelecionados;
	}
	public boolean isMostrarTabelaCompras() {
		return mostrarTabelaCompras;
	}
	public void setMostrarTabelaCompras(boolean mostrarTabelaCompras) {
		this.mostrarTabelaCompras = mostrarTabelaCompras;
	}
	public Integer getQtd_venda() {
		return qtd_venda;
	}
	public void setQtd_venda(Integer qtd_venda) {
		this.qtd_venda = qtd_venda;
	}
	public boolean isMostrarFinalizado() {
		return mostrarFinalizado;
	}
	public void setMostrarFinalizado(boolean mostrarFinalizado) {
		this.mostrarFinalizado = mostrarFinalizado;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getTotalTela() {
		return totalTela;
	}
	public void setTotalTela(String totalTela) {
		this.totalTela = totalTela;
	}
	public String getDesconto() {
		return desconto;
	}
	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}
	public String getRecebido() {
		return recebido;
	}
	public void setRecebido(String recebido) {
		this.recebido = recebido;
	}
	//fim dos gets e sets
	
	
	
	
	
	//funções
	public String buscar(){
		ProdutoBO produtoBO = new ProdutoBO();
		produtoTOs = produtoBO.findByDs_prod(nome, id_cat);
		if(produtoTOs!=null){
			mostrarTabela = true;
		}
		else {
			mostrarTabela = false;
		}
		return "/venda/produtos.xhtml";
	}
	
	public void ocultar(ActionEvent e){
		mostrarTabela = false;
	}
	
	public String addProduto(){
		String vazia  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_prod");
		if(vazia!=null){
			ProdutoTO produtoTO = new ProdutoTO();
			produtoTO.setId_prod(Integer.parseInt(vazia));
			produtoTO.setDs_prod(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ds_prod"));
			produtoTO.setId_cat(Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_cat")));
			produtoTO.setDs_cat(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ds_cat"));
			produtoTO.setPreco_prod(Double.parseDouble(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("preco_prod")));
			int qtd = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("qtd_venda"));
			qtd = qtd == 0 ? qtd = 1 : qtd;
			produtoTO.setQtd_venda(qtd);
			boolean add = true;
			for (int i=0; i<produtosSelecionados.size(); i++){
				if(produtosSelecionados.get(i).getId_prod() == produtoTO.getId_prod()){
					produtosSelecionados.get(i).setQtd_venda(produtosSelecionados.get(i).getQtd_venda()+produtoTO.getQtd_venda());
					add = false;
				} 
			}
			if(add){
				produtosSelecionados.add(produtoTO);
			}
		}
		mostrarTabelaCompras = true;
		qtd_venda = 1;
		return "/venda/produtos.xhtml";
	}
	
	public String removerProduto(){
		//pegar objeto da sessão
		Map<String, Object> contexto = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		produtosSelecionados.remove((ProdutoTO) contexto.get("rs"));
		if(produtosSelecionados.isEmpty()){
			mostrarTabelaCompras = false;
		}
		return "/venda/produtos.xhtml";
	}
	
	private void cleanBeans(){
		produtosSelecionados = new ArrayList<ProdutoTO>();
		nome = null;
		mostrarTabela = false;
		mostrarTabelaCompras = false;
		mostrarFinalizado = false;
		qtd_venda = 1;
		total = 0.0;
		desconto = null;
		recebido = null;
	}
	
	public String cancelar(){
		cleanBeans();
		return "/venda/cliente.xhtml";
	}
	
	public String concluir(){
		if(!produtosSelecionados.isEmpty()){
			for (int i=0; i<produtosSelecionados.size(); i++){
				Double preco_qtd = 0.0;
				preco_qtd += produtosSelecionados.get(i).getQtd_venda()*produtosSelecionados.get(i).getPreco_prod();
				total += preco_qtd;
			}
			totalTela = total.toString().replaceAll("\\.", ",");
			mostrarFinalizado = true;
		} else {
			mostrarFinalizado = false;
		}
		
		return "/venda/produtos.xhtml";
	}
	
	public String retornarCompra(){
		total = 0.0;
		totalTela = null;
		mostrarFinalizado = false;
		return "/venda/produtos.xhtml";
	}
	
	public String confirmar(){
		String retorno = "";
		id_cli = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_cli"));
		desconto = desconto.replaceAll("\\.", "").replaceAll(",", ".");
		recebido = recebido.replaceAll("\\.", "").replaceAll(",", ".");
		Double desc = desconto != null && !desconto.equals("") ? Double.parseDouble(desconto) : 0.0 ;
		Double rece = recebido != null && !recebido.equals("") ? Double.parseDouble(recebido) : 0.0 ;
		Double debito = total - (rece + desc);
		if(debito>=0){
			java.util.Date data = new java.util.Date();
			Date dt_pag_total = debito > 0 ? null : new Date(data.getTime());
			VendaBO vendaBO = new VendaBO();
			//retorno é o id_venda casatrada
			Integer id_venda = vendaBO.insert(id_cli, total, desc, debito, dt_pag_total);
			//inserindo na tabela venda_produto
			for(int i = 0; i<produtosSelecionados.size(); i++){
				VendaProdutoBO vendaProdutoBO = new VendaProdutoBO();
				vendaProdutoBO.insert(produtosSelecionados.get(i).getId_prod(), id_venda, produtosSelecionados.get(i).getQtd_venda(), produtosSelecionados.get(i).getPreco_prod());
			}
			cleanBeans();
			retorno = "/venda/sucesso.xhtml";
		} else {
			desconto = desconto.replaceAll("\\.", ",");
			recebido = recebido.replaceAll("\\.", ",");
			retorno = "/venda/produtos.xhtml";
		}
		return retorno;
		
	}
	
}
