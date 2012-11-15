package to;

public class VendaProdutoTO {
	
	private int id_prod;
	private int id_venda;
	private int qtd_prod;
	private Double preco_prod_vendido;
	
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
	
	
}
