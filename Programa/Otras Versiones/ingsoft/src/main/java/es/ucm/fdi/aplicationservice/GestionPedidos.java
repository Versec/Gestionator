package es.ucm.fdi.aplicationservice;

import java.util.Scanner;



/*
import es.ucm.fdi.datos.BDSucusales;
import es.ucm.fdi.datos.MetodoDePago;
import es.ucm.fdi.integracion.TSucursal;
*/
import es.ucm.fdi.datos.*;
import es.ucm.fdi.integracion.*;
import es.ucm.fdi.negocio.BuisnessPedido;

public class GestionPedidos {
 
	private /*TCliente*/ String emisor;
	private int repartidor;
	private boolean pagado;
	private String receptor;
	private String codigo;//Es el ID.
	private MetodoDePago metPago;
	private String dirSucursalEnvio; //direcciones para poder identificar las sucursales
	private String dirSucursalLlegada;
	private TSucursal SucursalEnvio;
	private TSucursal SucursalLlegada; 
	private int pesoPaquete;
	private es.ucm.fdi.datos.TipoDeEnvio UrgenciaPaquete;
	private TPControl puntoControl;
	private int precio;
	private BuisnessPedido BOPedido;
	
	/**
	 * Obtiene los datos introducidos por el usuario
	 */
	
	public GestionPedidos(BuisnessPedido BOPedido){
		this.BOPedido = BOPedido;
	}

	
	
	public void obtencionDeDatos(String nombreCliente,String receptor,int MPago,
			String dirSEnvio, String dirSLlegada, int peso, int Urgencia)
	{
		/*String nombreCliente, DNICliente, direccionCliente;
		
		nombreCliente = sc.nextLine();
		DNICliente =sc.nextLine();
		direccionCliente =sc.nextLine();*/
		
		//this.emisor = new TCliente(nombreCliente, DNICliente, direccionCliente);
		this.emisor = nombreCliente;
		this.receptor = receptor;
		//int MPago = sc.nextInt();
		switch(MPago)
		{
			case 1: 
				metPago = es.ucm.fdi.datos.MetodoDePago.Efectivo;
			case 2:
				metPago = es.ucm.fdi.datos.MetodoDePago.Contrarembolso;
			case 3:
				metPago = es.ucm.fdi.datos.MetodoDePago.Transferencia;
		}
		this.dirSucursalEnvio = dirSEnvio;
		this.dirSucursalLlegada = dirSLlegada;
		this.pesoPaquete = peso;
		switch(Urgencia)
		{
		case 1:
			this.UrgenciaPaquete = TipoDeEnvio.Normal;
		
		case 2:
			this.UrgenciaPaquete = TipoDeEnvio.Urgente;
		}
	}
	/*public void AltaPedido()
	{
	 
	 
	}*/
	/**
	 *  Valida los datos introduciodos por el usuario
	 * @return 
	 */
	 public boolean ValidarDatos() 
	 {
		 boolean datosCorrectos = true;
		 if (!this.emisor.equals(this.emisor.toString()))
		 {
	    	datosCorrectos = false;
		 }
		 if (!this.receptor.equals(this.receptor.toString()))
		 {
		    	datosCorrectos = false;
		 }
		/* if (!this.dirSucursalEnvio.equals(this.dirSucursalEnvio.toString()))
		 {
			 datosCorrectos = false;
		 }if (!this.dirSucursalLlegada.equals(this.dirSucursalLlegada.toString()))
		 {
			 datosCorrectos = false;
		 }*/
			 
	    return datosCorrectos;	
	  }
	 /**
	  * Mediate el nombre de las sucursales dadas por en usuario se 
	  * obtiene sus ID para la codificacion del pedido
	  */
	 public void buscarSucursal( BDMemoria<TSucursal> tablaSucursales)
	 { 
		 this.SucursalLlegada = tablaSucursales.find(this.dirSucursalLlegada);
		 this.SucursalEnvio = tablaSucursales.find(this.dirSucursalEnvio);
	 }
	 /**
	  *  Calcula el precio final del servicio de envio mediante la urgencia del paquete 
	  *  y su peso
	  * @return preio del servicio
	  */
	 public void CalculoDeTarifas()
	 {
		 int precio;
		 int precioUrgencia = 0;
		 int precioPeso = 0;
		 
		 if (this.UrgenciaPaquete == TipoDeEnvio.Urgente)//Calculo extra por serv. de urgencia 
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
	 /**
	  * Introduce el pedido en la base de datos
	  */
	 public void crearPedido()
	 {
		 
		 es.ucm.fdi.integracion.TPedido newPedido = new es.ucm.fdi.integracion.TPedido(this.emisor, this.repartidor,
				 this.pagado, this.receptor, this.codigo, this.metPago,
				 this.SucursalEnvio,this.SucursalLlegada, this.UrgenciaPaquete,
				 this.puntoControl,this.precio);
		 
		 BOPedido.Añadir(newPedido, this.codigo);
		 
	 }
	 public void crearPuntoControl()
	 {
		 es.ucm.fdi.integracion.TPControl newPuntoControl = new es.ucm.fdi.integracion.TPControl(EstadoPedido.Almacen, Localizacion.SUCURSAL_INICIO);
		 
		 this.puntoControl =newPuntoControl;
	 }
	/* public void RegistrarCliente()
	 {
	 }*/
	 
	 public boolean AñadirPedido(String nombreCliente,String emisor,int MPago,
				String dirSEnvio, String dirSLlegada, int peso, BDMemoria<TSucursal> tablaSucursales, int urgencia)
	 {
 
		obtencionDeDatos(nombreCliente, emisor,MPago, dirSEnvio, dirSLlegada, peso,urgencia);
	    	
	    	if (ValidarDatos())
	    	{
	    		
	    		//llamar a la creacion del codigo; 
	    		ponerCodigo(nombreCliente, emisor, peso);
	    		CalculoDeTarifas();
	    		buscarSucursal(tablaSucursales);
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
	 public void ponerCodigo(String cod1, String cod2, int num)
	 {
		 this.codigo = cod1 + cod2 + num; 
		 
	 }
	 
}
