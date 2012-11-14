package managedbeans.categoria;

import bo.CategoriaBO;

public class NovaCategoriaBeans {
	private String ds_cat;
	
	public String getDs_cat() {
		return ds_cat;
	}
	public void setDs_cat(String ds_cat) {
		this.ds_cat = ds_cat;
	}
	public String inserir(){
		CategoriaBO categoriaBO = new CategoriaBO();
		return categoriaBO.insert(ds_cat) ? null : "/categoria/listar.xhtml";
	}
}
