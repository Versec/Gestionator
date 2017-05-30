package es.ucm.fdi.negocio;

import java.util.List;

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

	public String toString(){
		String s="";
		List<TPedido> a = this.daoPedido.leerTodos();
		for(int i=0;i<a.size();i++)
			s=s+a.get(i).toStringPedido()+System.getProperty("line.separator");
		
		return s;
	}
	
	
	
	
}

