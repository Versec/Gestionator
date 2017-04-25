package es.ucm.fdi.datos;

import java.util.Collection;

public class DAOPedidoImp implements DAOPedido{
	
	private BDMemoria<TPedido> BDPedidos = new BDMemoria<TPedido>();

	public Integer crear(TPedido pedido, String id) {
		
		this.BDPedidos.insert(pedido, id);
		return null;
	}

	public TPedido leer(String id) {
		
		return this.BDPedidos.find(id);
	}

	public Collection<TPedido> leerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer actualizar(TPedido pedido) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer eliminar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
