package es.ucm.fdi.aplicationservice;

import java.util.Date;
import java.util.Calendar;

import es.ucm.fdi.integracion.DAOPedido;
import es.ucm.fdi.integracion.DAOSucursal;
import es.ucm.fdi.integracion.EstadoActual;
import es.ucm.fdi.integracion.Localizacion;
import es.ucm.fdi.integracion.MetPago;
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
			if(pedidoLeido.getPControl().getEstadoActual() != EstadoActual.REPARTO){
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
    		CalculoDeTarifas(pedido);
    		crearPuntoDeControl(pedido);
    		
    		crearPedido(pedido);
    		
    	}
    	else
    	{
    		return false;
    	}
    return true;
 }
	/**
	 * Valida los datos obtenidos.
	 * Comprueba que el emisor y receptorn no tengan numeros, que metPago y tipoDeEnvio no sean NULL y que el DNI sea correcto
	 * @param pedido
	 * @return
	 */
	 public boolean ValidarDatos(TPedido pedido) 
	 {
		 boolean datosCorrectos = true;
		 if (pedido.getEmisor().getNombre().matches("[0-9]*") || pedido.getEmisor().getDNI().matches("[0-9]*") ||pedido.getEmisor().getDNI().length() != 9 )
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
		
		 pedido.setId( pedido.getEmisor().getDNI() + "p");
		 
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
		 
		 
		 if(pedido.getPeso() >=6 ) //Aumento del precio segun el peso del paquete a enviar
		 {
			 precioPeso = 2;
		 }
		 else if(pedido.getPeso() >= 15)
		 {
			 precioPeso = 4;
		 }
		 else if (pedido.getPeso() >= 40)
		 {
			 precioPeso = 8;
		 }
			 
		 
		precio = 6 + precioUrgencia + precioPeso;
		
		pedido.setPrecio(precio);
	 }
	 
	 public void crearPuntoDeControl(TPedido pedido)
	 {
		 TPControl puntoControl = new TPControl("EnSucursalInicial" ,Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO);
		 
		 pedido.setPControl(puntoControl);
	 }
	 public void crearPedido(TPedido pedido)
	 {
		 negocioPedido.Añadir(pedido, pedido.getId());
	 }
	  public boolean realizarPagoEfectivo(TPedido pedido, String linea){
		
		 boolean pagado = false;
			   //El encargado pone "si" en la aplicaciÃ³n, si el cliente ha efectuado el pago
			 
				if(compruebaEntrada(linea)){
					pagado = chequeaRespuesta(linea); 
				}
				pedido. setPagado(pagado);
				return true;
		   }
		public boolean chequeaRespuesta(String valor) {
			boolean correcto;
			
			if (valor.equalsIgnoreCase("si")){
				 correcto = true;
			}else 
			{
				correcto = false;
			}
			return correcto;
		}
		public boolean compruebaEntrada(String valor)
		{
			return (valor.equalsIgnoreCase("si") || valor.equalsIgnoreCase("no"));
		}
public boolean realizarPagoTransferencia(TPedido pedido, String numTj, String cadTj, String cvcTj){
			
			boolean datosValidos=true;
			
			
			datosValidos= datosValidos && validarTarjeta(numTj);
			datosValidos= datosValidos && validarFechaCaducidad(cadTj);
			datosValidos= datosValidos && validarCvc(cvcTj);
				 pedido.setPagado(datosValidos);
				   
			return datosValidos;
		}
		   public boolean validarTarjeta(String numTj){
			   int i;
			   boolean valido = false;
			   if (numTj.length() == 16){
				   valido=true;
				   for (i=0; i!= 16; i++) {
					   valido = valido && (numTj.charAt(i) >='0' && numTj.charAt(i) <= '9');
				   }   return valido;
			   }
			   
			
			   
		   return valido;
		   
	   }
		   /*Comprueba la fecha de caducidad de la tarjeta*/
		   public boolean validarFechaCaducidad(String cadTj){
			  
			   String[] fecha = cadTj.split("/");
			   Calendar fechaActual = Calendar.getInstance();
			   int mesAct = fechaActual.get(Calendar.MONTH)+1;
			   int agnoAct =fechaActual.get(Calendar.YEAR)%100;
			   
			   if (fecha.length == 2){
					 
				   try{
				   		int agnoFecha  = Integer.parseInt(fecha[1]);
				   		int mesFecha =	Integer.parseInt(fecha[0]);	
				   		if ((agnoFecha > agnoAct) || (agnoFecha == agnoAct && mesFecha >= mesAct)){
				   			
				   			return true;}
				   } catch (NumberFormatException nfe) {
					   return false;
				   }
			   }
			   return false;		   
		   }
		   /*Comprueba que se introduzca 3 digitos de cvc*/
		   public boolean validarCvc (String cvcTj){
			  
			   if (cvcTj.length() == 3){
				   try {
						Integer.parseInt(cvcTj);
						return true;
					} catch (NumberFormatException nfe) {
						return false;
					}
			   }
			   
			   return false;
			   
		   }
		   
	public String toStringPedido(){
		return this.negocioPedido.toString();
	}
			   
	public String toStringSucursal(){
		return this.negocioSucursal.toString();
	}
		
}
