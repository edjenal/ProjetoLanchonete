package bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import to.MesaTO;

public class MesaBO {
	private String SQL_create = "insert into tb_mesa(id_area, ds_mesa, flAtivo_mesa, flAberta_mesa) values (?, ?, ?, ?)";
	private String SQL_update = "update tb_mesa set id_area = ?, ds_mesa = ?, flAtivo_mesa = ?,  flAberta_mesa = ? where id_mesa = ?";
	private String SQL_updateAberta = "update tb_mesa set flAberta_mesa = ? where id_mesa = ?";
	private String SQL_remove = "delete from tb_mesa where id_mesa = ?";
	private String SQL_findByPrimayKey = "select id_mesa, m.id_area, ds_mesa, flAtivo_mesa, flAberta_mesa, ds_area " +
			"from tb_mesa m inner join tb_area a on m.id_area = a.id_area where id_mesa = ?";
	private String SQL_findAll = "select id_mesa, m.id_area, ds_mesa, flAtivo_mesa, flAberta_mesa, ds_area " +
			"from tb_mesa m inner join tb_area a on m.id_area = a.id_area ";
	private String SQL_findByArea = "select id_mesa, m.id_area, ds_mesa, flAtivo_mesa, flAberta_mesa, ds_area " +
			"from tb_mesa m inner join tb_area a on m.id_area = a.id_area where m.id_area = ?";
	
	public boolean insert(MesaTO mesa) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_create);
			st.setInt(1, mesa.getIdArea());
			st.setString(2, mesa.getDsMesa());
			st.setBoolean(3, true);
			st.setBoolean(4, false);
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao inserir mesa");
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
	
	public boolean updateStatus(boolean aberta, int idMesa) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_updateAberta);
			st.setBoolean(1, aberta);
			st.setInt(2, idMesa);
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao atualizar status da mesa");
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
	
	public boolean update(MesaTO mesa) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_update);
			st.setInt(1, mesa.getIdArea());
			st.setString(2, mesa.getDsMesa());
			st.setBoolean(3, mesa.isFlAtivoMesa());
			st.setBoolean(4, mesa.isFlAbertaMesa());
			st.setInt(5, mesa.getIdMesa());
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao atualizar mesa");
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
	
	public boolean remove(int idMesa){
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try{
			st = con.prepareStatement(SQL_remove);
			st.setInt(1, idMesa);
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
	
	public MesaTO findByPrimaryKey(int idMesa) {
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findByPrimayKey);
			st.setInt(1, idMesa);
			rs = st.executeQuery();
			if (rs.next()) {
				MesaTO mesaTO = new MesaTO();
				mesaTO.setIdMesa(idMesa);
				mesaTO.setIdArea(rs.getInt("id_area"));
				mesaTO.setDsArea(rs.getString("ds_area"));
				mesaTO.setDsMesa(rs.getString("ds_mesa"));
				mesaTO.setFlAtivoMesa(rs.getBoolean("flAtivo_mesa"));
				mesaTO.setFlAbertaMesa(rs.getBoolean("flAberta_mesa"));
				return mesaTO;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar mesa pelo id");
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
	
	public List<MesaTO> findAll() {
		List<MesaTO> retorno = new ArrayList<MesaTO>();
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findAll);
			rs = st.executeQuery();
			while (rs.next()) {
				MesaTO mesaTO = new MesaTO();
				mesaTO.setIdMesa(rs.getInt("id_mesa"));
				mesaTO.setIdArea(rs.getInt("id_area"));
				mesaTO.setDsArea(rs.getString("ds_area"));
				mesaTO.setDsMesa(rs.getString("ds_mesa"));
				mesaTO.setFlAtivoMesa(rs.getBoolean("flAtivo_mesa"));
				mesaTO.setFlAbertaMesa(rs.getBoolean("flAberta_mesa"));
				retorno.add(mesaTO);
			} 
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar todas as mesas");
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
	
	public List<MesaTO> findByArea(int idArea) {
		List<MesaTO> retorno = new ArrayList<MesaTO>();
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findByArea);
			st.setInt(1, idArea);
			rs = st.executeQuery();
			while (rs.next()) {
				MesaTO mesaTO = new MesaTO();
				mesaTO.setIdMesa(rs.getInt("id_mesa"));
				mesaTO.setIdArea(rs.getInt("id_area"));
				mesaTO.setDsArea(rs.getString("ds_area"));
				mesaTO.setDsMesa(rs.getString("ds_mesa"));
				mesaTO.setFlAtivoMesa(rs.getBoolean("flAtivo_mesa"));
				mesaTO.setFlAbertaMesa(rs.getBoolean("flAberta_mesa"));
				retorno.add(mesaTO);
			} 
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar mesa pelo id");
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
