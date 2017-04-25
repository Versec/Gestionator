package es.ucm.fdi.datos;

import java.util.Collection;

public interface DAOPedido {
	
	public Integer crear(TPedido pedido, String id);
	public TPedido leer(String id);
	public Collection<TPedido> leerTodos();
	public Integer actualizar(TPedido pedido);
	public Integer eliminar (Integer id);

}
