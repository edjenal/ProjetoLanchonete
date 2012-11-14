package managedbeans.produto;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import to.CategoriaTO;
import to.ProdutoTO;
import bo.CategoriaBO;
import bo.ProdutoBO;

public class EditarProdutoBeans {
	
	private int id_prod;
	private int id_cat;
	private String ds_prod;
	private String preco_prod;
	
	private List<CategoriaTO> categoria;
	
	private ProdutoTO produtoTO;
	
	@PostConstruct
	public void init(){
		String vazia  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_prod");
		if(vazia!=null){
			id_prod = Integer.parseInt(vazia);
			ProdutoBO produtoBO = new ProdutoBO();
			produtoTO = produtoBO.findByPrimaryKey(id_prod);
			id_cat = produtoTO.getId_cat();
			ds_prod = produtoTO.getDs_prod();
			preco_prod = produtoTO.getPreco_prod_tela();
		}
	}
	
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
	
	public String update(){
		ProdutoBO produtoBO = new ProdutoBO();
		preco_prod = preco_prod.replaceAll("\\.", "").replaceAll(",", ".");
		return produtoBO.update(id_cat, ds_prod, Double.parseDouble(preco_prod), id_prod) ? null : "/produto/listar.xhtml";
	}
}
