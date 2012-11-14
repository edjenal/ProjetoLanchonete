package bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import to.CategoriaTO;

public class CategoriaBO {
	
	private String SQL_create = "insert into tb_categoria(ds_cat) values (?)";
	private String SQL_update = "update tb_categoria set ds_cat = ? where id_cat = ?";
	private String SQL_remove = "delete from tb_categoria where id_cat = ?";
	private String SQL_findByPrimayKey = "select ds_cat from tb_categoria where id_cat = ?";
	private String SQL_findAll = "select id_cat, ds_cat from tb_categoria order by ds_cat";
	
	public boolean insert(String ds_cat) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_create);
			st.setString(1, ds_cat);
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao inserir categoria");
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

	public boolean update(String ds_cat, int id_cat) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_update);
			st.setString(1, ds_cat);
			st.setInt(2, id_cat);
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao atualizar categoria");
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

	public CategoriaTO findByPrimaryKey(int id_cat) {
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findByPrimayKey);
			st.setInt(1, id_cat);
			rs = st.executeQuery();
			if (rs.next()) {
				CategoriaTO categoriaTO = new CategoriaTO();
				categoriaTO.setId_cat(id_cat);
				categoriaTO.setDs_cat(rs.getString("ds_cat"));
				return categoriaTO;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar categoria pelo id");
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
	
	public List<CategoriaTO> findAll(){
		List<CategoriaTO> resultado = new ArrayList<CategoriaTO>();
		Connection con = Conexao.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(SQL_findAll);
			while (rs.next()) {
				CategoriaTO categoriaTO = new CategoriaTO();
				categoriaTO.setId_cat(rs.getInt("id_cat"));
				categoriaTO.setDs_cat(rs.getString("ds_cat"));
				resultado.add(categoriaTO);
			}
		} catch (Exception e) {
			System.out.println("Falha ao buscar todas as categorias");
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
	
	public boolean remove(int id_cat){
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try{
			st = con.prepareStatement(SQL_remove);
			st.setInt(1, id_cat);
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

}
