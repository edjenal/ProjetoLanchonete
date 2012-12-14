package managedbeans.venda.mesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import to.CategoriaTO;
import to.FuncTO;
import to.MesaTO;
import to.ProdutoTO;
import to.VendaTO;
import bo.CategoriaBO;
import bo.FuncBO;
import bo.MesaBO;
import bo.ProdutoBO;

@ManagedBean(name="VendaBeans")
@SessionScoped
public class VendaBeans {
	private VendaTO vendaTO = new VendaTO();
	private List<MesaTO> mesasAbertas = new ArrayList<MesaTO>();
	private List<FuncTO> funcs = new ArrayList<FuncTO>();
	private List<CategoriaTO> categorias;
	private List<ProdutoTO> produtos = new ArrayList<ProdutoTO>();
	private List<ProdutoTO> produtosSelecionados = new ArrayList<ProdutoTO>();
	private String nome;
	private int id_cat;
	private int qtd = 1;
	
	//gets e sets
	public VendaTO getVendaTO() {
		return vendaTO;
	}
	public void setVendaTO(VendaTO vendaTO) {
		this.vendaTO = vendaTO;
	}

	public List<MesaTO> getMesasAbertas() {
		MesaBO mesaBO = new MesaBO();
		mesasAbertas = mesaBO.findByNotOpen();
		return mesasAbertas;
	}

	public void setMesasAbertas(List<MesaTO> mesasAbertas) {
		this.mesasAbertas = mesasAbertas;
	}
	public List<FuncTO> getFuncs() {
		return funcs;
	}
	public void setFuncs(List<FuncTO> funcs) {
		this.funcs = funcs;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId_cat() {
		return id_cat;
	}
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	public List<CategoriaTO> getCategorias() {
		CategoriaBO cat = new CategoriaBO();
		categorias = cat.findAll();
		return categorias;
	}
	public void setCategorias(List<CategoriaTO> categorias) {
		this.categorias = categorias;
	}
	public List<ProdutoTO> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<ProdutoTO> produtos) {
		this.produtos = produtos;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public List<ProdutoTO> getProdutosSelecionados() {
		return produtosSelecionados;
	}
	public void setProdutosSelecionados(List<ProdutoTO> produtosSelecionados) {
		this.produtosSelecionados = produtosSelecionados;
	}
	//fim dos gets e sets
	
	public String buscar(){
		ProdutoBO produtoBO = new ProdutoBO();
		produtos = produtoBO.findByDs_prod(nome, id_cat);
		return "/venda/mesa/venda.xhtml";
	}
	
	public void addProduto(int qtd, ProdutoTO produtoTO){
		qtd = qtd == 0 ? 1 : qtd;
		if(!produtosSelecionados.contains(produtoTO)){
			produtoTO.setQtd_venda(qtd);
			produtosSelecionados.add(produtoTO);
		}else{
			for(ProdutoTO prod : produtosSelecionados){
				if(prod.getId_prod() == produtoTO.getId_prod() ){
					int qnt_venda = prod.getQtd_venda() + qtd;
					prod.setQtd_venda(qnt_venda);
					break;
				}
			}
		}
	}
	
	public String removerProduto(){
		//pegar objeto da sessão
		Map<String, Object> contexto = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		produtosSelecionados.remove((ProdutoTO) contexto.get("rs"));
		return "/venda/mesa/venda.xhtml";
	}
	
	public String salva(){
		//vendaTO
		//produtosSelecionados
		System.out.println("Aquii");
		return null;
	}
	
	public void preencheGracom(int id) {
		FuncBO funcBO = new FuncBO();
		funcs = funcBO.findByArea(id);
    }  
	
}
