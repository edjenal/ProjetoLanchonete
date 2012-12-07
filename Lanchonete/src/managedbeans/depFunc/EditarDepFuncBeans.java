package managedbeans.depFunc;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import to.DepFuncTO;
import bo.DepFuncBO;

public class EditarDepFuncBeans {
	
	private Integer idDepFunc;
	private String dsDepFUnc;
	private DepFuncTO depFuncTO;
	
	@PostConstruct
	public void init(){
		String vazia  = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idDepFunc");
		if(vazia!=null){
			idDepFunc = Integer.parseInt(vazia);
			DepFuncBO depFuncBO = new DepFuncBO();
			depFuncTO = depFuncBO.findByPrimaryKey(idDepFunc);
			dsDepFUnc = depFuncTO.getDsDepFUnc();
		}
	}
	
	public Integer getIdDepFunc() {
		return idDepFunc;
	}
	public void setIdDepFunc(Integer idDepFunc) {
		this.idDepFunc = idDepFunc;
	}
	public String getDsDepFUnc() {
		return dsDepFUnc;
	}
	public void setDsDepFUnc(String dsDepFUnc) {
		this.dsDepFUnc = dsDepFUnc;
	}
	
	public String update(){
		DepFuncBO depFuncBO = new DepFuncBO();
		return depFuncBO.update(dsDepFUnc, idDepFunc) ? null : "/depFunc/listar.xhtml";
	}
	
	
	
}
