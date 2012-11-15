package bo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VendaBO {
	private String SQL_create = "insert into tb_venda(id_cli, valor_total_venda, valor_desconto_venda, valor_debito, dt_venda, dt_pag_total)" +
			" values (?, ?, ?, ?, getdate(), ?); select scope_identity() as id;";
	public Integer insert(int id_cli, Double valor_total_venda, Double valor_desconto_venda, Double valor_debito, Date dt_pag_total) {
		Integer retorno = null;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_create);
			st.setInt(1, id_cli);
			st.setDouble(2, valor_total_venda);
			st.setDouble(3, valor_desconto_venda);
			st.setDouble(4, valor_debito);
			st.setDate(5, dt_pag_total);
			rs = st.executeQuery();
			retorno = rs.getInt("id");
		} catch (Exception e) {
			e.printStackTrace();
			retorno = null;
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
