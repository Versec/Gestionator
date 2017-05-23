package es.ucm.fdi;

import es.ucm.fdi.datos.*;
import es.ucm.fdi.*;
import es.ucm.fdi.datos.BDPedidos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class EjemploAltaPedido 
	extends TestCase
{
	public EjemploAltaPedido(String TestName)
	{
		super(TestName);
	}
	
	public void Test()
	{
		AltaPedido AP = new AltaPedido();
		
		AP.main(null);
	}
	
	/**
	 * Metodo encargado de la correcta creacion de la base de datos
	 */
	public void Creaccion()
	{
		BDPedidos BDP = new BDPedidos();
		assertTrue("La base de datos esta vacia", BDP.getIds().isEmpty());
	}
	
	
	public void PedidoInsertado()
	{
		BDPedidos BDP = new BDPedidos();
		assertTrue("La base tiene un solo elemento. La base contiene: " +BDP, BDP.getIds().size() == 1);
	}

}
