package managedbeans.mesa;

import java.util.List;

import bo.MesaBO;

import to.MesaTO;

public class ListarMesaBeans {
	private List<MesaTO> mesas;

	public List<MesaTO> getMesas() {
		MesaBO mesa = new MesaBO();
		return mesas = mesa.findAll();
	}

	public void setMesas(List<MesaTO> mesas) {
		this.mesas = mesas;
	}
	
}
