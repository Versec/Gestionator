/*package es.ucm.fdi;

import es.ucm.fdi.aplicationservice.GestionPedidos;
import es.ucm.fdi.datos.BDMemoria;
import es.ucm.fdi.integracion.DAOPedido;
import es.ucm.fdi.integracion.EstadoActual;
import es.ucm.fdi.integracion.Localizacion;
import es.ucm.fdi.integracion.MetPago;
import es.ucm.fdi.integracion.TPControl;
import es.ucm.fdi.integracion.TPedido;
import es.ucm.fdi.integracion.TSucursal;
import es.ucm.fdi.integracion.TipoEnvio;
import es.ucm.fdi.negocio.BusinessPedido;
import junit.framework.TestCase;

public class EjemploUsoEliminarPedido extends TestCase{
	
	 //Unidad, integracion y sistema.
	
	//Pruebas de unidad:
	
		//Pruebas de unidad DAOPedido:
			//Pruebas de unidad eliminar.
	
	public void testEliminarDAOPedido(){
		BDMemoria<TPedido> pedidos=new BDMemoria<TPedido>();
		assertTrue("La BD debÃ­a estar vacÃ­a y tiene elementos. \n", pedidos.getIds().isEmpty());
		TPedido pedido1 = new TPedido("Yo", "2", true, "Ã‰l", 1111, MetPago.REEMBOLSO, new TSucursal(123, "Yo", "Calle OcaÃ±a", 1234), new TSucursal(123, "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("No enviado", Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO));
    	pedidos.insert(pedido1, "1111");		
    	DAOPedido daoPedido1 = new DAOPedido(pedidos);
    	assertTrue("La BD debe tener sÃ³lo un elemento. \n" , pedidos.getIds().size()==1);
    	daoPedido1.eliminar("1111");
    	assertTrue("La BD debÃ­a estar vacÃ­a y tiene elementos.\n", pedidos.getIds().isEmpty());    
    
	}
			//Pruebas de unidad leer.
	public void testLeerDAOPedido(){
		BDMemoria<TPedido> pedidos=new BDMemoria<TPedido>();
		TPedido pedido1 = new TPedido("Yo", "2", true, "Ã‰l", 1111, MetPago.REEMBOLSO, new TSucursal(123, "Yo", "Calle OcaÃ±a", 1234), new TSucursal(123, "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("No enviado", Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO));
    	assertTrue("La BD debÃ­a estar vacÃ­a y tiene elementos. \n", pedidos.getIds().isEmpty());
    	pedidos.insert(pedido1, "1111");	
    	DAOPedido daoPedido1 = new DAOPedido(pedidos);
    	assertTrue("La BD debe tener sÃ³lo un elemento. \n", pedidos.getIds().size()==1);    	
    	assertTrue("La BD debÃ­a contener la cadena \"hola\" y no estÃ¡ \n", daoPedido1.leer("1111")!=null && daoPedido1.leer("1111").equals(pedido1)); 
		
	}
	
	
		//Pruebas de unidad BusinessPedido:
			//Pruebas de unidad eliminar.
	
	public void testEliminarBusinessPedido(){
		BDMemoria<TPedido> pedidos=new BDMemoria<TPedido>();
		assertTrue("La BD debÃ­a estar vacÃ­a y tiene elementos. \n", pedidos.getIds().isEmpty());
		TPedido pedido1 = new TPedido("Yo", "2", true, "Ã‰l", 1111, MetPago.REEMBOLSO, new TSucursal(123, "Yo", "Calle OcaÃ±a", 1234), new TSucursal(123, "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("No enviado", Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO));		
		pedidos.insert(pedido1, "1111");
		DAOPedido daoPedido1 = new DAOPedido(pedidos);
		BusinessPedido BOPedido = new BusinessPedido(daoPedido1);
		assertTrue("La BD debe tener sÃ³lo un elemento. \n" , pedidos.getIds().size()==1);
		BOPedido.eliminar("1111");
    	assertTrue("La BD debÃ­a estar vacÃ­a y tiene elementos.\n", pedidos.getIds().isEmpty());  
		
	}
			//Pruebas de unidad leer.
	
	public void testLeerBusinessPedido(){
		BDMemoria<TPedido> pedidos=new BDMemoria<TPedido>();
		TPedido pedido1 = new TPedido("Yo", "2", true, "Ã‰l", 1111, MetPago.REEMBOLSO, new TSucursal(123, "Yo", "Calle OcaÃ±a", 1234), new TSucursal(123, "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("No enviado", Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO));
    	assertTrue("La BD debÃ­a estar vacÃ­a y tiene elementos. \n", pedidos.getIds().isEmpty());
    	pedidos.insert(pedido1, "1111");	
    	DAOPedido daoPedido1 = new DAOPedido(pedidos);
    	BusinessPedido BOPedido = new BusinessPedido(daoPedido1);
    	assertTrue("La BD debe tener sÃ³lo un elemento. \n", pedidos.getIds().size()==1);    	
    	assertTrue("La BD debÃ­a contener la cadena \"hola\" y no estÃ¡ \n", BOPedido.leer("1111")!=null && BOPedido.leer("1111").equals(pedido1)); 
		
		
	}
	
	//Pruebas de sistema GestionPedidos(AS):
	
	public void testEliminarGestionPedidos(){
		BDMemoria<TPedido> pedidos=new BDMemoria<TPedido>();
		assertTrue("La BD debÃ­a estar vacÃ­a y tiene elementos. \n", pedidos.getIds().isEmpty());
		
		//Comprobamos que elimina correctamente un pedido no enviado.
		
		TPedido pedido1 = new TPedido("Yo", "2", true, "Ã‰l", 1111, MetPago.REEMBOLSO, new TSucursal(123, "Yo", "Calle OcaÃ±a", 1234), new TSucursal(123, "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("No enviado", Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO));		
		pedidos.insert(pedido1, "1111");
		DAOPedido daoPedido1 = new DAOPedido(pedidos);
		BusinessPedido BOPedido = new BusinessPedido(daoPedido1);
		GestionPedidos gestion = new GestionPedidos(BOPedido);
		gestion.eliminar("1111");
    	assertTrue("La BD debÃ­a estar vacÃ­a y tiene elementos.\n", pedidos.getIds().isEmpty()); 
    	
    	//Comprobamos que no elimina un pedido en reparto.
    	
    	BDMemoria<TPedido> pedidos1=new BDMemoria<TPedido>();
    	TPedido pedido2 = new TPedido("Yo", "2", true, "Ã‰l", 2222, MetPago.REEMBOLSO, new TSucursal(123, "Yo", "Calle OcaÃ±a", 1234), new TSucursal(123, "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("En reparto", Localizacion.SUCURSAL_INICIO, EstadoActual.REPARTO));		
		pedidos1.insert(pedido2, "2222");
		DAOPedido daoPedido2 = new DAOPedido(pedidos1);
		BusinessPedido BOPedido1 = new BusinessPedido(daoPedido2);
		GestionPedidos gestion2 = new GestionPedidos(BOPedido1);
		gestion2.eliminar("2222");
		assertTrue("La BD debÃ­a tener el mismo numero de elementos que antes(un elemento), ya que no se permite eliminar pedidos en reparto.\n", pedidos1.getIds().size() == 1);  
		
		//Comprobamos que no elimina un pedido entregado.
		
		BDMemoria<TPedido> pedidos2=new BDMemoria<TPedido>();
    	TPedido pedido3 = new TPedido("Yo", "2", true, "Ã‰l", 3333, MetPago.REEMBOLSO, new TSucursal(123, "Yo", "Calle OcaÃ±a", 1234), new TSucursal(123, "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Entregado", Localizacion.SUCURSAL_INICIO, EstadoActual.ENTREGADO));		
		pedidos2.insert(pedido3, "3333");
		DAOPedido daoPedido3 = new DAOPedido(pedidos2);
		BusinessPedido BOPedido2 = new BusinessPedido(daoPedido3);
		GestionPedidos gestion3 = new GestionPedidos(BOPedido2);
		gestion3.eliminar("3333");
		assertTrue("La BD debÃ­a tener el mismo numero de elementos que antes(un elemento), ya que no se permite eliminar pedidos en reparto.\n", pedidos2.getIds().size() == 1);  
	}
	
		//Pruebas de unidad interfaz:
	
	
	
	

}*/
