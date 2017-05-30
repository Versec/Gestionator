package es.ucm.fdi.integracion;

import java.util.List;

import es.ucm.fdi.datos.BDMemoria;

public class DAOPedido implements DAO<TPedido>{

	private BDMemoria<TPedido> BDPedidos;
	
	public DAOPedido(BDMemoria<TPedido> BDPedidos){
		this.BDPedidos = BDPedidos;
	}
	
	public void add(TPedido dato, String id) {
		this.BDPedidos.insert(dato, id);
		
	}

	public void eliminar(String id) {
		this.BDPedidos.removeId(id);
		
	}

	public void actualizar(TPedido datoActualizado) {
		this.BDPedidos.insert(datoActualizado, datoActualizado.getId());
		
	}

	public TPedido leer(String id) {
		return this.BDPedidos.find(id);
	}


	
	public List<TPedido> leerTodos() {
		return this.BDPedidos.getList();
	}

}
