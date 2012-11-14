package managedbeans.produto;

import java.util.List;

import to.CategoriaTO;

import bo.CategoriaBO;
import bo.ProdutoBO;

public class NovoProdutoBeans {
	
	private int id_prod;
	private int id_cat;
	private String ds_prod;
	private String preco_prod;
	
	private List<CategoriaTO> categoria;
	
	public int getId_prod() {
		return id_prod;
	}
	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
	}
	public int getId_cat() {
		return id_cat;
	}
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	public String getDs_prod() {
		return ds_prod;
	}
	public void setDs_prod(String ds_prod) {
		this.ds_prod = ds_prod;
	}
	public String getPreco_prod() {
		return preco_prod;
	}
	public void setPreco_prod(String preco_prod) {
		this.preco_prod = preco_prod;
	}
	public List<CategoriaTO> getCategoria() {
		CategoriaBO categoriaBO = new CategoriaBO();
		return categoria = categoriaBO.findAll();
	}
	public void setCategoria(List<CategoriaTO> categoria) {
		this.categoria = categoria;
	}
	public String inserir(){
		ProdutoBO produtoBO = new ProdutoBO();
		preco_prod = preco_prod.replaceAll("\\.", "").replaceAll(",", ".");
		return produtoBO.insert(id_cat, ds_prod, Double.parseDouble(preco_prod))? null : "/produto/listar.xhtml";
	}
	
}
