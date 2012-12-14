package to;

public class ProdutoTO {
	
	private int id_prod;
	private int id_cat;
	private String ds_prod;
	private Double preco_prod;
	
	private String ds_cat;
	private String preco_prod_tela;
	
	private Integer qtd_venda;
	
	private String subValor;
	
	public int getId_prod() {
		return id_prod;
	}
	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
	}
	public int getId_cat() {
		return id_cat;
	}
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	public String getDs_prod() {
		return ds_prod;
	}
	public void setDs_prod(String ds_prod) {
		this.ds_prod = ds_prod;
	}
	public Double getPreco_prod() {
		return preco_prod;
	}
	public void setPreco_prod(Double preco_prod) {
		this.preco_prod = preco_prod;
	}
	public String getDs_cat() {
		return ds_cat;
	}
	public void setDs_cat(String ds_cat) {
		this.ds_cat = ds_cat;
	}
	public String getPreco_prod_tela() {
		return preco_prod_tela = getPreco_prod().toString().replaceAll("\\.", ",");
	}
	public void setPreco_prod_tela(String preco_prod_tela) {
		this.preco_prod_tela = preco_prod_tela;
	}
	public Integer getQtd_venda() {
		return qtd_venda;
	}
	public void setQtd_venda(Integer qtd_venda) {
		this.qtd_venda = qtd_venda;
	}
	public String getSubValor() {
		Double valor = getQtd_venda() * getPreco_prod();
		subValor = valor.toString().replaceAll("\\.", ",");
		return subValor;
	}
	public void setSubValor(String subValor) {
		this.subValor = subValor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_prod;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoTO other = (ProdutoTO) obj;
		if (id_prod != other.id_prod)
			return false;
		return true;
	}
	

	
}
