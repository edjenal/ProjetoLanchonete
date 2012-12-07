package managedbeans.area;

import bo.AreaBO;

public class NovaAreaBeans {
	private String dsArea;
	
	public String getDsArea() {
		return dsArea;
	}
	public void setDsArea(String dsArea) {
		this.dsArea = dsArea;
	}

	public String inserir(){
		AreaBO areaBO = new AreaBO();
		return areaBO.insert(dsArea) ? null : "/area/listar.xhtml";
	}
}
