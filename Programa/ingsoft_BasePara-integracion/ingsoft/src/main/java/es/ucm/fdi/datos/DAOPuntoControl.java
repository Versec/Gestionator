package es.ucm.fdi.datos;

import java.util.Collection;

public interface DAOPuntoControl {
	
	
	public Integer crear(TPuntoControl puntoControl, String id);
	public TPuntoControl leer(String id);
	public Collection<TPuntoControl> leerTodos();
	public Integer actualizar(TPuntoControl puntoControl);
	public Integer eliminar (Integer id);
	
	
}
