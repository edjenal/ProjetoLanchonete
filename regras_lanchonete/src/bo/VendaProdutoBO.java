package bo;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class VendaProdutoBO {
	private String SQL_create = "insert into tb_venda_produto(id_prod, id_venda, qtd_prod, preco_prod_vendido) values (?, ?, ?, ?)";
	public boolean insert(int id_prod, int id_venda, int qtd_prod, Double preco_prod_vendido) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_create);
			st.setInt(1, id_prod);
			st.setInt(2, id_venda);
			st.setInt(3, qtd_prod);
			st.setDouble(4, preco_prod_vendido);
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao inserir produto na tabela tb_venda_produto");
		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}
}
