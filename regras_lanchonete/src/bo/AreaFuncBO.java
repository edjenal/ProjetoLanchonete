package bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import to.AreaFuncTO;

public class AreaFuncBO {
	
	private String SQL_create = "insert into tb_area_func(id_area, id_func) values (?, ?)";
	private String SQL_remove = "delete from tb_area_func where id_func = ?";
	private String SQL_findByidFunc = "select a.id_area, f.id_func, nm_func, ds_area from tb_area_func af " +
				"join tb_func f on f.id_func = af.id_func " +
				"join tb_area a on a.id_area = af.id_area " +
				"where f.id_func = ?";
	private String SQL_findAllByidArea = "select a.id_area, f.id_func, nm_func, ds_area from tb_area_func af " +
			"join tb_func f on f.id_func = af.id_func " +
			"join tb_area a on a.id_area = af.id_area where flAtivo_func = 'true' and a.id_area = ?";
	
	public boolean insert(AreaFuncTO areaFunc) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_create);
			st.setInt(1, areaFunc.getIdArea());
			st.setInt(2, areaFunc.getIdFunc());
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao inserir area do Funcionario");
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
	
	public boolean remove(int idFunc){
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try{
			st = con.prepareStatement(SQL_remove);
			st.setInt(1, idFunc);
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
	
	public List<AreaFuncTO> findByidFunc(int idFunc){
		List<AreaFuncTO> retorno = new ArrayList<AreaFuncTO>();
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findByidFunc);
			st.setInt(1, idFunc);
			rs = st.executeQuery();
			while (rs.next()) {
				AreaFuncTO areaFuncTO = new AreaFuncTO();
				areaFuncTO.setIdArea(rs.getInt("id_area"));
				areaFuncTO.setIdFunc(rs.getInt("id_func"));
				areaFuncTO.setNmFunc(rs.getString("nm_func"));
				areaFuncTO.setDsArea(rs.getString("ds_area"));
				retorno.add(areaFuncTO);
			} 
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar as areas pelo funcionario");
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
	
	public List<AreaFuncTO> findAllByidArea(int idArea){
		List<AreaFuncTO> retorno = new ArrayList<AreaFuncTO>();
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findAllByidArea);
			st.setInt(1, idArea);
			rs = st.executeQuery();
			while (rs.next()) {
				AreaFuncTO areaFuncTO = new AreaFuncTO();
				areaFuncTO.setIdArea(rs.getInt("id_area"));
				areaFuncTO.setIdFunc(rs.getInt("id_func"));
				areaFuncTO.setNmFunc(rs.getString("nm_func"));
				areaFuncTO.setDsArea(rs.getString("ds_area"));
				retorno.add(areaFuncTO);
			} 
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar as areas pelo funcionario");
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
}
