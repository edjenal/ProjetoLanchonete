package managedbeans.func;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import to.AreaFuncTO;
import to.DepFuncTO;
import to.FuncTO;
import to.TurnoFuncTO;
import bo.AreaFuncBO;
import bo.DepFuncBO;
import bo.FuncBO;
import bo.TurnoFuncBO;

public class EditarFuncBeans {
	
	private FuncTO func = new FuncTO();
	
	private List<DepFuncTO> deps = new ArrayList<DepFuncTO>();
	
	private List<AreaFuncTO> areasFunc = new ArrayList<AreaFuncTO>();
	
	private List<TurnoFuncTO> turnosFunc = new ArrayList<TurnoFuncTO>();
	
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
	public List<AreaFuncTO> getAreasFunc() {
		AreaFuncBO areaFuncBO = new AreaFuncBO();
		return areasFunc = areaFuncBO.findByidFunc(func.getIdFunc());
	}
	public void setAreasFunc(List<AreaFuncTO> areasFunc) {
		this.areasFunc = areasFunc;
	}
	public List<TurnoFuncTO> getTurnosFunc() {
		TurnoFuncBO turnoFuncBO = new TurnoFuncBO();
		return turnosFunc = turnoFuncBO.findByidFunc(func.getIdFunc());
	}
	public void setTurnosFunc(List<TurnoFuncTO> turnosFunc) {
		this.turnosFunc = turnosFunc;
	}
	public String update(){
		FuncBO funcBO = new FuncBO();
		return funcBO.update(func) ? null : "/func/listar.xhtml";
	}
}
