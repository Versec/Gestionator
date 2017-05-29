package es.ucm.fdi;


import junit.framework.TestCase;
import es.ucm.fdi.aplicationservice.GestionPedidos;
import es.ucm.fdi.datos.BDMemoria;
import es.ucm.fdi.integracion.DAOPedido;
import es.ucm.fdi.integracion.DAOSucursal;
import es.ucm.fdi.integracion.EstadoActual;
import es.ucm.fdi.integracion.Localizacion;

import es.ucm.fdi.integracion.TCliente;
import es.ucm.fdi.integracion.TPControl;
import es.ucm.fdi.integracion.TPedido;
import es.ucm.fdi.integracion.TSucursal;
import es.ucm.fdi.integracion.TipoEnvio;
import es.ucm.fdi.negocio.BusinessPedido;
import es.ucm.fdi.negocio.BusinessSucursal;

	public class EjemploMetodoDePago
	extends TestCase
{
	//Prueba de unidad para metodo de pago
	
	public void testCadenaEfectivo()
	{
		BDMemoria<TPedido> pedido=new BDMemoria<TPedido>();
		BDMemoria<TSucursal> sucursal = new BDMemoria<TSucursal>();
		
		assertTrue("La base de datos deberia estar vacia y tiene elementos. \n", pedido.getIds().isEmpty());
		
		DAOPedido daoPedido = new DAOPedido(pedido);
		DAOSucursal daoSucursal = new DAOSucursal(sucursal);
		BusinessPedido BP = new BusinessPedido(daoPedido);
		BusinessSucursal BS = new BusinessSucursal(daoSucursal);
		
		GestionPedidos GPedido = new GestionPedidos(BP, BS, daoPedido);
		
		TCliente cliente2 = new TCliente("Magdalena", "54015569Z", 968515681);
		TPedido pedido1 = new TPedido(cliente2, 2, true, "Random", "1111", null, new TSucursal("123", "Yo", "Calle Oculta", 1234),
				new TSucursal("123", "El", "Calle Torrijos", 2345), TipoEnvio.NORMAL, new TPControl("Mimente no da para mas",Localizacion.SUCURSAL_INICIO, EstadoActual.NOENVIADO ), 9);
		
		GPedido.realizarPagoEfectivo(pedido1, "si");
		assertTrue("Si se introduce el si al pago en efectivo, efectua el pago. \n", pedido1.getPagado());
		
		GPedido.realizarPagoEfectivo(pedido1, "Alfredo");
		assertFalse("Se ha introducido una respuesta erronea. \n", pedido1.getPagado());
		 
		GPedido.realizarPagoEfectivo(pedido1, "nO");
		assertTrue("Si se introduce el no al pago en efectivo, no se efectua el pago. \n", !pedido1.getPagado());
		
	}

	
	public void testNumeroTarjeta()
	{
		BDMemoria<TPedido> pedido=new BDMemoria<TPedido>();
		BDMemoria<TSucursal> sucursal = new BDMemoria<TSucursal>();
		
		assertTrue("La base de datos deberia estar vacia y tiene elementos. \n", pedido.getIds().isEmpty());
		
		DAOPedido daoPedido = new DAOPedido(pedido);
		DAOSucursal daoSucursal = new DAOSucursal(sucursal);
		BusinessPedido BP = new BusinessPedido(daoPedido);
		BusinessSucursal BS = new BusinessSucursal(daoSucursal);
		
		GestionPedidos GPedido = new GestionPedidos(BP, BS, daoPedido);
		
		
		 assertFalse("Numero de tarjeta mal introducida, debe tener 16 numeros. \n", GPedido.validarTarjeta("4g5u9v.5999"));
		 
		 assertTrue("El numero de tarjeta es correcto. \n", GPedido.validarTarjeta("1234567890123456"));

		 assertFalse("Numero de tarjeta mal introducida, debe tener 16 numeros.\n", GPedido.validarTarjeta("123456789h123456"));
		 
		 assertFalse("Numero de tarjeta mal introducida, debe tener 16 numeros.\n", GPedido.validarTarjeta("1234567"));
		
	}
	
	public void testCaducidadTarjeta()
	{
		
		BDMemoria<TPedido> pedido=new BDMemoria<TPedido>();
		BDMemoria<TSucursal> sucursal = new BDMemoria<TSucursal>();
		
		assertTrue("La base de datos deberia estar vacia y tiene elementos. \n", pedido.getIds().isEmpty());
		
		DAOPedido daoPedido = new DAOPedido(pedido);
		DAOSucursal daoSucursal = new DAOSucursal(sucursal);
		BusinessPedido BP = new BusinessPedido(daoPedido);
		BusinessSucursal BS = new BusinessSucursal(daoSucursal);
		
		GestionPedidos GPedido = new GestionPedidos(BP, BS, daoPedido);
		
		
		
		 assertFalse("El valor del mes es erroneo, debe ser numerico. \n", GPedido.validarFechaCaducidad("1h/18"));
		 
		 assertTrue("Fecha introducida correctamente. \n", GPedido.validarFechaCaducidad("09/17"));

		 assertFalse("La fecha introducida es erronea, el mes introducido es menor que el actual. \n", GPedido.validarFechaCaducidad("04/17"));
		 
		 assertTrue("Fecha introducida correctamente. \n",GPedido.validarFechaCaducidad("04/18"));
		 
		 assertFalse("La fecha introducida es erronea, el agno introducido es menor que el actual.\n", GPedido.validarFechaCaducidad("04/16"));
		
		 assertFalse("La fecha introducida es erronea, se debe introducir la '/' para separar el mes y agno. \n", GPedido.validarFechaCaducidad("0417"));
		 
		 assertFalse("La fecha introducida es erronea, solo debe tener mes y agno.\n", GPedido.validarFechaCaducidad("01/08/17"));
		 
	}
	
	public void testCvcTarjeta()
	{

		BDMemoria<TPedido> pedido=new BDMemoria<TPedido>();
		BDMemoria<TSucursal> sucursal = new BDMemoria<TSucursal>();
		
		assertTrue("La base de datos deberia estar vacia y tiene elementos. \n", pedido.getIds().isEmpty());
		
		DAOPedido daoPedido = new DAOPedido(pedido);
		DAOSucursal daoSucursal = new DAOSucursal(sucursal);
		BusinessPedido BP = new BusinessPedido(daoPedido);
		BusinessSucursal BS = new BusinessSucursal(daoSucursal);
		
		GestionPedidos GPedido = new GestionPedidos(BP, BS, daoPedido);
		
		
		assertFalse("Cvc incorrecto, deberia tener 3 numeros. \n", GPedido.validarCvc("123456"));
		 
		assertTrue("Cvc correcto. \n",GPedido.validarCvc("123"));

		assertFalse("Cvc incorrecto, deberia tener 3 numeros. \n", GPedido.validarCvc("1g3"));
		
		assertFalse("Cvc incorrecto, deberia tener 3 numeros. \n", GPedido.validarCvc("1.3"));
	}

		
}

