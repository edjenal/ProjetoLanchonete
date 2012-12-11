package managedbeans.turno;

import bo.TurnoBO;

public class NovoTurnoBeans {
	private String dsTurno;
	
	public String getDsTurno() {
		return dsTurno;
	}

	public void setDsTurno(String dsTurno) {
		this.dsTurno = dsTurno;
	}

	public String inserir(){
		TurnoBO turnoBO = new TurnoBO();
		return turnoBO.insert(dsTurno) ? null : "/turno/listar.xhtml";
	}
}
