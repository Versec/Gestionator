package es.ucm.fdi.datos;

import java.util.Collection;

public interface DAOEmpleado {
	
	public Integer crear(TEmpleado empleado, String id);
	public TEmpleado leer(String id);
	public Collection<TEmpleado> leerTodos();
	public Integer actualizar(TEmpleado empleado);
	public Integer eliminar (Integer id);
}
