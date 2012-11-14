package bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import to.ProdutoTO;

public class ProdutoBO {
	
	private String SQL_create = "insert into tb_produto(id_cat, ds_prod, preco_prod) values (?, ?, ?)";
	private String SQL_update = "update tb_produto set id_cat = ?, ds_prod = ?, preco_prod = ? where id_prod = ?";
	private String SQL_remove = "delete from tb_produto where id_prod = ?";
	private String SQL_findByPrimayKey = "select prod.id_cat, cat.ds_cat, ds_prod, preco_prod from tb_produto prod inner join tb_categoria cat on cat.id_cat = prod.id_cat where id_prod = ?";
	private String SQL_findAll = "select id_prod, prod.id_cat, cat.ds_cat, ds_prod, preco_prod from tb_produto prod inner join tb_categoria cat on cat.id_cat = prod.id_cat";
	private String SQL_findByDs_prod = "select id_prod, prod.id_cat, cat.ds_cat, ds_prod, preco_prod from tb_produto prod inner join tb_categoria cat on cat.id_cat = prod.id_cat where ds_prod LIKE ";
	
	public boolean insert(int id_cat, String ds_prod, Double preco_prod) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_create);
			st.setInt(1, id_cat);
			st.setString(2, ds_prod);
			st.setDouble(3, preco_prod);
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao inserir produto");
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
	
	public boolean update(int id_cat, String ds_prod, Double preco_prod, int id_prod) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_update);
			st.setInt(1, id_cat);
			st.setString(2, ds_prod);
			st.setDouble(3, preco_prod);
			st.setInt(4, id_prod);
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao atualizar produto");
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
	
	public ProdutoTO findByPrimaryKey(int id_prod) {
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findByPrimayKey);
			st.setInt(1, id_prod);
			rs = st.executeQuery();
			if (rs.next()) {
				ProdutoTO produtoTO = new ProdutoTO();
				produtoTO.setId_prod(id_prod);
				produtoTO.setId_cat(rs.getInt("id_cat"));
				produtoTO.setDs_cat(rs.getString("ds_cat"));
				produtoTO.setDs_prod(rs.getString("ds_prod"));
				produtoTO.setPreco_prod(rs.getDouble("preco_prod"));
				return produtoTO;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar produto pelo id");
			return null;
		} finally {
			try {
				rs.close();
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<ProdutoTO> findAll(){
		List<ProdutoTO> resultado = new ArrayList<ProdutoTO>();
		Connection con = Conexao.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(SQL_findAll);
			while (rs.next()) {
				ProdutoTO produtoTO = new ProdutoTO();
				produtoTO.setId_prod(rs.getInt("id_prod"));
				produtoTO.setId_cat(rs.getInt("id_cat"));
				produtoTO.setDs_cat(rs.getString("ds_cat"));
				produtoTO.setDs_prod(rs.getString("ds_prod"));
				produtoTO.setPreco_prod(rs.getDouble("preco_prod"));
				resultado.add(produtoTO);
			}
		} catch (Exception e) {
			System.out.println("Falha ao buscar todas os produtos");
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
	
	public boolean remove(int id_prod){
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try{
			st = con.prepareStatement(SQL_remove);
			st.setInt(1, id_prod);
			return st.execute();
		} catch (Exception e){
			e.printStackTrace();
			return false;
		} finally{
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<ProdutoTO> findByDs_prod(String ds_prod, int id_cat){
		ds_prod = "'"+ds_prod+"%'";
		String sqlPog = " and prod.id_cat = "+id_cat;
		List<ProdutoTO> resultado = new ArrayList<ProdutoTO>();
		Connection con = Conexao.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(SQL_findByDs_prod+ds_prod+sqlPog);
			while (rs.next()) {
				ProdutoTO produtoTO = new ProdutoTO();
				produtoTO.setId_prod(rs.getInt("id_prod"));
				produtoTO.setId_cat(rs.getInt("id_cat"));
				produtoTO.setDs_cat(rs.getString("ds_cat"));
				produtoTO.setDs_prod(rs.getString("ds_prod"));
				produtoTO.setPreco_prod(rs.getDouble("preco_prod"));
				resultado.add(produtoTO);
			}
		} catch (Exception e) {
			System.out.println("Falha ao buscar os produtos pela descricao");
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
