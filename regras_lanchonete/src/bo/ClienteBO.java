package bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import to.ClienteTO;

public class ClienteBO {

	private String SQL_create = "insert into tb_cliente(nm_cliente, tel_cli, cpf_cli) values (?, ?, ?)";
	private String SQL_update = "update tb_cliente set nm_cliente = ?, tel_cli = ?, cpf_cli = ? where id_cli = ?";
	private String SQL_remove = "delete from tb_cliente where id_cli = ?";
	private String SQL_findByPrimayKey = "select nm_cliente, tel_cli, cpf_cli from tb_cliente where id_cli = ?";
	private String SQL_findAll = "select id_cli, nm_cliente, tel_cli, cpf_cli from tb_cliente";
	private String SQL_findByNm_cli = "select id_cli, nm_cliente, tel_cli, cpf_cli from tb_cliente where nm_cliente LIKE ";

	public boolean insert(String nm_cli, String tel_cli, String cpf_cli) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_create);
			st.setString(1, nm_cli);
			st.setString(2, tel_cli);
			st.setString(3, cpf_cli);
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao inserir cliente");
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

	public boolean update(String nm_cli, String tel_cli, String cpf_cli,
			int id_cli) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_update);
			st.setString(1, nm_cli);
			st.setString(2, tel_cli);
			st.setString(3, cpf_cli);
			st.setInt(4, id_cli);
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao atualizar cliente");
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

	public ClienteTO findByPrimaryKey(int id_cli) {
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findByPrimayKey);
			st.setInt(1, id_cli);
			rs = st.executeQuery();
			if (rs.next()) {
				ClienteTO clienteTO = new ClienteTO();
				clienteTO.setId_cli(id_cli);
				clienteTO.setNm_cli(rs.getString("nm_cliente"));
				clienteTO.setTel_cli(rs.getString("tel_cli"));
				clienteTO.setCpf_cli(rs.getString("cpf_cli"));
				return clienteTO;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar cliente pelo id");
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
	
	public List<ClienteTO> findAll(){
		List<ClienteTO> resultado = new ArrayList<ClienteTO>();
		Connection con = Conexao.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(SQL_findAll);
			while (rs.next()) {
				ClienteTO clienteTO = new ClienteTO();
				clienteTO.setId_cli(rs.getInt("id_cli"));
				clienteTO.setNm_cli(rs.getString("nm_cliente"));
				clienteTO.setTel_cli(rs.getString("tel_cli"));
				clienteTO.setCpf_cli(rs.getString("cpf_cli"));
				resultado.add(clienteTO);
			}
		} catch (Exception e) {
			System.out.println("Falha ao buscar todos os clientes");
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
	
	public boolean remove(int id_cli){
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try{
			st = con.prepareStatement(SQL_remove);
			st.setInt(1, id_cli);
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
	
	public List<ClienteTO> findByNm_cli(String nm_cliente){
		nm_cliente = "'"+nm_cliente+"%'";
		List<ClienteTO> resultado = new ArrayList<ClienteTO>();
		Connection con = Conexao.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(SQL_findByNm_cli+nm_cliente);
			while (rs.next()) {
				ClienteTO clienteTO = new ClienteTO();
				clienteTO.setId_cli(rs.getInt("id_cli"));
				clienteTO.setNm_cli(rs.getString("nm_cliente"));
				clienteTO.setTel_cli(rs.getString("tel_cli"));
				clienteTO.setCpf_cli(rs.getString("cpf_cli"));
				resultado.add(clienteTO);
			}
		} catch (Exception e) {
			System.out.println("Falha ao buscar os clientes pelo nome");
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
