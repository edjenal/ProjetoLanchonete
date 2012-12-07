package bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import to.DepFuncTO;

public class DepFuncBO {
	private String SQL_create = "insert into tb_dep_func(ds_dep_func) values (?)";
	private String SQL_update = "update tb_dep_func set ds_dep_func = ? where id_dep_func = ?";
	private String SQL_remove = "delete from tb_dep_func where id_dep_func = ?";
	private String SQL_findByPrimayKey = "select ds_dep_func from tb_dep_func where id_dep_func = ?";
	private String SQL_findAll = "select id_dep_func, ds_dep_func from tb_dep_func order by ds_dep_func";
	
	public boolean insert(String dsDepFUnc) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_create);
			st.setString(1, dsDepFUnc);
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao inserir departamento");
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
	
	public boolean update(String dsDepFUnc, int idDepFunc) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_update);
			st.setString(1, dsDepFUnc);
			st.setInt(2, idDepFunc);
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao atualizar departamento");
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
	
	public DepFuncTO findByPrimaryKey(int idDepFunc) {
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findByPrimayKey);
			st.setInt(1, idDepFunc);
			rs = st.executeQuery();
			if (rs.next()) {
				DepFuncTO depFuncTO = new DepFuncTO();
				depFuncTO.setIdDepFunc(idDepFunc);
				depFuncTO.setDsDepFUnc(rs.getString("ds_dep_func"));
				return depFuncTO;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar departamento pelo id");
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
	
	public List<DepFuncTO> findAll(){
		List<DepFuncTO> resultado = new ArrayList<DepFuncTO>();
		Connection con = Conexao.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(SQL_findAll);
			while (rs.next()) {
				DepFuncTO depFuncTO = new DepFuncTO();
				depFuncTO.setIdDepFunc(rs.getInt("id_dep_func"));
				depFuncTO.setDsDepFUnc(rs.getString("ds_dep_func"));
				resultado.add(depFuncTO);
			}
		} catch (Exception e) {
			System.out.println("Falha ao buscar todas os departamentos");
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
	
	public boolean remove(int idDepFunc){
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try{
			st = con.prepareStatement(SQL_remove);
			st.setInt(1, idDepFunc);
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
