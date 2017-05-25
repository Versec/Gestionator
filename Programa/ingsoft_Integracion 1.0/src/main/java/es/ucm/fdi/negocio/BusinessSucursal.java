package es.ucm.fdi.negocio;

import es.ucm.fdi.integracion.DAOSucursal;
import es.ucm.fdi.integracion.TSucursal;

public class BusinessSucursal {

	
	
	private DAOSucursal daoSucursal;
	
	
	public BusinessSucursal(DAOSucursal daoPedido){
		this.daoSucursal=daoPedido;
	}
	
	public void updatePControl(TSucursal pedidoActualizado){
		this.daoSucursal.actualizar(pedidoActualizado);
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
	

}
