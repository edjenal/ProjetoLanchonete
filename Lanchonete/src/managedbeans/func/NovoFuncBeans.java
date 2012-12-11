package managedbeans.func;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import to.DepFuncTO;
import to.FuncTO;
import bo.DepFuncBO;
import bo.FuncBO;

public class NovoFuncBeans {
	private FuncTO func = new FuncTO();
	private List<DepFuncTO> deps = new ArrayList<DepFuncTO>();
	
	public FuncTO getFunc() {
		return func;
	}
	public void setFunc(FuncTO func) {
		this.func = func;
	}
	public List<DepFuncTO> getDeps() {
		DepFuncBO dep = new DepFuncBO();
		return deps = dep.findAll();
	}
	public void setDeps(List<DepFuncTO> deps) {
		this.deps = deps;
	}
	public String inserir(){
		try{
			FuncBO funcBO = new FuncBO();
			if(funcBO.findByCpf(func.getCpfFunc())==null){
				return funcBO.insert(func) ? null : "/func/listar.xhtml";
			} else {
				FacesMessage mensagem = new FacesMessage("CPF: " + func.getCpfFunc() + " já está cadastrado!");
				FacesContext.getCurrentInstance().addMessage("form", mensagem);
				return null;
			}
			
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
}
