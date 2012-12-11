package managedbeans.mesa;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import to.AreaTO;
import to.MesaTO;
import bo.AreaBO;
import bo.MesaBO;

public class EditarMesaBeans {
	private MesaTO mesa = new MesaTO();
	
	private List<AreaTO> areas = new ArrayList();
	
	@PostConstruct
	public void init(){
		String vazia  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idMesa");
		if(vazia!=null){
			MesaBO mesaBO = new MesaBO();
			mesa = mesaBO.findByPrimaryKey(Integer.parseInt(vazia));
		}
	}
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
	public String update(){
		MesaBO mesaBO = new MesaBO();
		return mesaBO.update(mesa) ? null : "/mesa/listar.xhtml";
	}
}
