package es.ucm.fdi.aplicationservice;

import java.util.Scanner;

import es.ucm.fdi.datos.BDSucusales;
import es.ucm.fdi.datos.MetodoDePago;
import es.ucm.fdi.integracion.TSucursal;

public class GestionPedidos {
 
	private String emisor;
	private int repartidor;
	private boolean pagado;
	private String receptor;
	private int codigo;//Es el ID.
	private MetodoDePago metPago;
	private String dirSucursalEnvio; //direcciones para poder identificar las sucursales
	private String dirSucursalLlegada;
	private TSucursal SucursalSalida;
	private TSucursal SucursalLlegada; 
	
	
	public void obtencionDeDatos( )
	{
		Scanner sc = new Scanner(System.in);
		this.emisor = sc.nextLine();
		this.receptor = sc.nextLine();
		int MPago = sc.nextInt();
		switch(MPago)
		{
			case 1: 
				metPago = es.ucm.fdi.datos.MetodoDePago.Efectivo;
			case 2:
				metPago = es.ucm.fdi.datos.MetodoDePago.Contrarembolso;
			case 3:
				metPago = es.ucm.fdi.datos.MetodoDePago.Transferencia;
		}
		this.dirSucursalEnvio = sc.nextLine();
		this.dirSucursalLlegada = sc.nextLine();
		
		sc.close();
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
		 if (!this.dirSucursalEnvio.equals(this.dirSucursalEnvio.toString()))
		 {
			 datosCorrectos = false;
		 }if (!this.dirSucursalLlegada.equals(this.dirSucursalLlegada.toString()))
		 {
			 datosCorrectos = false;
		 }
			 
	    return datosCorrectos;	
	  }
	 public void buscarSucursal()
	 {
		 BDSucusales<TSucursal> tablaSucursales = new es.ucm.fdi.datos.BDSucusales<TSucursal>(); 
		 //es.ucm.fdi.integracion.TSucursal sucursal = new es.ucm.fdi.integracion.TSucursal(Id, nombre, direccion, codPostal);
		 
		 this.SucursalLlegada = tablaSucursales.find(this.dirSucursalLlegada);
		 this.SucursalSalida = tablaSucursales.find(this.dirSucursalEnvio);
	 }
	 public int CalculoDeTarifas()
	 {
		 int precio;
		 
		// precio = 6 + urgencia + peso
		 
		return codigo;
		 
		 
	 }
	 
	 public void crearPedido()
	 {
		 es.ucm.fdi.integracion.DAOPedido pedido = new es.ucm.fdi.integracion.DAOPedido();
		 
	 }
	 
}
