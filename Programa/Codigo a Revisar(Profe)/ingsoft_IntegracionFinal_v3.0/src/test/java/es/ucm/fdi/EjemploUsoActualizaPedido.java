package es.ucm.fdi;

import es.ucm.fdi.aplicationservice.GestionPedidos;

import es.ucm.fdi.datos.BDMemoria;
import es.ucm.fdi.integracion.DAOPedido;
import es.ucm.fdi.integracion.EstadoActual;
import es.ucm.fdi.integracion.Localizacion;
import es.ucm.fdi.integracion.MetPago;
import es.ucm.fdi.integracion.TCliente;
import es.ucm.fdi.integracion.TPControl;
import es.ucm.fdi.integracion.TPedido;
import es.ucm.fdi.integracion.TSucursal;
import es.ucm.fdi.integracion.TipoEnvio;
import es.ucm.fdi.negocio.BusinessPedido;
import junit.framework.TestCase;

public class EjemploUsoActualizaPedido extends TestCase {

	/**
	 * Unidad
	 * Compruebo el funcionamiento de la dao y del BuisnessObject.
	 * */
	public void testUnidades(){
		int i=0;
		BDMemoria<TPedido> bdCadenas=new BDMemoria<TPedido>();
		DAOPedido daoPedido = new DAOPedido(bdCadenas);
		//2 elementos a la BD.
		insertTwoElements(bdCadenas);
		
		TPedido datoActualizado=daoPedido.leer("0p");
		//Compruebo si se han introducido los datos.
		assertTrue("No se ha encontrado el elemento y si esta "+bdCadenas,datoActualizado!=null);
		
		datoActualizado.setPControl(new TPControl("Calle joseRamons 13",
				Localizacion.SUCURSAL_INICIO,EstadoActual.REPARTO));
		daoPedido.actualizar(datoActualizado);
		TPControl control=daoPedido.leer("0p").getPControl();
		
		//Compruebo que el dato se ha actualizado atraves de la funcion daoPedido.actualizar.
		assertTrue("La dao no ha actualizado el dato \n"+bdCadenas,
				control.getEstado().equalsIgnoreCase("Calle joseRamons 13") 
				&& control.getLocaclizacionActual() == Localizacion.SUCURSAL_INICIO); 
		
		//Compruebo si se actualiza usando el buisnessPedido.
		BusinessPedido BPedido =new BusinessPedido(daoPedido);
		datoActualizado.setPControl(new TPControl("En calle lugo nº 1, llega hoy",
				Localizacion.SUCURSAL_INTERMEDIA,EstadoActual.REPARTO));
		BPedido.updatePControl(datoActualizado);
		
		//Vuelvo a comprobar si esta el dato.
		assertTrue("No se ha encontrado el elemento y si esta "+bdCadenas,daoPedido.leer("0p")!=null);
		
		//Compruebo que el dato se ha actualizado atraves de la funcion	BPedido.updatePControl(datoActualizado).
		control=daoPedido.leer("0p").getPControl();
		assertTrue("La dao no ha actualizado el dato \n"+bdCadenas,
					control.getEstado().equalsIgnoreCase("En calle lugo nº 1, llega hoy") 
					&& control.getLocaclizacionActual() == Localizacion.SUCURSAL_INTERMEDIA); 
		
	}
	
	//Integracion
	
	/**
	 * Sistema, compruebo todo el proceso.
	 * */
	
	public void testActualizar(){
		
		BDMemoria<TPedido> bdCadenas=new BDMemoria<TPedido>();
		DAOPedido daoPedido = new DAOPedido(bdCadenas);
		
		//2 elementos a la BD.
		insertTwoElements(bdCadenas);
		
		GestionPedidos GPedidos = new GestionPedidos(new BusinessPedido(daoPedido),null ,daoPedido);
		boolean ok = GPedidos.ActualizarPedido("0p", new TPControl("En calle pepe nº2",Localizacion.SUCURSAL_INTERMEDIA,EstadoActual.REPARTO));
		
		//Compruebo el valor booleano de la funcion.
		assertTrue("No se ha encontrado el elemento y si esta "+bdCadenas,ok);
		
		//Compruebo si se ha modificado el estado.
		TPControl control=daoPedido.leer("0p").getPControl();
		assertTrue("La dao no ha actualizado el dato \n"+bdCadenas,
					control.getEstado().equalsIgnoreCase("En calle pepe nº2") 
					&& control.getLocaclizacionActual() == Localizacion.SUCURSAL_INTERMEDIA); 
		
    	//Compruebo si encuentra el otro pedido.
		ok = GPedidos.ActualizarPedido("1p", new TPControl("En calle pepe nº2",Localizacion.SUCURSAL_INTERMEDIA,EstadoActual.REPARTO));
		assertTrue("No se ha encontrado el elemento y si esta "+bdCadenas,ok);
		
		//Compruebo si se ha encontrado un elemneto que no esta.
		ok = GPedidos.ActualizarPedido("3p", new TPControl("En calle pepe nº2",Localizacion.SUCURSAL_INTERMEDIA,EstadoActual.REPARTO));
		assertFalse("Se ha encontrado un elemento que no deberia haberse encontrado \n"+bdCadenas,ok);
	
	}
	
	
	private void insertTwoElements(BDMemoria<TPedido>bdCadenas){
		int i=0;
		while(i<2){
			TPedido ped= new TPedido(new TCliente("Paco ","51448787T",65517),12,false,"Jose",  i +"p",MetPago.TRANSFERENCIA,
					new TSucursal("123","Envios Pedro","Alicante nº 23",2734),new TSucursal("125","Envios Jaime","Madrid nº 25",2834),
					TipoEnvio.NORMAL,new TPControl("En calle lugo nº 1: llega hoy ",Localizacion.SUCURSAL_INTERMEDIA,EstadoActual.REPARTO),60);
			
			bdCadenas.insert(ped,ped.getId());
			i++;
			}
	}
	
}
