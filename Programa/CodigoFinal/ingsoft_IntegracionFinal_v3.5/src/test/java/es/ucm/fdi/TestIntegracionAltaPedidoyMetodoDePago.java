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


//Integracion de metodo de pago y alta pedido
//Metodo de pago carece de test de unidad por lo que solo se realizara
//test de sistema

public class TestIntegracionAltaPedidoyMetodoDePago extends TestCase{
	
	//Pruebas de integracion de los casos de uso AltaPedido y MetodoDePago
	public void testEfectivo(){
	
	BDMemoria<TPedido> pedido=new BDMemoria<TPedido>();
	BDMemoria<TSucursal> sucursal = new BDMemoria<TSucursal>();
	
	
	
	DAOPedido daoPedido = new DAOPedido(pedido);
	DAOSucursal daoSucursal = new DAOSucursal(sucursal);
	BusinessPedido BP = new BusinessPedido(daoPedido);
	BusinessSucursal BS = new BusinessSucursal(daoSucursal);
	
	GestionPedidos GPedido = new GestionPedidos(BP, BS, daoPedido);
	
	
	//Insercion de sucursales con el fin de comprobar el correcto funcionamiento del tranferObject de sucursal
	//y su uso necesario en el test de insercion de pedido
	
	TSucursal sucursal1 = new TSucursal("42", "Madrid", "Madrid", 84569); 
	TSucursal sucursal2 = new TSucursal("43", "Barcelona", "Barcelona", 84569);
	
	sucursal.insert(sucursal1, "42");
	sucursal.insert(sucursal2, "43");
	
	assertTrue("La BD de sucursales tiene dos sucursales", sucursal.getIds().size() == 2);
	
	//Creacion de 3 clientes distintos
	
	TCliente cliente2 = new TCliente("Magdalena", "54015569Z", 968515661);
	TCliente cliente3 = new TCliente("Fer", "12345678l", 968515381);
	TCliente cliente4 = new TCliente("Er", "22345678q", 968515681);
	
	// Creacion de 3 pedidos distintos
	
	TPedido pedido1 = new TPedido(cliente2, 2, false, "Random", "1111", MetPago.EFECTIVO, new TSucursal("123", "Yo", "Calle Oculta", 1234),
			new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
	TPedido pedido2 = new TPedido(cliente3, 2, false, "Random", "1112", MetPago.EFECTIVO, new TSucursal("123", "Yo", "Calle Oculta", 1234),
			new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
	TPedido pedido3 = new TPedido(cliente4, 2, false, "Random", "1113",MetPago.TRANSFERENCIA, new TSucursal("123", "Yo", "Calle Oculta", 1234),
			new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
	
	/*Comprobacion de la entrada introducida sea si para que se efectue el pago*/
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
	
	//Comprobacion de la isercion de sucursales
	assertTrue("La BD de sucursales tiene dos sucursales", sucursal.getIds().size() == 2);
	
	TCliente cliente2 = new TCliente("Magdalena", "54015569Z", 968515681);
	TCliente cliente3 = new TCliente("Fer", "12345678l", 968515381);
	TCliente cliente4 = new TCliente("Er", "22345678q", 968515681);
	
	TPedido pedido1 = new TPedido(cliente2, 2, false, "Random", "1111", MetPago.TRANSFERENCIA, new TSucursal("123", "Yo", "Calle Oculta", 1234),
			new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
	TPedido pedido2 = new TPedido(cliente4, 2, false, "Random", "1112", MetPago.TRANSFERENCIA, new TSucursal("123", "Yo", "Calle Oculta", 1234),
			new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
	TPedido pedido3 = new TPedido(cliente3, 2, false, "Random", "1113", MetPago.TRANSFERENCIA, new TSucursal("123", "Yo", "Calle Oculta", 1234),
			new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
	
	/*Comprobacion de la cadena introducida del numero de tarjeta sean 16 digitos; que la fecha sea igual o 
	 * mayor a la actual, teniendo el campo mes y agno separados por "/" con dos digitos ambos campos; y que
	 * el cvc sean 3 digitos*/
	GPedido.realizarPagoTransferencia(pedido1, "5698471236547896", "08/17","564");
	GPedido.AñadirPedido(pedido1);
	assertTrue("La BD debe tener al menos un elemento. \n" , pedido.getIds().size()==1);
	
	GPedido.realizarPagoTransferencia(pedido2, "5698471236547896", "08/17","564");
	GPedido.AñadirPedido(pedido2);
	assertTrue("La base de datos contiene dos elementos",pedido.getIds().size()==2);
	
	GPedido.realizarPagoTransferencia(pedido3, "5698471236547896", "08/17","564");
	GPedido.AñadirPedido(pedido3);
	assertTrue("La base de datos contiene tres elementos",pedido.getIds().size()==3);
}		

		
	public void testContrareembolso(){
		
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
	TCliente cliente3 = new TCliente("Fer", "12345678l", 968515381);
	TCliente cliente4 = new TCliente("Er", "22345678q", 968515681);
	
	TPedido pedido1 = new TPedido(cliente2, 2, false, "Random", "1111", MetPago.CONTRA_REEMBOLSO, new TSucursal("123", "Yo", "Calle Oculta", 1234),
			new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
	TPedido pedido2 = new TPedido(cliente3, 2, false, "Random", "1112", MetPago.CONTRA_REEMBOLSO, new TSucursal("123", "Yo", "Calle Oculta", 1234),
			new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
	TPedido pedido3 = new TPedido(cliente4, 2, false, "Random", "1113",MetPago.CONTRA_REEMBOLSO, new TSucursal("123", "Yo", "Calle Oculta", 1234),
			new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);	
	
	/*Comprobacion de la entrada introducida sea si para que se efectue el pago*/
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
	
		
	
}
