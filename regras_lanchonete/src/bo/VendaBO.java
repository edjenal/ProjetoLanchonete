package bo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import to.VendaTO;

public class VendaBO {
	
	private String SQL_create = "insert into tb_venda(id_cli, id_func, id_mesa, valor_total_venda, valor_desconto_venda, valor_debito, dt_venda, dt_pag_total)" +
			" values (?, ?, ?, ?, ?, ?, getdate(), ?)";
	
	//private String SQL_id = "select scope_identity() as id";
	
	private String SQL_findTop = "select top 1 * from tb_venda order by id_venda desc";
	
	private String SQL_findByPrimaryKey = "select id_venda, ven.id_cli, id_func, id_mesa, valor_total_venda, valor_desconto_venda, valor_debito, dt_venda, dt_pag_total, nm_cliente from tb_venda ven inner join tb_cliente cli on cli.id_cli = ven.id_cli where id_venda = ?";
	
	private String SQL_update = "update tb_venda set valor_debito = ?, dt_pag_total = ? where id_venda = ?";
	
	private String SQL_findById_cli = "select id_venda, id_cli, id_func, id_mesa, valor_total_venda, valor_desconto_venda, valor_debito, dt_venda, dt_pag_total from tb_venda where id_cli = ? and valor_debito != 0.0  order by dt_venda desc";
	
	public Integer insert(VendaTO venda) {
		Integer id = null;
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			//inserir uma venda
			st = con.prepareStatement(SQL_create);
			if(venda.getId_cli()!=null){
				st.setInt(1, venda.getId_cli());
			} else {
				st.setNull(1, java.sql.Types.INTEGER);
			}
			if(venda.getId_func()!=null){
				st.setInt(2, venda.getId_func());
			} else 
				st.setNull(2, java.sql.Types.INTEGER );
			if(venda.getId_mesa()!=null){
				st.setInt(3, venda.getId_mesa());
			} else {
				st.setNull(3, java.sql.Types.INTEGER);
			}
			st.setDouble(4, venda.getValor_total_venda());
			st.setDouble(5, venda.getValor_desconto_venda());
			st.setDouble(6, venda.getValor_debito());
			st.setDate(7, venda.getDt_pag_total());
			retorno = st.execute();
			if(!retorno){
				//coletar ultimo id inserido
				s = con.createStatement();
				rs = s.executeQuery(SQL_findTop);
				if(rs.next()){
					id = rs.getInt("id_venda");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			id = null;
			System.out.println("Falha ao inserir produto na tabela tb_venda_produto");
		} finally {
			try {
				s.close();
				rs.close();
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return id;
	}
	private String SQL_filtro = "select id_venda, ven.id_cli, valor_total_venda, valor_desconto_venda, valor_debito, " +
									"dt_venda, dt_pag_total, nm_cliente from tb_venda ven " +
									"inner join tb_cliente cli on cli.id_cli = ven.id_cli " +
									"where nm_cliente like ? ";
	public List<VendaTO> findByFiltro(Date inicial, Date fim, String nm_cli, int situacao){
		switch (situacao) {
		case 2:
			SQL_filtro+="and dt_pag_total is null ";
			break;
		case 3:
			SQL_filtro+="and dt_pag_total is not null ";
		default:
			break;
		}
		boolean filtarPorData = false;
		String dt_fim = "";
		if(inicial!=null && fim!=null){
			//se nao ia ficar 00:00:00 e nao pegaria ate a ultima hora do dia escolhindo
			dt_fim = fim.toString() + " 23:59:59";
			filtarPorData = true;
			SQL_filtro += "and dt_venda >= ? and dt_venda <= ? ";
		}
		nm_cli = nm_cli!=null && !nm_cli.equals("") ? nm_cli+"%" : "%" ;
		List<VendaTO> resultado = new ArrayList<VendaTO>();
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			SQL_filtro+="order by dt_venda desc";
			st = con.prepareStatement(SQL_filtro);
			st.setString(1, nm_cli);
			if(filtarPorData){
				st.setDate(2, inicial);
				st.setString(3, dt_fim);
			}
			rs = st.executeQuery();
			while (rs.next()) {
				VendaTO vendaTO = new VendaTO();
				vendaTO.setId_venda(rs.getInt("id_venda"));
				vendaTO.setId_cli(rs.getInt("id_cli"));
				vendaTO.setValor_total_venda(rs.getDouble("valor_total_venda"));
				vendaTO.setValor_desconto_venda(rs.getDouble("valor_desconto_venda"));
				vendaTO.setValor_debito(rs.getDouble("valor_debito"));
				vendaTO.setDt_venda(rs.getDate("dt_venda"));
				vendaTO.setDt_pag_total(rs.getDate("dt_pag_total"));
				vendaTO.setNm_cliente(rs.getString("nm_cliente"));
				resultado.add(vendaTO);
			}
		} catch (Exception e) {
			System.out.println("Falha ao buscar as vendas pelo filtro");
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
	
	public VendaTO findByPrimaryKey(int id_venda){
		VendaTO vendaTO = new VendaTO();
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findByPrimaryKey);
			st.setInt(1, id_venda);
			rs = st.executeQuery();
			if (rs.next()) {
				vendaTO.setId_venda(rs.getInt("id_venda"));
				vendaTO.setId_cli(rs.getInt("id_cli"));
				vendaTO.setValor_total_venda(rs.getDouble("valor_total_venda"));
				vendaTO.setValor_desconto_venda(rs.getDouble("valor_desconto_venda"));
				vendaTO.setValor_debito(rs.getDouble("valor_debito"));
				vendaTO.setDt_venda(rs.getDate("dt_venda"));
				vendaTO.setDt_pag_total(rs.getDate("dt_pag_total"));
				vendaTO.setNm_cliente(rs.getString("nm_cliente"));
			}
		} catch (Exception e) {
			System.out.println("Falha ao buscar as vendas pelo filtro");
			vendaTO = null;
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
		return vendaTO;
	}
	
	public boolean update(Double valor_debito, Date dt_pag_total, int id_venda) {
		boolean retorno = true;
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL_update);
			st.setDouble(1, valor_debito);
			st.setDate(2, dt_pag_total);
			st.setInt(3, id_venda);
			retorno = st.execute();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
			System.out.println("Falha ao atualizar venda");
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
	
	public List<VendaTO> findByIdCli(int id_cli){
		List<VendaTO> resultado = new ArrayList<VendaTO>();
		
		Connection con = Conexao.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(SQL_findById_cli);
			st.setInt(1, id_cli);
			rs = st.executeQuery();
			while (rs.next()) {
				VendaTO vendaTO = new VendaTO();
				vendaTO.setId_venda(rs.getInt("id_venda"));
				vendaTO.setId_cli(rs.getInt("id_cli"));
				vendaTO.setValor_total_venda(rs.getDouble("valor_total_venda"));
				vendaTO.setValor_desconto_venda(rs.getDouble("valor_desconto_venda"));
				vendaTO.setValor_debito(rs.getDouble("valor_debito"));
				vendaTO.setDt_venda(rs.getDate("dt_venda"));
				vendaTO.setDt_pag_total(rs.getDate("dt_pag_total"));
				resultado.add(vendaTO);
			}
		} catch (Exception e) {
			System.out.println("Falha ao buscar as vendas pelo cliente");
			resultado = null;
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
