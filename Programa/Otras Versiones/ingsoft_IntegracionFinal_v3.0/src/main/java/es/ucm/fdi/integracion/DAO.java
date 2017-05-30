package es.ucm.fdi.integracion;

import java.util.List;

public interface DAO<T> {

	public void add(T dato, String id);
	public void eliminar(String ID);
	public void actualizar(T datoActualizado);
	public T leer(String Id);
	public List<T> leerTodos();
	
}
