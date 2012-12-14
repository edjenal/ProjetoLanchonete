package bo;

import java.util.HashMap;
import java.util.Map;

import to.ProdutoTO;

public class LixoBO {
	
	Map<Integer,ProdutoTO> map = new HashMap<Integer,ProdutoTO>();
	
	
	
	public void addMap(int qnt,ProdutoTO to){
		if(!map.containsKey(qnt)){
			map.put(to.getId_prod(),to);
		}else{
			int qnt_venda = to.getQtd_venda() + qnt;
			to.setQtd_venda(qnt_venda);
			map.put(to.getId_prod(),to);
		}
	}

}
