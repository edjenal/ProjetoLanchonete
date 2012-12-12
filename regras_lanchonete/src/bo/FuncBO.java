package bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import to.FuncTO;

public class FuncBO {
	private String SQL_create = "insert into tb_func(id_dep_func, flAtivo_func, nm_func, cpf_func, tel_func) values (?, ?, ?, ?, ?)";
	private String SQL_update = "update tb_func set id_dep_func = ?, flAtivo_func = ?, nm_func = ?,  tel_func = ? where id_func = ?";
	private String SQL_remove = "delete from tb_func where id_func = ?";
	private String SQL_findByPrimayKey = "select id_func, d.id_dep_func, flAtivo_func, nm_func, cpf_func, tel_func, ds_dep_func " +
			"from tb_func f inner join tb_dep_func d on f.id_dep_func = d.id_dep_func where id_func = ?";
	private String SQL_findAll = "select id_func, d.id_dep_func, flAtivo_func, nm_func, cpf_func, tel_func, ds_dep_func " +
			"from tb_func f inner join tb_dep_func d on f.id_dep_func = d.id_dep_func ";
	private String SQL_findByDep = "select id_func, d.id_dep_func, flAtivo_func, nm_func, cpf_func, tel_func, ds_dep_func " +
			"from tb_func f inner join tb_dep_func d on f.id_dep_func = d.id_dep_func where d.id_dep_func = ?";
	private String SQL_findByCpf = "select id_func, d.id_dep_func, flAtivo_func, nm_func, cpf_func, tel_func, ds_dep_func " +
			"from tb_func f inner join tb_dep_func d on f.id_dep_func = d.id_dep_func where cpf_func = ?";
	
	public boolean insert(FuncTO func) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_create);
			st.setInt(1, func.getIdDepFunc());
			st.setBoolean(2, true);
			st.setString(3, func.getNmFunc());
			st.setString(4, func.getCpfFunc());
			st.setString(5, func.getTelFunc());
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao inserir funcionário");
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
	
	public boolean update(FuncTO func) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_update);
			st.setInt(1, func.getIdDepFunc());
			st.setBoolean(2, func.getFlAtivoFunc());
			st.setString(3, func.getNmFunc());
			st.setString(4, func.getTelFunc());
			st.setInt(5, func.getIdFunc());
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao atualizar funcionário");
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
	
	public FuncTO findByPrimaryKey(int idFunc) {
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findByPrimayKey);
			st.setInt(1, idFunc);
			rs = st.executeQuery();
			if (rs.next()) {
				FuncTO funcTO = new FuncTO();
				funcTO.setIdFunc(idFunc);
				funcTO.setIdDepFunc(rs.getInt("id_dep_func"));
				funcTO.setDsDepFunc(rs.getString("ds_dep_func"));
				funcTO.setFlAtivoFunc(rs.getBoolean("flativo_func"));
				funcTO.setNmFunc(rs.getString("nm_func"));
				funcTO.setCpfFunc(rs.getString("cpf_func"));
				funcTO.setTelFunc(rs.getString("tel_func"));
				funcTO.setDsDepFunc(rs.getString("ds_dep_func"));
				return funcTO;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar funcionário pelo id");
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
	
	public FuncTO findByCpf(String cpf) {
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findByCpf);
			st.setString(1, cpf);
			rs = st.executeQuery();
			if (rs.next()) {
				FuncTO funcTO = new FuncTO();
				funcTO.setIdFunc(rs.getInt("id_func"));
				funcTO.setIdDepFunc(rs.getInt("id_dep_func"));
				funcTO.setDsDepFunc(rs.getString("ds_dep_func"));
				funcTO.setFlAtivoFunc(rs.getBoolean("flativo_func"));
				funcTO.setNmFunc(rs.getString("nm_func"));
				funcTO.setCpfFunc(rs.getString("cpf_func"));
				funcTO.setTelFunc(rs.getString("tel_func"));
				funcTO.setDsDepFunc(rs.getString("ds_dep_func"));
				return funcTO;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar funcionário pelo id");
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
	
	public List<FuncTO> findAll() {
		List<FuncTO> retorno = new ArrayList<FuncTO>();
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findAll);
			rs = st.executeQuery();
			while (rs.next()) {
				FuncTO funcTO = new FuncTO();
				funcTO.setIdFunc(rs.getInt("id_func"));
				funcTO.setIdDepFunc(rs.getInt("id_dep_func"));
				funcTO.setDsDepFunc(rs.getString("ds_dep_func"));
				funcTO.setFlAtivoFunc(rs.getBoolean("flativo_func"));
				funcTO.setNmFunc(rs.getString("nm_func"));
				funcTO.setCpfFunc(rs.getString("cpf_func"));
				funcTO.setTelFunc(rs.getString("tel_func"));
				funcTO.setDsDepFunc(rs.getString("ds_dep_func"));
				retorno.add(funcTO);
			} 
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar todas as funcionários");
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
	
	public List<FuncTO> findByDep(int idArea) {
		List<FuncTO> retorno = new ArrayList<FuncTO>();
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findByDep);
			st.setInt(1, idArea);
			rs = st.executeQuery();
			while (rs.next()) {
				FuncTO funcTO = new FuncTO();
				funcTO.setIdFunc(rs.getInt("id_func"));
				funcTO.setIdDepFunc(rs.getInt("id_dep_func"));
				funcTO.setDsDepFunc(rs.getString("ds_dep_func"));
				funcTO.setFlAtivoFunc(rs.getBoolean("flativo_func"));
				funcTO.setNmFunc(rs.getString("nm_func"));
				funcTO.setCpfFunc(rs.getString("cpf_func"));
				funcTO.setTelFunc(rs.getString("tel_func"));
				funcTO.setDsDepFunc(rs.getString("ds_dep_func"));
				retorno.add(funcTO);
			} 
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar funcionario pelo departamento");
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
	
	public List<FuncTO> findByStatus(boolean ativo) {
		List<FuncTO> retorno = new ArrayList<FuncTO>();
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(SQL_findAll);
			sql.append(" where flAtivo_func = ?");
			st = con.prepareStatement(sql.toString());
			st.setBoolean(1, ativo);
			rs = st.executeQuery();
			while (rs.next()) {
				FuncTO funcTO = new FuncTO();
				funcTO.setIdFunc(rs.getInt("id_func"));
				funcTO.setIdDepFunc(rs.getInt("id_dep_func"));
				funcTO.setDsDepFunc(rs.getString("ds_dep_func"));
				funcTO.setFlAtivoFunc(rs.getBoolean("flativo_func"));
				funcTO.setNmFunc(rs.getString("nm_func"));
				funcTO.setCpfFunc(rs.getString("cpf_func"));
				funcTO.setTelFunc(rs.getString("tel_func"));
				funcTO.setDsDepFunc(rs.getString("ds_dep_func"));
				retorno.add(funcTO);
			} 
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar funcionario pelo status");
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
	
	public List<FuncTO> findByNomeOuAtivo(FuncTO func) {
		List<FuncTO> retorno = new ArrayList<FuncTO>();
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(SQL_findAll);
			sql.append(" where flAtivo_func = ?");
			if(!func.getNmFunc().equals("")){
				func.setNmFunc(func.getNmFunc()+"%");
				sql.append(" and nm_func like ?");
				st = con.prepareStatement(sql.toString());
				st.setBoolean(1, func.getFlAtivoFunc());
				st.setString(2, func.getNmFunc());
				func.setNmFunc(func.getNmFunc().replaceAll("%", ""));
			} else {
				st = con.prepareStatement(sql.toString());
				st.setBoolean(1, func.getFlAtivoFunc());
			}
			rs = st.executeQuery();
			while (rs.next()) {
				FuncTO funcTO = new FuncTO();
				funcTO.setIdFunc(rs.getInt("id_func"));
				funcTO.setIdDepFunc(rs.getInt("id_dep_func"));
				funcTO.setDsDepFunc(rs.getString("ds_dep_func"));
				funcTO.setFlAtivoFunc(rs.getBoolean("flativo_func"));
				funcTO.setNmFunc(rs.getString("nm_func"));
				funcTO.setCpfFunc(rs.getString("cpf_func"));
				funcTO.setTelFunc(rs.getString("tel_func"));
				funcTO.setDsDepFunc(rs.getString("ds_dep_func"));
				retorno.add(funcTO);
			} 
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar funcionario pelo nome e/ou status");
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
