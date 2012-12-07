package bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import to.AreaTO;

public class AreaBO {
	
	private String SQL_create = "insert into tb_area(ds_area) values (?)";
	private String SQL_update = "update tb_area set ds_area = ? where id_area = ?";
	private String SQL_remove = "delete from tb_area where id_area = ?";
	private String SQL_findByPrimayKey = "select ds_area from tb_area where id_area = ?";
	private String SQL_findAll = "select id_area, ds_area from tb_area order by ds_area";
	
	public boolean insert(String dsArea) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_create);
			st.setString(1, dsArea);
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao inserir area");
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

	public boolean update(String dsArea, int idArea) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_update);
			st.setString(1, dsArea);
			st.setInt(2, idArea);
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao atualizar area");
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

	public AreaTO findByPrimaryKey(int idArea) {
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findByPrimayKey);
			st.setInt(1, idArea);
			rs = st.executeQuery();
			if (rs.next()) {
				AreaTO areaTO = new AreaTO();
				areaTO.setIdArea(idArea);
				areaTO.setDsArea(rs.getString("ds_area"));
				return areaTO;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar area pelo id");
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
	
	public List<AreaTO> findAll(){
		List<AreaTO> resultado = new ArrayList<AreaTO>();
		Connection con = Conexao.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(SQL_findAll);
			while (rs.next()) {
				AreaTO areaTO = new AreaTO();
				areaTO.setIdArea(rs.getInt("id_area"));
				areaTO.setDsArea(rs.getString("ds_area"));
				resultado.add(areaTO);
			}
		} catch (Exception e) {
			System.out.println("Falha ao buscar todas as areas");
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
	
	public boolean remove(int idArea){
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try{
			st = con.prepareStatement(SQL_remove);
			st.setInt(1, idArea);
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
