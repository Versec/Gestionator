package es.ucm.fdi.negocio;

import java.util.List;

import es.ucm.fdi.integracion.DAOSucursal;
import es.ucm.fdi.integracion.TPedido;
import es.ucm.fdi.integracion.TSucursal;

public class BusinessSucursal {

	
	
	private DAOSucursal daoSucursal;
	
	
	public BusinessSucursal(DAOSucursal daoSucursal){
		this.daoSucursal=daoSucursal;
	}
	
	public void eliminar(String id) {
		this.daoSucursal.eliminar(id);
		
	}
	
	public void Añadir(TSucursal ped,String id) {
		this.daoSucursal.add(ped, id);
		
	}
	
	public TSucursal leer(String id) {
		return this.daoSucursal.leer(id);
	}
	
	public String toString(){
		String s="";
		List<TSucursal> a = this.daoSucursal.leerTodos();
		for(int i=0;i<a.size();i++)
			s=s+a.get(i).toStringSucursal();
		
		return s;
	}
	
}
