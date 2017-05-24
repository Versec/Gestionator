package es.ucm.fdi;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import es.ucm.fdi.aplicationservice.GestionPedidos;
import es.ucm.fdi.datos.*;
import es.ucm.fdi.*;
import es.ucm.fdi.integracion.DAOPedido;
import es.ucm.fdi.integracion.EstadoPedido;
import es.ucm.fdi.integracion.Localizacion;
import es.ucm.fdi.integracion.TPControl;
import es.ucm.fdi.integracion.TPedido;
import es.ucm.fdi.integracion.TSucursal;
import es.ucm.fdi.integracion.TipoEnvio;
import es.ucm.fdi.negocio.BuisnessPedido;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class EjemploMetodoPago
	extends TestCase
{
	//Prueba de unidad para crear un pedido
	/**
	 * Test para la correcta inserccion de datos mediante las DAO
	 * 
	 * Comprueba la creacion de una BD vacia, inserta datos comprobando su correcta inserccion
	 */
	public void testCadenaEfectivo()
	{
		MetodoPago metodo = new MetodoPago();
		
		 assertFalse("Si se introduce en efectivo un valor distinto de Si o no deberia devolver false. \n", metodo.compruebaEntrada("otro"));
		 
		 assertTrue("Si se introduce en efectivo la cadena si deberia devolver true. \n", metodo.compruebaEntrada("sI"));

		 assertTrue("Si se introduce en efectivo la cadena no deberia devolver true. \n", metodo.compruebaEntrada("No"));
		
	}
	
	public void testChequeaEfectivo()
	{
		MetodoPago metodo = new MetodoPago();
		
		 assertFalse("Si se introduce en efectivo un valor distinto de Si o no deberia devolver false. \n", metodo.chequeaRespuesta("otro"));
		 
		 assertTrue("Si se introduce en efectivo la cadena si deberia devolver true. \n", metodo.chequeaRespuesta("sI"));

		 assertFalse("Si se introduce en efectivo la cadena no deberia devolver true. \n", metodo.chequeaRespuesta("No"));
		
	}
	
	public void testNumeroTarjeta()
	{
		MetodoPago metodo = new MetodoPago();
		
		 assertFalse("La tarjeta tiene que tener 16 digitos. \n", metodo.validarTarjeta("4g5u9v.5999"));
		 
		 assertTrue("Un numero de tarjeta correcto deberia devolver true. \n", metodo.validarTarjeta("1234567890123456"));

		 assertFalse("Los valores de la tarjeta deben ser numericos. \n", metodo.validarTarjeta("123456789h123456"));
		
	}
	
	public void testCaducidadTarjeta()
	{
		MetodoPago metodo = new MetodoPago();
		
		 assertFalse("Si se introduce alguna letra en la fecha deberia devolver false. \n", metodo.validarFechaCaducidad("1ñ/18"));
		 
		 assertTrue("Una fecha mayor a la actual deberia devolver true. \n", metodo.validarFechaCaducidad("09/17"));

		 assertFalse("Una fecha con el mismo año pero mes menor que el actual deberia devolver false. \n", metodo.validarFechaCaducidad("04/17"));
		 
		 assertTrue("Una fecha con el mismo mes pero un año mayor que el actual deberia devolver true. \n", metodo.validarFechaCaducidad("04/18"));
		 
		 assertFalse("Una fecha menor a la actual deberia devolver false. \n", metodo.validarFechaCaducidad("04/16"));
		
		 assertFalse("Si se introduce una fecha sin la / para diferenciar mes y año deberia devolver false. \n", metodo.validarFechaCaducidad("0417"));
		 
	}
	
	public void testCvcTarjeta()
	{
		MetodoPago metodo = new MetodoPago();
		
		assertFalse("La tarjeta tiene que tener 3 digitos. \n", metodo.validarCvc("123456"));
		 
		assertTrue("Un numero de cvc correcto de 3 digitos deberia devolver true. \n", metodo.validarCvc("123"));

		assertFalse("Los valores de la tarjeta deben ser numericos. \n", metodo.validarCvc("1g3"));
		
	}
	
	public void testContrareembolso()
	{
		MetodoPago metodo = new MetodoPago();
		assertFalse("contrarreembolso false. \n", metodo.realizarPago(MetodoDePago.Contrarembolso));
	}
	
	
	//Prueba de unidad DAO
/*	public void testMetodoPago()
	{
		BDMemoria<TPedido> pedido = new BDMemoria<TPedido>();
		MetodoPago daoPedido = new MetodoPago();
		
		
		assertTrue("La BD debía estar vacía y tiene elementos. \n", pedido.getIds().isEmpty());
		
		//inserto nuevos pedidos
		
		TPedido pedidoPrueba = new TPedido("Fulgencio",5, true, "Alvaro", "256877" ,MetodoDePago.Efectivo, new TSucursal("28", "Valencia", "Desconocida" , 26841),
				new TSucursal("28", "Madrid", "Desconocida" , 24811),TipoDeEnvio.Urgente, new TPControl(EstadoPedido.Almacen, Localizacion.SUCURSAL_INICIO),58);
		
		daoPedido.add(pedidoPrueba, "2897"); //cambiar codigo a un int
		
		assertTrue("La base de datos contiene un elemento",pedido.getIds().size()==1);
		
		
		
	}*/
	/**
	 * Test para comprobar la correcta inserccion de datos mediante los BO
	 */
	
	//Prueba de unidad BO
/*	public void testAltaPedidoAñadirBuissnesPedido()
	{
		BDMemoria<TPedido> pedido=new BDMemoria<TPedido>();
		
		
		
		assertTrue("La base de datos deberia estar vacia y tiene elementos. \n", pedido.getIds().isEmpty());
		
		TPedido pedido1 = new TPedido("Magdalena", 2, true, "Random", "1111", MetodoDePago.Contrarembolso, new TSucursal("123", "Yo", "Calle Oculta", 1234),
				new TSucursal("123", "El", "Calle Torrijos", 2345), TipoDeEnvio.Normal, new TPControl(EstadoPedido.Perdido, Localizacion.SUCURSAL_INICIO), 9);	
		
		DAOPedido daoPedido1 = new DAOPedido(pedido);
		
		
		BuisnessPedido BOPedido = new BuisnessPedido(daoPedido1);
		
		BOPedido.Añadir(pedido1, "1111");

		
		
		assertTrue("La BD debe tener al menos un elemento. \n" , pedido.getIds().size()==1);
		
		TPedido pedido2 = new TPedido("Magdalena", 2, true, "Random", "1112", MetodoDePago.Contrarembolso, new TSucursal("123", "Yo", "Calle Oculta", 1234),
				new TSucursal("123", "El", "Calle Torrijos", 2345), TipoDeEnvio.Normal, new TPControl(EstadoPedido.Perdido, Localizacion.SUCURSAL_INICIO), 9);
		
		DAOPedido daoPedido2 = new DAOPedido(pedido);
		
		BOPedido = new BuisnessPedido(daoPedido2);
		
		//Introduzco un segundo elemento
		
		BOPedido.Añadir(pedido2, "1112");
		
		assertTrue("La base de datos contiene mas de un elemento", pedido.getIds().size() >1);
		
		//Compruebo que el pedido introducio esta en la base de datos mesiante su codigo
		assertTrue("La base de datos contiene un pedido con codigo '1112'", pedido.find("1112")!= null);
	}
	
	
	public void testAltaPedidoAñadirGestionPedidos()
	{
		BDMemoria<TPedido> pedido=new BDMemoria<TPedido>();
		
		/*TPedido pedido1 = new TPedido("Magdalena", 2, true, "Random", "1111", MetodoDePago.Contrarembolso, new TSucursal("123", "Yo", "Calle Oculta", 1234),
				new TSucursal("123", "El", "Calle Torrijos", 2345), TipoDeEnvio.Normal, new TPControl(EstadoPedido.Perdido, Localizacion.SUCURSAL_INICIO), 9);	
		//
		DAOPedido daoPedido2 = new DAOPedido(pedido);
		BuisnessPedido BOPedido1 = new BuisnessPedido(daoPedido2);
		
		GestionPedidos GPedido = new GestionPedidos(BOPedido1);
		BDMemoria<TSucursal> tablaSucursales = new BDMemoria<TSucursal>();
		TSucursal sucursal1 = new TSucursal("42", "Madrid", "Madrid", 84569); 
		TSucursal sucursal2 = new TSucursal("43", "Barcelona", "Barcelona", 84569);
		
		tablaSucursales.insert(sucursal1, "42");
		tablaSucursales.insert(sucursal2, "43");
		
		assertTrue("La BD de sucursales tiene dos sucursales", tablaSucursales.getIds().size() == 2);
		
		
		GPedido.AñadirPedido("Manuela", "ALfredo", 2, "42", "43", 40,tablaSucursales,1);
		
		assertTrue("La BD debe tener al menos un elemento. \n" , pedido.getIds().size()==1);
		GPedido.AñadirPedido("Manuela", "nop", 1, "42", "43", 82,tablaSucursales,0);
		GPedido.AñadirPedido("Manuela", "fredo", 0, "42", "43", 7,tablaSucursales,1);
		
		assertTrue("La base de datos contiene tres elementos",pedido.getIds().size()==3);
		
		
		
	}*/
		
}
