package managedbeans.turno;

import java.util.List;

import javax.faces.context.FacesContext;

import to.AreaTO;
import to.TurnoTO;
import bo.AreaBO;
import bo.CategoriaBO;
import bo.TurnoBO;

public class ListarTurnoBeans {
	
	private List<TurnoTO> turnoTO;

	public List<TurnoTO> getTurnoTO() {
		TurnoBO turnoBO = new TurnoBO();
		return turnoTO = turnoBO.findAll();
	}

	public void setTurnoTO(List<TurnoTO> turnoTO) {
		this.turnoTO = turnoTO;
	}
	
	
}
