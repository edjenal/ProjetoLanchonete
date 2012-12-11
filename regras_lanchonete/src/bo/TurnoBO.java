package bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import to.TurnoTO;

public class TurnoBO {
	
	private String SQL_create = "insert into tb_turno(ds_turno) values (?)";
	private String SQL_update = "update tb_turno set ds_turno = ? where id_turno = ?";
	private String SQL_remove = "delete from tb_turno where id_turno = ?";
	private String SQL_findByPrimayKey = "select ds_turno from tb_turno where id_turno = ?";
	private String SQL_findAll = "select id_turno, ds_turno from tb_turno order by ds_turno";
	
	public boolean insert(String dsTurno) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_create);
			st.setString(1, dsTurno);
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao inserir turno");
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

	public boolean update(String dsTurno, int idTurno) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_update);
			st.setString(1, dsTurno);
			st.setInt(2, idTurno);
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao atualizar turno");
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

	public TurnoTO findByPrimaryKey(int idTurno) {
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findByPrimayKey);
			st.setInt(1, idTurno);
			rs = st.executeQuery();
			if (rs.next()) {
				TurnoTO turnoTO = new TurnoTO();
				turnoTO.setIdTurno(idTurno);
				turnoTO.setDsTurno(rs.getString("ds_turno"));
				return turnoTO;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar turno pelo id");
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
	
	public List<TurnoTO> findAll(){
		List<TurnoTO> resultado = new ArrayList<TurnoTO>();
		Connection con = Conexao.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(SQL_findAll);
			while (rs.next()) {
				TurnoTO turnoTO = new TurnoTO();
				turnoTO.setIdTurno(rs.getInt("id_turno"));
				turnoTO.setDsTurno(rs.getString("ds_turno"));
				resultado.add(turnoTO);
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
	
	public boolean remove(int idTurno){
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try{
			st = con.prepareStatement(SQL_remove);
			st.setInt(1, idTurno);
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
