package es.ucm.fdi.datos;

import java.util.Collection;

public class DAOPuntoControlImp implements DAOPuntoControl{
	
	private BDMemoria<TPuntoControl> BDPuntosControl = new BDMemoria<TPuntoControl>();

	public Integer crear(TPuntoControl puntoControl, String id) {
		this.BDPuntosControl.insert(puntoControl, id);
		return null;
		
	}

	public TPuntoControl leer(String id) {
		
		return this.BDPuntosControl.find(id);
				
	}

	public Collection<TPuntoControl> leerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer actualizar(TPuntoControl puntoControl) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer eliminar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
