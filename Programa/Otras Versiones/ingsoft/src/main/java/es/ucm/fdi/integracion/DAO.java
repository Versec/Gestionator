package es.ucm.fdi.integracion;

import java.util.List;

public interface DAO<T> {

	public void add(T dato);
	public void eliminar(int ID);
	public void actualizar(T datoActualizado);
	public T leer(int Id);
	public List<T> leerTodos();
	
}
