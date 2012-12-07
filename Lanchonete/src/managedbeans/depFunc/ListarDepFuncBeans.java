package managedbeans.depFunc;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import to.DepFuncTO;
import bo.DepFuncBO;

public class ListarDepFuncBeans {
	
	private List<DepFuncTO> departamentos;
	private int idDepFunc;
	
	public List<DepFuncTO> getDepartamentos() {
		DepFuncBO depBO = new DepFuncBO();
		return departamentos = depBO.findAll();
	}
	public void setDepartamentos(List<DepFuncTO> departamentos) {
		this.departamentos = departamentos;
	}
	public int getIdDepFunc() {
		return idDepFunc;
	}
	public void setIdDepFunc(int idDepFunc) {
		this.idDepFunc = idDepFunc;
	}
	public String deletar(){
		idDepFunc = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idDepFunc"));
		DepFuncBO depBO = new DepFuncBO();
		return depBO.remove(idDepFunc) ? null : "/depFunc/listar.xhtml";
	}
	
}
