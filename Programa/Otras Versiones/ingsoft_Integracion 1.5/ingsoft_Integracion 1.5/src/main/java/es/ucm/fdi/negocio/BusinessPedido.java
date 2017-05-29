package es.ucm.fdi.negocio;

import es.ucm.fdi.integracion.DAOPedido;
import es.ucm.fdi.integracion.TPedido;

public class BusinessPedido {

	private DAOPedido daoPedido;
	
	
	public BusinessPedido(DAOPedido daoPedido){
		this.daoPedido=daoPedido;
	}
	
	public void updatePControl(TPedido pedidoActualizado){
		this.daoPedido.actualizar(pedidoActualizado);
	}
	public void eliminar(String id) {
		this.daoPedido.eliminar(id);
		
	}
	
	public void Añadir(TPedido ped,String id) {
		this.daoPedido.add(ped, id);
		
	}
	
	public TPedido leer(String id) {
		return this.daoPedido.leer(id);
	}
}
