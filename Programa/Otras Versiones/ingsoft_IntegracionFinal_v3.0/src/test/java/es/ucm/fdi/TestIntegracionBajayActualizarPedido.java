package es.ucm.fdi;

import junit.framework.TestCase;
import es.ucm.fdi.aplicationservice.GestionPedidos;
import es.ucm.fdi.datos.BDMemoria;
import es.ucm.fdi.integracion.DAOPedido;
import es.ucm.fdi.integracion.DAOSucursal;
import es.ucm.fdi.integracion.EstadoActual;
import es.ucm.fdi.integracion.Localizacion;
import es.ucm.fdi.integracion.MetPago;
import es.ucm.fdi.integracion.TCliente;
import es.ucm.fdi.integracion.TPControl;
import es.ucm.fdi.integracion.TPedido;
import es.ucm.fdi.integracion.TSucursal;
import es.ucm.fdi.integracion.TipoEnvio;
import es.ucm.fdi.negocio.BusinessPedido;
import es.ucm.fdi.negocio.BusinessSucursal;

public class TestIntegracionBajayActualizarPedido extends TestCase{
	//Pruebas de integracion de los casos de uso BajaPedido y ActualizacionPedido
	
	public void testIntegracion(){
		BDMemoria<TSucursal> sucursales=new BDMemoria<TSucursal>();
		BDMemoria<TPedido> bdCadenas=new BDMemoria<TPedido>();
		
		DAOPedido daoPedido = new DAOPedido(bdCadenas);
		DAOSucursal daoSucursal = new DAOSucursal(sucursales);
		insertTwoElements(bdCadenas);
		
		BusinessPedido BOPedido = new BusinessPedido(daoPedido);
		BusinessSucursal BOSucursal = new BusinessSucursal(daoSucursal);
		GestionPedidos gestion = new GestionPedidos(BOPedido, BOSucursal, daoPedido);
		gestion.eliminar("0p");
		gestion.eliminar("1p");
		assertTrue("La BD debía estar vacía y tiene elementos.\n", bdCadenas.getIds().isEmpty());
		insertTwoElements(bdCadenas);
		gestion.eliminar("0p");
		boolean ok = gestion.ActualizarPedido("1p", new TPControl("En calle pepe nº2",Localizacion.SUCURSAL_INTERMEDIA,EstadoActual.REPARTO));
		
		//Compruebo el valor booleano de la funcion.
		assertTrue("No se ha encontrado el elemento y si esta "+bdCadenas,ok);
		
		TPControl control=daoPedido.leer("1p").getPControl();
		//Compruebo si se ha modificado el estado.
		assertTrue("La dao no ha actualizado el dato \n"+bdCadenas,
				control.getEstado().equalsIgnoreCase("En calle pepe nº2") 
				&& control.getLocaclizacionActual() == Localizacion.SUCURSAL_INTERMEDIA &&
				control.getEstadoActual()==EstadoActual.REPARTO); 
	}
	
	
	private void insertTwoElements(BDMemoria<TPedido>bdCadenas){
		int i=0;
		while(i<2){
			TPedido ped= new TPedido(new TCliente("Paco ","51448787T",65517),12,false,"Jose",  i +"p",MetPago.TRANSFERENCIA,
					new TSucursal("123","Envios Pedro","Alicante nº 23",2734),new TSucursal("125","Envios Jaime","Madrid nº 25",2834),
					TipoEnvio.NORMAL,new TPControl("En calle lugo nº 1: llega hoy ",Localizacion.SUCURSAL_INTERMEDIA,EstadoActual.NOENVIADO),60);
			
			bdCadenas.insert(ped,ped.getId());
			i++;
			}
	}
	
}
