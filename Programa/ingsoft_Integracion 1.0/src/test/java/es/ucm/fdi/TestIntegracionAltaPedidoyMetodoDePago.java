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

public class TestIntegracionAltaPedidoyMetodoDePago extends TestCase{
	
	
	public void testEfectivo(){
	
	BDMemoria<TPedido> pedido=new BDMemoria<TPedido>();
	BDMemoria<TSucursal> sucursal = new BDMemoria<TSucursal>();
	
	
	
	DAOPedido daoPedido = new DAOPedido(pedido);
	DAOSucursal daoSucursal = new DAOSucursal(sucursal);
	BusinessPedido BP = new BusinessPedido(daoPedido);
	BusinessSucursal BS = new BusinessSucursal(daoSucursal);
	
	GestionPedidos GPedido = new GestionPedidos(BP, BS, daoPedido);
	
	TSucursal sucursal1 = new TSucursal("42", "Madrid", "Madrid", 84569); 
	TSucursal sucursal2 = new TSucursal("43", "Barcelona", "Barcelona", 84569);
	
	sucursal.insert(sucursal1, "42");
	sucursal.insert(sucursal2, "43");
	
	assertTrue("La BD de sucursales tiene dos sucursales", sucursal.getIds().size() == 2);
	
	TCliente cliente2 = new TCliente("Magdalena", "54015569Z", 968515681);
	
	TPedido pedido1 = new TPedido(cliente2, 2, false, "Random", "1111", MetPago.EFECTIVO, new TSucursal("123", "Yo", "Calle Oculta", 1234),
			new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
	TPedido pedido2 = new TPedido(cliente2, 2, false, "Random", "1112", MetPago.EFECTIVO, new TSucursal("123", "Yo", "Calle Oculta", 1234),
			new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
	TPedido pedido3 = new TPedido(cliente2, 2, false, "Random", "1113",MetPago.TRASFERNCIA, new TSucursal("123", "Yo", "Calle Oculta", 1234),
			new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
	
	
	GPedido.realizarPagoEfectivo(pedido1, "si");
	
	GPedido.AñadirPedido(pedido1);
	assertTrue("La BD debe tener al menos un elemento. \n" , pedido.getIds().size()==1);
	
	GPedido.realizarPagoEfectivo(pedido2, "si");
	GPedido.AñadirPedido(pedido2);
	assertTrue("La base de datos contiene dos elementos",pedido.getIds().size()==2);
	
	GPedido.realizarPagoEfectivo(pedido3, "si");
	GPedido.AñadirPedido(pedido3);
	assertTrue("La base de datos contiene tres elementos",pedido.getIds().size()==3);
}
	public void testFalloEfectivo()
	{
		BDMemoria<TPedido> pedido=new BDMemoria<TPedido>();
		BDMemoria<TSucursal> sucursal = new BDMemoria<TSucursal>();
		
		
		
		DAOPedido daoPedido = new DAOPedido(pedido);
		DAOSucursal daoSucursal = new DAOSucursal(sucursal);
		BusinessPedido BP = new BusinessPedido(daoPedido);
		BusinessSucursal BS = new BusinessSucursal(daoSucursal);
		
		GestionPedidos GPedido = new GestionPedidos(BP, BS, daoPedido);
		
		TSucursal sucursal1 = new TSucursal("42", "Madrid", "Madrid", 84569); 
		TSucursal sucursal2 = new TSucursal("43", "Barcelona", "Barcelona", 84569);
		
		sucursal.insert(sucursal1, "42");
		sucursal.insert(sucursal2, "43");
		
		assertTrue("La BD de sucursales tiene dos sucursales", sucursal.getIds().size() == 2);
		
		TCliente cliente2 = new TCliente("Magdalena", "54015569Z", 968515681);
		
		TPedido pedido1 = new TPedido(cliente2, 2, false, "Random", "1111", MetPago.EFECTIVO, new TSucursal("123", "Yo", "Calle Oculta", 1234),
				new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
		TPedido pedido2 = new TPedido(cliente2, 2, false, "Random", "1112", MetPago.CONTRA_REEMBOLSO, new TSucursal("123", "Yo", "Calle Oculta", 1234),
				new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
		TPedido pedido3 = new TPedido(cliente2, 2, false, "Random", "1113", MetPago.EFECTIVO, new TSucursal("123", "Yo", "Calle Oculta", 1234),
				new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
		
		
		GPedido.realizarPagoEfectivo(pedido1, "no");
		
		GPedido.AñadirPedido(pedido1);
		assertTrue("La BD debe tener al menos un elemento. \n" , pedido.getIds().isEmpty());
		
		GPedido.realizarPagoEfectivo(pedido2, "no");
		GPedido.AñadirPedido(pedido2);
		assertFalse("La base de datos contiene dos elementos",pedido.getIds().size()==2);
		
		GPedido.realizarPagoEfectivo(pedido3, "no");
		GPedido.AñadirPedido(pedido3);
		assertFalse("La base de datos contiene tres elementos",pedido.getIds().size()==3);
		
	GPedido.realizarPagoEfectivo(pedido1, "Alfredo");
		
		GPedido.AñadirPedido(pedido1);
		assertFalse("La BD debe tener al menos un elemento. \n" , pedido.getIds().size()==1);
		
		GPedido.realizarPagoEfectivo(pedido2, "Alfredo");
		GPedido.AñadirPedido(pedido2);
		assertFalse("La base de datos contiene dos elementos",pedido.getIds().size()==2);
		
		GPedido.realizarPagoEfectivo(pedido3, "Manuela");
		GPedido.AñadirPedido(pedido3);
		assertFalse("La base de datos contiene tres elementos",pedido.getIds().size()==3);
		
		
	}
	
	public void testTransferencia(){
		
		BDMemoria<TPedido> pedido=new BDMemoria<TPedido>();
		BDMemoria<TSucursal> sucursal = new BDMemoria<TSucursal>();
		
		
		
		DAOPedido daoPedido = new DAOPedido(pedido);
		DAOSucursal daoSucursal = new DAOSucursal(sucursal);
		BusinessPedido BP = new BusinessPedido(daoPedido);
		BusinessSucursal BS = new BusinessSucursal(daoSucursal);
		
		GestionPedidos GPedido = new GestionPedidos(BP, BS, daoPedido);
		
		TSucursal sucursal1 = new TSucursal("42", "Madrid", "Madrid", 84569); 
		TSucursal sucursal2 = new TSucursal("43", "Barcelona", "Barcelona", 84569);
		
		sucursal.insert(sucursal1, "42");
		sucursal.insert(sucursal2, "43");
		
		assertTrue("La BD de sucursales tiene dos sucursales", sucursal.getIds().size() == 2);
		
		TCliente cliente2 = new TCliente("Magdalena", "54015569Z", 968515681);
		
		TPedido pedido1 = new TPedido(cliente2, 2, false, "Random", "1111", MetPago.TRASFERNCIA, new TSucursal("123", "Yo", "Calle Oculta", 1234),
				new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
		TPedido pedido2 = new TPedido(cliente2, 2, false, "Random", "1112", MetPago.TRASFERNCIA, new TSucursal("123", "Yo", "Calle Oculta", 1234),
				new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
		TPedido pedido3 = new TPedido(cliente2, 2, false, "Random", "1113", MetPago.TRASFERNCIA, new TSucursal("123", "Yo", "Calle Oculta", 1234),
				new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
		
		
		GPedido.realizarPagoTransferecia(pedido1, "5698471236547896", "08/17","564");
		
		GPedido.AñadirPedido(pedido1);
		assertTrue("La BD debe tener al menos un elemento. \n" , pedido.getIds().size()==1);
		
		GPedido.realizarPagoTransferecia(pedido2, "5698471236547896", "08/17","564");
		GPedido.AñadirPedido(pedido2);
		assertTrue("La base de datos contiene dos elementos",pedido.getIds().size()==2);
		
		GPedido.realizarPagoTransferecia(pedido3, "5698471236547896", "08/17","564");
		GPedido.AñadirPedido(pedido3);
		assertTrue("La base de datos contiene tres elementos",pedido.getIds().size()==3);
	}
	
		public void testFalloTransferencia()
		{
			
			BDMemoria<TPedido> pedido=new BDMemoria<TPedido>();
			BDMemoria<TSucursal> sucursal = new BDMemoria<TSucursal>();
			
			
			
			DAOPedido daoPedido = new DAOPedido(pedido);
			DAOSucursal daoSucursal = new DAOSucursal(sucursal);
			BusinessPedido BP = new BusinessPedido(daoPedido);
			BusinessSucursal BS = new BusinessSucursal(daoSucursal);
			
			GestionPedidos GPedido = new GestionPedidos(BP, BS, daoPedido);
			
			TSucursal sucursal1 = new TSucursal("42", "Madrid", "Madrid", 84569); 
			TSucursal sucursal2 = new TSucursal("43", "Barcelona", "Barcelona", 84569);
			
			sucursal.insert(sucursal1, "42");
			sucursal.insert(sucursal2, "43");
			
			assertTrue("La BD de sucursales tiene dos sucursales", sucursal.getIds().size() == 2);
			
			TCliente cliente2 = new TCliente("Magdalena", "54015569Z", 968515681);
			
			TPedido pedido1 = new TPedido(cliente2, 2, false, "Random", "1111", null, new TSucursal("123", "Yo", "Calle Oculta", 1234),
					new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
			TPedido pedido2 = new TPedido(cliente2, 2, false, "Random", "1112", null, new TSucursal("123", "Yo", "Calle Oculta", 1234),
					new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
			TPedido pedido3 = new TPedido(cliente2, 2, false, "Random", "1113", null, new TSucursal("123", "Yo", "Calle Oculta", 1234),
					new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
			
			
			GPedido.realizarPagoEfectivo(pedido1, "no");
			
			GPedido.AñadirPedido(pedido1);
			assertTrue("La BD debe tener al menos un elemento. \n" , pedido.getIds().isEmpty());
			
			GPedido.realizarPagoEfectivo(pedido2, "no");
			GPedido.AñadirPedido(pedido2);
			assertFalse("La base de datos contiene dos elementos",pedido.getIds().size()==2);
			
			GPedido.realizarPagoEfectivo(pedido3, "no");
			GPedido.AñadirPedido(pedido3);
			assertFalse("La base de datos contiene tres elementos",pedido.getIds().size()==3);
			
		GPedido.realizarPagoEfectivo(pedido1, "Alfredo");
			
			GPedido.AñadirPedido(pedido1);
			assertFalse("La BD debe tener al menos un elemento. \n" , pedido.getIds().size()==1);
			
			GPedido.realizarPagoEfectivo(pedido2, "Alfredo");
			GPedido.AñadirPedido(pedido2);
			assertFalse("La base de datos contiene dos elementos",pedido.getIds().size()==2);
			
			GPedido.realizarPagoEfectivo(pedido3, "Manuela");
			GPedido.AñadirPedido(pedido3);
			assertFalse("La base de datos contiene tres elementos",pedido.getIds().size()==3);
			
			
		}
	
	
	
	
}
