package to;

public class VendaProdutoTO {
	
	private int id_prod;
	private int id_venda;
	private int qtd_prod;
	private Double preco_prod_vendido;
	
	private String ds_cat;
	private String ds_prod;
	private String preco_prod_vendidoTela;
	
	private String valor_por_produto;
	
	
	public int getId_prod() {
		return id_prod;
	}
	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
	}
	public int getId_venda() {
		return id_venda;
	}
	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}
	public int getQtd_prod() {
		return qtd_prod;
	}
	public void setQtd_prod(int qtd_prod) {
		this.qtd_prod = qtd_prod;
	}
	public Double getPreco_prod_vendido() {
		return preco_prod_vendido;
	}
	public void setPreco_prod_vendido(Double preco_prod_vendido) {
		this.preco_prod_vendido = preco_prod_vendido;
	}
	public String getDs_cat() {
		return ds_cat;
	}
	public void setDs_cat(String ds_cat) {
		this.ds_cat = ds_cat;
	}
	public String getDs_prod() {
		return ds_prod;
	}
	public void setDs_prod(String ds_prod) {
		this.ds_prod = ds_prod;
	}
	public String getPreco_prod_vendidoTela() {
		return preco_prod_vendidoTela = getPreco_prod_vendido().toString().replaceAll("\\.", ",");
	}
	public void setPreco_prod_vendidoTela(String preco_prod_vendidoTela) {
		this.preco_prod_vendidoTela = preco_prod_vendidoTela;
	}
	public String getValor_por_produto() {
		Double resultado = getPreco_prod_vendido() * getQtd_prod();
		return valor_por_produto = resultado.toString().toString().replaceAll("\\.", ",");
	}
	public void setValor_por_produto(String valor_por_produto) {
		this.valor_por_produto = valor_por_produto;
	}
	
}
