package es.ucm.fdi.integracion;

import java.util.List;

import es.ucm.fdi.datos.*;


public class DAOPedido implements DAO<TPedido>{

	
	private BDMemoria<TPedido> BDPedidos = new BDMemoria<TPedido>();
	
	public DAOPedido(BDMemoria<TPedido> BDPedidos)
	{
		this.BDPedidos = BDPedidos;
	}
	
	public void add(TPedido dato, String cod) 
	{
		// TODO Auto-generated method stub
		this.BDPedidos.insert(dato, cod);
	}

	public void eliminar(int ID) {
		// TODO Auto-generated method stub
		
	}

	public void actualizar(TPedido datoActualizado) {
		// TODO Auto-generated method stub
		
	}

	public TPedido leer(String Id) {
		// TODO Auto-generated method stub
		return this.BDPedidos.find(Id);
	}

	public List<TPedido> leerTodos() {
		// TODO Auto-generated method stub
		return null;
	}


}
