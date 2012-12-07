package managedbeans.area;

import java.util.List;

import javax.faces.context.FacesContext;

import to.AreaTO;
import bo.AreaBO;
import bo.CategoriaBO;

public class ListarAreaBeans {
	
	private List<AreaTO> areaTO;
	private int idArea;
	
	
	public List<AreaTO> getAreaTO() {
		AreaBO areaBO = new AreaBO();
		return areaTO = areaBO.findAll();
	}
	public void setAreaTO(List<AreaTO> areaTO) {
		this.areaTO = areaTO;
	}
	public int getIdArea() {
		return idArea;
	}
	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}
	public String deletar(){
		idArea = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idArea"));
		AreaBO areaBO = new AreaBO();
		return areaBO.remove(idArea) ? null : "/area/listar.xhtml";
	}
	
}
