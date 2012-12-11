package managedbeans.func;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import to.DepFuncTO;
import to.FuncTO;
import bo.DepFuncBO;
import bo.FuncBO;

public class EditarFuncBeans {
	
	private FuncTO func = new FuncTO();
	
	private List<DepFuncTO> deps = new ArrayList<DepFuncTO>();
	
	@PostConstruct
	public void init(){
		String vazia  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idFunc");
		if(vazia!=null){
			FuncBO funcBO = new FuncBO();
			func = funcBO.findByPrimaryKey(Integer.parseInt(vazia));
		}
	}
	
	public FuncTO getFunc() {
		return func;
	}

	public void setFunc(FuncTO func) {
		this.func = func;
	}

	public List<DepFuncTO> getDeps() {
		DepFuncBO depBO = new DepFuncBO();
		return deps = depBO.findAll();
	}

	public void setDeps(List<DepFuncTO> deps) {
		this.deps = deps;
	}
	
	public String update(){
		FuncBO funcBO = new FuncBO();
		return funcBO.update(func) ? null : "/func/listar.xhtml";
	}
}
