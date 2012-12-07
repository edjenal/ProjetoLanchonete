package managedbeans.depFunc;

import bo.DepFuncBO;

public class NovoDepFuncBeans {
	
	private String dsDepFUnc;
	
	public String getDsDepFUnc() {
		return dsDepFUnc;
	}
	public void setDsDepFUnc(String dsDepFUnc) {
		this.dsDepFUnc = dsDepFUnc;
	}
	public String inserir(){
		DepFuncBO categoriaBO = new DepFuncBO();
		return categoriaBO.insert(dsDepFUnc) ? null : "/depFunc/listar.xhtml";
	}
}
