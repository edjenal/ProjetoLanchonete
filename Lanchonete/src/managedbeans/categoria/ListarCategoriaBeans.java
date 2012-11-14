package managedbeans.categoria;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import bo.CategoriaBO;
import bo.ClienteBO;

import to.CategoriaTO;

public class ListarCategoriaBeans {
	
	private List<CategoriaTO> categoriaTO;
	private int id_cat;
	
	
	public List<CategoriaTO> getCategoriaTO() {
		CategoriaBO categoriaBO = new CategoriaBO();
		return categoriaTO = categoriaBO.findAll();
	}
	public void setCategoriaTO(List<CategoriaTO> categoriaTO) {
		this.categoriaTO = categoriaTO;
	}
	public int getId_cat() {
		return id_cat;
	}
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	
	public String deletar(){
		id_cat = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_cat"));
		CategoriaBO categoriaBO = new CategoriaBO();
		return categoriaBO.remove(id_cat) ? null : "/categoria/listar.xhtml";
	}
	
}
