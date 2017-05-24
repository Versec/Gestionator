package es.ucm.fdi;

import es.ucm.fdi.datos.*;
import es.ucm.fdi.*;
import es.ucm.fdi.datos.BDPedidos;
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


public class EjemploAltaPedido 
	extends TestCase
{
	//Prueba de unidad para crear un pedido
	
	public void testVacio()
	{
		BDPedidos<TPedido> pedido = new BDPedidos<TPedido>();
		assertTrue("La BD debía estar vacía y tiene elementos. \n", pedido.getIds().isEmpty());
	}
	
	public void testAltaPedidoDAOPedido()
	{
		BDPedidos<TPedido> pedido = new BDPedidos<TPedido>();
		DAOPedido daoPedido = new DAOPedido();
		
		
		assertTrue("La BD debía estar vacía y tiene elementos. \n", pedido.getIds().isEmpty());
		
		//inserto nuevos pedidos
		
		TPedido pedidoPrueba = new TPedido("Fulgencio",5, true, "Alvaro", "256877" ,MetodoDePago.Efectivo, new TSucursal(28, "Valencia", "Desconocida" , 26841),
				new TSucursal(28, "Madrid", "Desconocida" , 24811),TipoDeEnvio.Urgente, new TPControl(EstadoPedido.Almacen, Localizacion.SUCURSAL_INICIO),58);
		
		daoPedido.add(pedidoPrueba, "2897"); //cambiar codigo a un int
		
		assertTrue("La base de datos contiene un elemento",pedido.getIds().size()==1);
		
		
		
		
		
	}
	
		
}
