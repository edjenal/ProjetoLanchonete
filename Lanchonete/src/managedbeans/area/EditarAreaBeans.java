package managedbeans.area;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import to.AreaTO;
import bo.AreaBO;

public class EditarAreaBeans {
	private int idArea;
	private String dsArea;
	private AreaTO areaTO;
	
	@PostConstruct
	public void init(){
		String vazia  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idArea");
		if(vazia!=null){
			idArea = Integer.parseInt(vazia);
			AreaBO areaBO = new AreaBO();
			areaTO = areaBO.findByPrimaryKey(idArea);
			dsArea = areaTO.getDsArea();
		}
	}
	public int getIdArea() {
		return idArea;
	}
	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}
	public String getDsArea() {
		return dsArea;
	}
	public void setDsArea(String dsArea) {
		this.dsArea = dsArea;
	}
	public String update(){
		AreaBO areaBO = new AreaBO();
		return areaBO.update(dsArea, idArea) ? null : "/area/listar.xhtml";
	}
	
}
