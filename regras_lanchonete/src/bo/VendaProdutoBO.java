package bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import to.VendaProdutoTO;

public class VendaProdutoBO {
	
	private String SQL_create = "insert into tb_venda_produto(id_prod, id_venda, qtd_prod, preco_prod_vendido) values (?, ?, ?, ?)";
	
	private String SQL_findById_venda = "select ds_cat, ds_prod, preco_prod_vendido, qtd_prod from tb_venda_produto vp "
											+ "inner join tb_produto p on p.id_prod = vp.id_prod "
											+ "inner join tb_categoria c on p.id_cat = c.id_cat "
											+ "where id_venda = ? order by ds_prod";
	
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
	
	public List<VendaProdutoTO> findById_venda(int id_venda){
		List<VendaProdutoTO> resultado = new ArrayList<VendaProdutoTO>();
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findById_venda);
			st.setInt(1, id_venda);
			rs = st.executeQuery();
			while (rs.next()) {
				VendaProdutoTO vendaProdutoTO = new VendaProdutoTO();
				vendaProdutoTO.setDs_cat(rs.getString("ds_cat"));
				vendaProdutoTO.setDs_prod(rs.getString("ds_prod"));
				vendaProdutoTO.setPreco_prod_vendido(rs.getDouble("preco_prod_vendido"));
				vendaProdutoTO.setQtd_prod(rs.getInt("qtd_prod"));
				resultado.add(vendaProdutoTO);
			}
		} catch (Exception e) {
			System.out.println("Falha ao buscar os produtos pelo ID da venda");
			e.printStackTrace();
		} finally{
			try{
				rs.close();
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}
}
