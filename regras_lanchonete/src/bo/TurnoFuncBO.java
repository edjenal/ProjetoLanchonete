package bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import to.TurnoFuncTO;

public class TurnoFuncBO {
	private String SQL_create = "insert into tb_turno_func(id_turno, id_func) values (?, ?)";
	private String SQL_remove = "delete from tb_turno_func where id_func = ?";
	private String SQL_findByidFunc = "select t.id_turno, f.id_func, nm_func, ds_turno from tb_turno_func tf " +
				"join tb_func f on f.id_func = tf.id_func " +
				"join tb_turno t on t.id_turno = tf.id_turno " +
				"where f.id_func = ?";
	private String SQL_findAllByidTurno = "select t.id_turno, f.id_func, nm_func, ds_turno from tb_turno_func tf " +
			"join tb_func f on f.id_func = tf.id_func " +
			"join tb_turno t on t.id_turno = tf.id_turno where flAtivo_func = 'true' and t.id_turno = ?";
	
	public boolean insert(TurnoFuncTO turnoFunc) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_create);
			st.setInt(1, turnoFunc.getIdTurno());
			st.setInt(2, turnoFunc.getIdFunc());
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao inserir turno do Funcionario");
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
	
	public List<TurnoFuncTO> findByidFunc(int idFunc){
		List<TurnoFuncTO> retorno = new ArrayList<TurnoFuncTO>();
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findByidFunc);
			st.setInt(1, idFunc);
			rs = st.executeQuery();
			while (rs.next()) {
				TurnoFuncTO turnoFuncTO = new TurnoFuncTO();
				turnoFuncTO.setIdTurno(rs.getInt("id_turno"));
				turnoFuncTO.setIdFunc(rs.getInt("id_func"));
				turnoFuncTO.setNmFunc(rs.getString("nm_func"));
				turnoFuncTO.setDsTurno(rs.getString("ds_turno"));
				retorno.add(turnoFuncTO);
			} 
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar os turnos pelo funcionario");
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
	
	public List<TurnoFuncTO> findAllByidTurno(int idTurno){
		List<TurnoFuncTO> retorno = new ArrayList<TurnoFuncTO>();
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findAllByidTurno);
			st.setInt(1, idTurno);
			rs = st.executeQuery();
			while (rs.next()) {
				TurnoFuncTO turnoFuncTO = new TurnoFuncTO();
				turnoFuncTO.setIdTurno(rs.getInt("id_turno"));
				turnoFuncTO.setIdFunc(rs.getInt("id_func"));
				turnoFuncTO.setNmFunc(rs.getString("nm_func"));
				turnoFuncTO.setDsTurno(rs.getString("ds_turno"));
				retorno.add(turnoFuncTO);
			} 
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar os turnos pelo funcionario");
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
