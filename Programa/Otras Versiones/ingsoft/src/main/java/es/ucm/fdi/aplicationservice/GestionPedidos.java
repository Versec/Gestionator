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
 
	private TCliente emisor;
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
	private es.ucm.fdi.integracion.TipoDeEnvio UrgenciaPaquete;
	private TPControl puntoControl;
	private int precio;
	private BuisnessPedido BOPedido;
	
	
	
	public GestionPedidos(BuisnessPedido BOPedido){
		this.BOPedido = BOPedido;
	}

	
	/**
	 * Inicializa los datos , seleciona los metodos de pago y urgencia
	 * 
	 * @param nombreCliente
	 * @param receptor
	 * @param MPago
	 * @param dirSEnvio
	 * @param dirSLlegada
	 * @param peso
	 * @param Urgencia
	 */
	public void obtencionDeDatos(String nombreCliente,String receptor,int MPago,
			String dirSEnvio, String dirSLlegada, int peso, int Urgencia)
	{
		
	
		this.receptor = receptor;
		
		
		switch(MPago)
		{
			case 1:
				metPago = es.ucm.fdi.integracion.MetodoDePago.Efectivo;
				break;
			case 2:
				metPago = es.ucm.fdi.integracion.MetodoDePago.Contrarembolso;
				break;
			case 3:
				metPago = es.ucm.fdi.integracion.MetodoDePago.Transferencia;
				break;
			default: 
				metPago = null;
		}
		this.dirSucursalEnvio = dirSEnvio;
		this.dirSucursalLlegada = dirSLlegada;
		this.pesoPaquete = peso;
		switch(Urgencia)
		{
			case 1:
				this.UrgenciaPaquete = TipoDeEnvio.Normal;
				break;
		
			case 2:
				this.UrgenciaPaquete = TipoDeEnvio.Urgente;
				break;
			default:
				this.UrgenciaPaquete = null;
			
		}
	}
	/**
	 *  Valida los datos introduciodos por el usuario
	 * @return retorna un booleano siendo false si algun dato introducido no es correcto
	 */
	 public boolean ValidarDatos() 
	 {
		 boolean datosCorrectos = true;
		 if (emisor.getNombre().matches("[0-9]*") || emisor.getDNI().matches("[0-9]*"))
		 {
	    	datosCorrectos = false;
		 }
		 if (!this.receptor.equals(this.receptor.toString())|| receptor.matches("[0-9]*"))
		 {
		    	datosCorrectos = false;
		 }
		 if (!this.dirSucursalEnvio.equals(this.dirSucursalEnvio.toString()))
		 {
			 datosCorrectos = false;
		 }if (!this.dirSucursalLlegada.equals(this.dirSucursalLlegada.toString()))
		 {
			 datosCorrectos = false;
		 }
		 if (this.metPago == null)
		 {
			 datosCorrectos = false;
		 }
		 if(this.UrgenciaPaquete == null)
		 {
			 datosCorrectos = false;
		 }
			 
	    return datosCorrectos;	
	  }
	 /**
	  * Mediate el nombre de las sucursales dadas por en usuario se 
	  * obtiene sus ID para la codificacion del pedido
	  */
	 public void buscarSucursal()
	 {  
		 BDMemoria<TSucursal> tablaSucursales = new BDMemoria<TSucursal>();
		 this.SucursalLlegada = tablaSucursales.find(this.dirSucursalLlegada);
		 this.SucursalEnvio = tablaSucursales.find(this.dirSucursalEnvio);
	 }
	 /**
	  *  Calcula el precio final del servicio de envio mediante la urgencia del paquete 
	  *  y su peso
	  * @return precio del servicio
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
	 /**
	  * Crea un punto de control a partir del estado y la localizacion del pedido
	  */
	 public void crearPuntoControl()
	 {
		 es.ucm.fdi.integracion.TPControl newPuntoControl = new es.ucm.fdi.integracion.TPControl(EstadoPedido.Almacen, Localizacion.SUCURSAL_INICIO);
		 
		 this.puntoControl =newPuntoControl;
	 }
	public void RegistrarCliente(String nombre, String DNI, int telf)
	 {
		this.emisor = new TCliente(nombre, DNI, telf);
	 }
	 
	 public boolean AñadirPedido(String nombreCliente, String DNICliente, int telefonoCliente,String receptor,int MPago,
				String dirSEnvio, String dirSLlegada, int peso, /*BDMemoria<TSucursal> tablaSucursales,*/ int urgencia)
	 {
 
		RegistrarCliente(nombreCliente, DNICliente, telefonoCliente);
		obtencionDeDatos(nombreCliente, receptor,MPago, dirSEnvio, dirSLlegada, peso,urgencia);
	    	
	    	if (ValidarDatos())
	    	{
	    		
	    		//llamar a la creacion del codigo; 
	    		ponerCodigo(nombreCliente, receptor, peso);
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
	 public void ponerCodigo(String cod1, String cod2, int num)
	 {
		 this.codigo = cod1 + cod2 + num; 
		 
	 }
	 
}
