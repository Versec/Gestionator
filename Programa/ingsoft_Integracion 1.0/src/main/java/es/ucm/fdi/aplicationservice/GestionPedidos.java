package es.ucm.fdi.aplicationservice;

import es.ucm.fdi.integracion.DAOPedido;
import es.ucm.fdi.integracion.DAOSucursal;
import es.ucm.fdi.integracion.EstadoActual;
import es.ucm.fdi.integracion.TPControl;
import es.ucm.fdi.integracion.TPedido;
import es.ucm.fdi.integracion.TSucursal;
import es.ucm.fdi.integracion.TipoEnvio;
import es.ucm.fdi.negocio.BusinessPedido;
import es.ucm.fdi.negocio.BusinessSucursal;

public class GestionPedidos {

	private BusinessPedido negocioPedido;
	private BusinessSucursal negocioSucursal;
	private DAOPedido daoPedido;
	
	
	public GestionPedidos(BusinessPedido negocioPedido, BusinessSucursal negocioSucursal, DAOPedido daoPedido){
		this.negocioPedido=negocioPedido;
		this.daoPedido=daoPedido;
		this.negocioSucursal = negocioSucursal;
	}
	
	public boolean ActualizarPedido(String Id, TPControl Pcontrol){
		boolean found=true;
		TPedido pedido=this.daoPedido.leer(Id);
		if(pedido==null)
			found=false;
		else{
		pedido.setPControl(Pcontrol);
		this.negocioPedido.updatePControl(pedido);
		}
		
		return found;
	}
	
	public Integer eliminar (String id) {
		Integer OK = -1;
		TPedido pedidoLeido = negocioPedido.leer(id);
		if(pedidoLeido != null){
			if(pedidoLeido.getPControl().getEstadoActual() == EstadoActual.NOENVIADO){
				negocioPedido.eliminar(id);
				OK = 1;
			}
			else OK = 0;
			
		}
		return OK;	
	}
	
	public TSucursal buscarSucursal(String id)
	{
		return negocioSucursal.leer(id);
		
	}
	
	public boolean AñadirPedido(TPedido pedido)
 {

	//RegistrarCliente(nombreCliente, DNICliente, telefonoCliente);
	
    	if (ValidarDatos(pedido))
    	{
    		
    		
    		ponerCodigo(pedido);
    		CalculoDeTarifas();
    		buscarSucursal(/*tablaSucursales*/);
    		crearPuntoControl();
    		//Comprobar que el pago se ha realizado correctamente(if (correcto) insetro el pedido en la base de datos)
    		crearPedido();
    	}
    	else
    	{
    		return false;
    	}
    return true;
 }
	
	 public boolean ValidarDatos(TPedido pedido) 
	 {
		 boolean datosCorrectos = true;
		 if (pedido.getEmisor().getNombre().matches("[0-9]*") || pedido.getEmisor().getDNI().matches("[0-9]*"))
		 {
	    	datosCorrectos = false;
		 }
		 if ( pedido.getReceptor().matches("[0-9]*"))
		 {
		    	datosCorrectos = false;
		 }
		 if (pedido.getMetPago() == null)
		 {
			 datosCorrectos = false;
		 }
		 if(pedido.getTipoEnvio() == null)
		 {
			 datosCorrectos = false;
		 } 
	    return datosCorrectos;	
	  }
	 
	 /**
	  * Funcion improvisada para crear un codigo, hay que cambiarla
	  * @param pedido
	  */
	 public void ponerCodigo(TPedido pedido )
	 {
		 pedido.setId(pedido.getEmisor() + pedido.getReceptor());
		 
	 }
	 public void CalculoDeTarifas(TPedido pedido)
	 {
		 int precio;
		 int precioUrgencia = 0;
		 int precioPeso = 0;
		 
		 if (pedido.getTipoEnvio() == TipoEnvio.URGENTE)//Calculo extra por serv. de urgencia 
		 {
			 precioUrgencia = 2;
		 }
		 
		 
		 if(this.pesoPaquete >=6 ) //Aumento del precio segun el peso del paquete a enviar
		 {
			 precioPeso = 2;
		 }
		 else if(this.pesoPaquete >= 15)
		 {
			 precioPeso = 4;
		 }
		 else if (this.pesoPaquete >= 40)
		 {
			 precioPeso = 8;
		 }
			 
		 
		precio = 6 + precioUrgencia + precioPeso;
		
		this.precio = precio;
	 }
}
