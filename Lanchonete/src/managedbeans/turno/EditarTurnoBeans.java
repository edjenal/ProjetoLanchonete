package managedbeans.turno;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import to.TurnoTO;
import bo.TurnoBO;

public class EditarTurnoBeans {
	private int idTurno;
	private String dsTurno;
	private TurnoTO turnoTO;
	
	@PostConstruct
	public void init(){
		String vazia  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idTurno");
		if(vazia!=null){
			idTurno = Integer.parseInt(vazia);
			TurnoBO turnoBO = new TurnoBO();
			turnoTO = turnoBO.findByPrimaryKey(idTurno);
			dsTurno = turnoTO.getDsTurno();
		}
	}
	public int getIdTurno() {
		return idTurno;
	}
	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}
	public String getDsTurno() {
		return dsTurno;
	}
	public void setDsTurno(String dsTurno) {
		this.dsTurno = dsTurno;
	}
	public String update(){
		TurnoBO turnoBO = new TurnoBO();
		return turnoBO.update(dsTurno, idTurno) ? null : "/turno/listar.xhtml";
	}
	
}
