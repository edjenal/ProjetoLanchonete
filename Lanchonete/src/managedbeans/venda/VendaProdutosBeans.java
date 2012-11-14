package managedbeans.venda;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import to.CategoriaTO;
import to.ProdutoTO;
import bo.CategoriaBO;
import bo.ClienteBO;
import bo.ProdutoBO;

public class VendaProdutosBeans {
	private List<ProdutoTO> produtoTOs;
	private String nome;
	private int id_cat;
	private int id_prod;
	private boolean mostrarTabela = false;
	private boolean mostrarTabelaCompras = false;
	private List<CategoriaTO> categoria;
	
	private int id_cli;
	private String nm_cli;
	
	private List<ProdutoTO> produtosSelecionados;
	
	
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
		ClienteBO clienteBO = new ClienteBO();
		nm_cli = clienteBO.findByPrimaryKey(getId_cli()).getNm_cli();
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
		//Como ??? pegar objeto da sessão????
		Map<String, Object> contexto = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		produtosSelecionados.add((ProdutoTO) contexto.get("rs"));
		mostrarTabelaCompras = true;
		return "/venda/produtos.xhtml";
	}
	
	public String removerProduto(){
		//pegar objeto da sessão
		Map<String, Object> contexto = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		produtosSelecionados.remove((ProdutoTO) contexto.get("rs"));
		return "/venda/produtos.xhtml";
	}
}
