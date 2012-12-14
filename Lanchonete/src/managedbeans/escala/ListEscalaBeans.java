package managedbeans.escala;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import to.AreaTO;
import to.TurnoTO;
import bo.AreaBO;
import bo.AreaFuncBO;
import bo.TurnoBO;
import bo.TurnoFuncBO;

@ManagedBean(name="ListEscalaBeans")
@RequestScoped 
public class ListEscalaBeans {
	private List<AreaTO> areas = new ArrayList<AreaTO>();
	private AreaFuncBO areaFunc = new AreaFuncBO();
	
	
	private List<TurnoTO> turnos = new ArrayList<TurnoTO>();
	private TurnoFuncBO turnoFunc = new TurnoFuncBO();
	
	public List<AreaTO> getAreas() {
		AreaBO area = new AreaBO();
		areas = area.findAll();
		return areas;
	}
	
	public void setAreas(List<AreaTO> areas) {
		this.areas = areas;
	}
	
	public AreaFuncBO getAreaFunc() {
		return areaFunc;
	}

	public void setAreaFunc(AreaFuncBO areaFunc) {
		this.areaFunc = areaFunc;
	}

	public List<TurnoTO> getTurnos() {
		TurnoBO turno = new TurnoBO();
		turnos = turno.findAll();
		return turnos;
	}

	public void setTurnos(List<TurnoTO> turnos) {
		this.turnos = turnos;
	}

	public TurnoFuncBO getTurnoFunc() {
		return turnoFunc;
	}

	public void setTurnoFunc(TurnoFuncBO turnoFunc) {
		this.turnoFunc = turnoFunc;
	}
	
	
}
