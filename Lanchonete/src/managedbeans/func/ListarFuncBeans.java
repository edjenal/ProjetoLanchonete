package managedbeans.func;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import to.FuncTO;
import bo.FuncBO;

public class ListarFuncBeans {
	private FuncTO func = new FuncTO();
	private List<FuncTO> funcs = new ArrayList<FuncTO>();
	
	private boolean mostrarTabela = false;
	
	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}
	
	public String buscar(){
		FuncBO funcBO = new FuncBO();
		funcs = funcBO.findByNomeOuAtivo(func);
		mostrarTabela = !funcs.isEmpty() ? true : false;
		return "/func/listar.xhtml";
	}
	
	public void ocultar(ActionEvent e){
		mostrarTabela = false;
	}
	public FuncTO getFunc() {
		return func;
	}
	public void setFunc(FuncTO func) {
		this.func = func;
	}
	public List<FuncTO> getFuncs() {
		return funcs;
	}
	public void setFuncs(List<FuncTO> funcs) {
		this.funcs = funcs;
	}
	
}
