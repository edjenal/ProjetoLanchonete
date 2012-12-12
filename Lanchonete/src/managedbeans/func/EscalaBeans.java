package managedbeans.func;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import to.AreaTO;
import to.FuncTO;
import to.TurnoTO;
import bo.AreaBO;
import bo.FuncBO;
import bo.TurnoBO;

public class EscalaBeans {
	private FuncTO func = new FuncTO();
	
	private List<TurnoTO> turnos = new ArrayList<TurnoTO>();
	
	private List<AreaTO> areas = new ArrayList<AreaTO>();
	
	private List<TurnoTO> turnosEscolhidos = new ArrayList<TurnoTO>();
	
	private List<AreaTO> areasEscolhidas = new ArrayList<AreaTO>();
	
	private boolean mostrarBtSalvar = false;

	public FuncTO getFunc() {
		String vazia  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idFunc");
		if(vazia!=null){
			FuncBO funcBO = new FuncBO();
			func = funcBO.findByPrimaryKey(Integer.parseInt(vazia));
		}
		return func;
	}
	
	private void validaMostrarBT(){
		mostrarBtSalvar = turnosEscolhidos.isEmpty() ? false : true;
		mostrarBtSalvar = areasEscolhidas.isEmpty() ? false : true;
	}
	
	public void addTurno(){
		String vazia  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idTurno");
		if(vazia!=null){
			boolean add = true;
			for(TurnoTO turn : turnosEscolhidos){
				add = turn.getIdTurno() != Integer.parseInt(vazia) ? true : false;
			}
			if(add){
				TurnoTO turno = new TurnoTO();
				turno.setIdTurno(Integer.parseInt(vazia));
				turno.setDsTurno(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("dsTurno"));
				turnosEscolhidos.add(turno);
			}
			validaMostrarBT();
		}
	}
	
	public void addArea(){
		String vazia  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idArea");
		if(vazia!=null){
			boolean add = true;
			for(AreaTO are : areasEscolhidas){
				add = are.getIdArea() != Integer.parseInt(vazia) ? true : false;
			}
			if(add){
				AreaTO areaTO = new AreaTO();
				areaTO.setIdArea(Integer.parseInt(vazia));
				areaTO.setDsArea(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("dsArea"));
				areasEscolhidas.add(areaTO);
			}
			validaMostrarBT();
		}
	}
	
	public void removerTurno(){
		//pegar objeto da sessão
		Map<String, Object> contexto = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		turnosEscolhidos.remove((TurnoTO) contexto.get("rs2"));
		validaMostrarBT();
	}
	
	public void removerArea(){
		//pegar objeto da sessão
		Map<String, Object> contexto = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		areasEscolhidas.remove((AreaTO) contexto.get("rs4"));
		validaMostrarBT();
	}
	
	public String salvar(){
		System.out.println("Aqui");
		return null;
	}
	
	public String cancelar(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("EscalaBeans");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("ListarFuncBeans");
		return "/func/listar.xhtml";
	}
	
	//get e sets
	public List<AreaTO> getAreas() {
		AreaBO areaBO = new AreaBO();
		return areas = areaBO.findAll();
	}

	public void setAreas(List<AreaTO> areas) {
		this.areas = areas;
	}

	public void setFunc(FuncTO func) {
		this.func = func;
	}

	public List<TurnoTO> getTurnos() {
		TurnoBO turnoBO = new TurnoBO();
		return turnos = turnoBO.findAll();
	}

	public void setTurnos(List<TurnoTO> turnos) {
		this.turnos = turnos;
	}

	public List<TurnoTO> getTurnosEscolhidos() {
		return turnosEscolhidos;
	}

	public void setTurnosEscolhidos(List<TurnoTO> turnosEscolhidos) {
		this.turnosEscolhidos = turnosEscolhidos;
	}

	public List<AreaTO> getAreasEscolhidas() {
		return areasEscolhidas;
	}

	public void setAreasEscolhidas(List<AreaTO> areasEscolhidas) {
		this.areasEscolhidas = areasEscolhidas;
	}

	public boolean isMostrarBtSalvar() {
		return mostrarBtSalvar;
	}

	public void setMostrarBtSalvar(boolean mostrarBtSalvar) {
		this.mostrarBtSalvar = mostrarBtSalvar;
	}
	
	
	
}
