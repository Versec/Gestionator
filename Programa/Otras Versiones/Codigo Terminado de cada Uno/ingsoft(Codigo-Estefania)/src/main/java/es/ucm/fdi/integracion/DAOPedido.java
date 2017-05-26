package es.ucm.fdi.integracion;

import java.util.List;

import es.ucm.fdi.datos.*;


public class DAOPedido implements DAO<TPedido>{

	
	private BDPedidos<TPedido> BDPedidos = new BDPedidos<TPedido>();
	
	public void add(TPedido dato, String cod) 
	{
		// TODO Auto-generated method stub
		BDPedidos.insert(dato, cod);
	}

	public void eliminar(int ID) {
		// TODO Auto-generated method stub
		
	}

	public void actualizar(TPedido datoActualizado) {
		// TODO Auto-generated method stub
		
	}

	public TPedido leer(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TPedido> leerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
