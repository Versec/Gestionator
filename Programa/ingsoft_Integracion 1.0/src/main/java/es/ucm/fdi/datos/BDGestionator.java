package es.ucm.fdi.datos;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.Vector;

import es.ucm.fdi.integracion.TEmpleado;
import es.ucm.fdi.integracion.TPedido;

public class BDGestionator<T extends TPedido, A extends TEmpleado> {
	
	private Hashtable<String,T> tablaP=new Hashtable<String,T>();
	private Hashtable<String,A> tablaE=new Hashtable<String,A>();
	/**
	 * 	Insertar un elemento en la tabla. Si se insertan dos elementos con mismo
	 *  identificador, se sobreescriben. 
	 * 
	 * @param nuevo el nuevo objeto
	 * @param id identificador
	 */
	public synchronized void insertP(T nuevo, String id){
		tablaP.put(id,nuevo);
	}
	
	/**
	 * Encontrar un objeto con un identificador concreto. Devuelve NULL si 
	 * no se encuentra
	 * 
	 * @param id identificador del objeto
	 * @return El objeto o null si no se encuentra.
	 */
	public synchronized T findP(String id){
		return tablaP.get(id);
	}
	
	/**
	 * Obtiene la lista de identificadores registrados en la tabla
	 * 
	 * @return la lista de identificadores.
	 */
	public synchronized Vector<String> getIdsP(){
		return new Vector<String>(tablaP.keySet());		
	}
	
	/**
	 * Se borra un objeto de la tabla cuyo identificador coincida con el suministrado
	 * @param id identificador de la tabla
	 * @return true si se borra, false e.o.c.
	 */
	public synchronized boolean removeIdP(String id){
		if (tablaP.containsKey(id)){
			tablaP.remove(id);
		 return true;
		}
		return false;
	}	
	
	/**
	 * Imprime la tabla por el flujo de salida indicado. Se puede 
	 * dirigir a un fichero o a la salida estándar.
	 *  
	 * @param os flujo donde volcar la salida
	 */
	public void printP(OutputStream os){
		PrintStream ps=new java.io.PrintStream(os);
		for (String key:tablaP.keySet()){
			ps.println(key+":"+tablaP.get(key));
		}		
	}
	
	
	public synchronized void insertE(A nuevo, String id){
		tablaE.put(id,nuevo);
	}
	
	/**
	 * Encontrar un objeto con un identificador concreto. Devuelve NULL si 
	 * no se encuentra
	 * 
	 * @param id identificador del objeto
	 * @return El objeto o null si no se encuentra.
	 */
	public synchronized A findE(String id){
		return tablaE.get(id);
	}
	
	/**
	 * Obtiene la lista de identificadores registrados en la tabla
	 * 
	 * @return la lista de identificadores.
	 */
	public synchronized Vector<String> getIdsE(){
		return new Vector<String>(tablaE.keySet());		
	}
	
	/**
	 * Se borra un objeto de la tabla cuyo identificador coincida con el suministrado
	 * @param id identificador de la tabla
	 * @return true si se borra, false e.o.c.
	 */
	public synchronized boolean removeIdE(String id){
		if (tablaE.containsKey(id)){
			tablaE.remove(id);
		 return true;
		}
		return false;
	}	
	
	/**
	 * Imprime la tabla por el flujo de salida indicado. Se puede 
	 * dirigir a un fichero o a la salida estándar.
	 *  
	 * @param os flujo donde volcar la salida
	 */
	public void printE(OutputStream os){
		PrintStream ps=new java.io.PrintStream(os);
		for (String key:tablaE.keySet()){
			ps.println(key+":"+tablaE.get(key));
		}		
	}
	
	

}
