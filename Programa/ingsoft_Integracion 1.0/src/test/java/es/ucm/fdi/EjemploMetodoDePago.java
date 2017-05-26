package es.ucm.fdi;

import java.io.ByteArrayInputStream;

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
		
		assertTrue("Si se introduce en efectivo la cadena Si deberia devolver true. \n", pedido1.getPagado());
		
		GPedido.realizarPagoEfectivo(pedido1, "Alfredo");
		assertFalse("Si se introduce en efectivo un valor distinto de Si o No deberia devolver false. \n", pedido1.getPagado());
		 
		GPedido.realizarPagoEfectivo(pedido1, "nO");

		 assertTrue("Si se introduce en efectivo la cadena No deberia devolver true. \n", !pedido1.getPagado());
		
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
		
		

		
		
		
		 assertFalse("La tarjeta tiene que tener 16 digitos. \n", GPedido.validarTarjeta("4g5u9v.5999"));
		 
		 assertTrue("Un numero de tarjeta correcto deberia devolver true. \n", GPedido.validarTarjeta("1234567890123456"));

		 assertFalse("Los valores de la tarjeta deben ser numericos. \n", GPedido.validarTarjeta("123456789h123456"));
		
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
		
		
		
		 assertFalse("Si se introduce alguna letra en la fecha deberia devolver false. \n", GPedido.validarFechaCaducidad("1h/18"));
		 
		 assertTrue("Una fecha mayor a la actual deberia devolver true. \n", GPedido.validarFechaCaducidad("09/17"));

		 assertFalse("Una fecha con el mismo aÃ±o pero mes menor que el actual deberia devolver false. \n", GPedido.validarFechaCaducidad("04/17"));
		 
		 assertTrue("Una fecha con el mismo mes pero un aÃ±o mayor que el actual deberia devolver true. \n",GPedido.validarFechaCaducidad("04/18"));
		 
		 assertFalse("Una fecha menor a la actual deberia devolver false. \n", GPedido.validarFechaCaducidad("04/16"));
		
		 assertFalse("Si se introduce una fecha sin la / para diferenciar mes y aÃ±o deberia devolver false. \n", GPedido.validarFechaCaducidad("0417"));
		 
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
		
		
		assertFalse("La tarjeta tiene que tener 3 digitos. \n", GPedido.validarCvc("123456"));
		 
		assertTrue("Un numero de cvc correcto de 3 digitos deberia devolver true. \n",GPedido.validarCvc("123"));

		assertFalse("Los valores de la tarjeta deben ser numericos. \n", GPedido.validarCvc("1g3"));
		
	}

		
}

