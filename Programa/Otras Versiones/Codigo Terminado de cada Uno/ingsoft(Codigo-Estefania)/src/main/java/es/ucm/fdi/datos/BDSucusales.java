package es.ucm.fdi.datos;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.Vector;


/**
 * Esta clase representa una BD con una única tabla con clave primaria. 
 * Se puede reescribir para almacenar en varias tablas. Se parametriza
 * con un tipo concreto. No se guarda en disco. Asume que cada objeto
 * sobreescribe el método toString
 *  
 * @author escalope, @jjgomezs
 *
 * @param <T> El parámetro que se quiere usar para la tabla. Se pueden poner
 * tantos como se quiera
 */
public class BDSucusales<T>{
	private Hashtable<String,T> tabla=new Hashtable<String,T>();
	
	/**
	 * 	Insertar un elemento en la tabla. Si se insertan dos elementos con mismo
	 *  identificador, se sobreescriben. 
	 * 
	 * @param nuevo el nuevo objeto
	 * @param id identificador
	 */
	public synchronized void insert(T nuevo, String id){
		tabla.put(id,nuevo);
	}
	
	/**
	 * Encontrar un objeto con un identificador concreto. Devuelve NULL si 
	 * no se encuentra
	 * 
	 * @param id identificador del objeto
	 * @return El objeto o null si no se encuentra.
	 */
	public synchronized T find(String id){
		return tabla.get(id);
	}
	
	/**
	 * Obtiene la lista de identificadores registrados en la tabla
	 * 
	 * @return la lista de identificadores.
	 */
	public synchronized Vector<String> getIds(){
		return new Vector<String>(tabla.keySet());		
	}
	
	/**
	 * Se borra un objeto de la tabla cuyo identificador coincida con el suministrado
	 * @param id identificador de la tabla
	 * @return true si se borra, false e.o.c.
	 */
	public synchronized boolean removeId(String id){
		if (tabla.containsKey(id)){
			tabla.remove(id);
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
	public void print(OutputStream os){
		PrintStream ps=new java.io.PrintStream(os);
		for (String key:tabla.keySet()){
			ps.println(key+":"+tabla.get(key));
		}		
	}
	/**
	 * Convierte la BD a una cadena
	 */
	public String toString(){
		java.io.ByteArrayOutputStream baos=new java.io.ByteArrayOutputStream();
		print(baos);
		return baos.toString();
	}

}

