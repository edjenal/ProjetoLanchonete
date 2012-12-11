package managedbeans.mesa;

import java.util.List;

import to.AreaTO;
import to.MesaTO;
import bo.AreaBO;
import bo.MesaBO;

public class NovaMesaBeans {
	
	private MesaTO mesa = new MesaTO();
	private List<AreaTO> areas;
	
	public MesaTO getMesa() {
		return mesa;
	}
	public void setMesa(MesaTO mesa) {
		this.mesa = mesa;
	}
	public List<AreaTO> getAreas() {
		AreaBO area = new AreaBO();
		return areas = area.findAll();
	}
	public void setAreas(List<AreaTO> areas) {
		this.areas = areas;
	}
	public String inserir(){
		MesaBO mesaBO = new MesaBO();
		return mesaBO.insert(this.mesa)? null : "/mesa/listar.xhtml";
	}
	
}
