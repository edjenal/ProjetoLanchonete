package managedbeans.categoria;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import bo.CategoriaBO;
import to.CategoriaTO;

public class EditarCategoriaBeans {
	private int id_cat;
	private String ds_cat;
	private CategoriaTO categoriaTO;
	
	@PostConstruct
	public void init(){
		String vazia  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_cat");
		if(vazia!=null){
			id_cat = Integer.parseInt(vazia);
			CategoriaBO categoriaBO = new CategoriaBO();
			categoriaTO = categoriaBO.findByPrimaryKey(id_cat);
			ds_cat = categoriaTO.getDs_cat();
		}
	}

	public int getId_cat() {
		return id_cat;
	}

	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}

	public String getDs_cat() {
		return ds_cat;
	}

	public void setDs_cat(String ds_cat) {
		this.ds_cat = ds_cat;
	}
	
	public String update(){
		CategoriaBO categoriaBO = new CategoriaBO();
		return categoriaBO.update(ds_cat, id_cat) ? null : "/categoria/listar.xhtml";
	}
	
}
