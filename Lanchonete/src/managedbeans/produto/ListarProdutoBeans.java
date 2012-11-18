package managedbeans.produto;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import bo.CategoriaBO;
import bo.ProdutoBO;

import to.CategoriaTO;
import to.ProdutoTO;

public class ListarProdutoBeans {
	private List<ProdutoTO> produtoTOs;
	private String nome;
	private int id_cat;
	private int id_prod;
	private boolean mostrarTabela = false;
	private List<CategoriaTO> categoria;
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
	public String deletar(){
		String vazia  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_prod");
		if(vazia!=null){
			id_prod = Integer.parseInt(vazia);
		}
		ProdutoBO produtoBO = new ProdutoBO();
		produtoBO.remove(id_prod);
		mostrarTabela = false;
		return null;
	}
	
	public String buscar(){
		ProdutoBO produtoBO = new ProdutoBO();
		produtoTOs = produtoBO.findByDs_prod(nome, id_cat);
		mostrarTabela = !produtoTOs.isEmpty() ? true : false;
		return "/produto/listar.xhtml";
	}
	
	public void ocultar(ActionEvent e){
		mostrarTabela = false;
	}
	
}
