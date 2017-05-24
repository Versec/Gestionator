package es.ucm.fdi.negocio;


import es.ucm.fdi.integracion.DAOPedido;
import es.ucm.fdi.integracion.TPedido;

public class BuisnessPedido {
	private DAOPedido daoPedido;
	private TPedido tPedido;
	
	public BuisnessPedido(DAOPedido daoPedido){
		this.daoPedido = daoPedido;
	}
	
	public void Añadir(String id) {
		this.daoPedido.add(tPedido, id);
		
	}
	
	public TPedido leer(int id) {
		return this.daoPedido.leer(id);
	}
}
