package es.ucm.fdi.integracion;

import java.util.List;

public interface DAO<T> {

	public void add(T dato, String cod);
	public void eliminar(int ID);
	public void actualizar(T datoActualizado);
	public T leer(String Id);
	public List<T> leerTodos();
	
}
