package es.ucm.fdi.datos;

import java.util.Collection;

public interface DAOSucursal{
	
	public Integer crear(TSucursal sucursal, String id);
	public TSucursal leer(Integer id);
	public Collection<TSucursal> leerTodos();
	public Integer actualizar(TSucursal sucursal);
	public Integer eliminar (Integer id);

}
