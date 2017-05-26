package es.ucm.fdi;

import java.io.ByteArrayInputStream;

import junit.framework.TestCase;
import es.ucm.fdi.datos.*;


public class EjemploMetodoPago
	extends TestCase
{
	//Prueba de unidad para metodo de pago
	
	public void testCadenaEfectivo()
	{
		MetodoPago metodo = new MetodoPago();
		
		 assertFalse("Si se introduce en efectivo un valor distinto de Si o No deberia devolver false. \n", metodo.compruebaEntrada("otro"));
		 
		 assertTrue("Si se introduce en efectivo la cadena Si deberia devolver true. \n", metodo.compruebaEntrada("sI"));

		 assertTrue("Si se introduce en efectivo la cadena No deberia devolver true. \n", metodo.compruebaEntrada("No"));
		
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
	
	
	public void testEfectivo()
	{
		ByteArrayInputStream in1 = new ByteArrayInputStream("si".getBytes());
		System.setIn(in1);
		MetodoPago metodo = new MetodoPago();
		assertTrue("Efectivo debe salir true. \n", metodo.realizarPago(MetodoDePago.Efectivo));
		
		System.setIn(System.in);
		
		ByteArrayInputStream in2 = new ByteArrayInputStream("no".getBytes());
		System.setIn(in2);
		metodo = new MetodoPago();
		assertFalse("Efectivo debe salir false. \n", metodo.realizarPago(MetodoDePago.Efectivo));
	}
	public void testTransferencia()
	{
		ByteArrayInputStream in1 = new ByteArrayInputStream("x".getBytes());
		System.setIn(in1);
		MetodoPago metodo = new MetodoPago();
		assertFalse("Si la operación se cancela debe salir false. \n", metodo.realizarPago(MetodoDePago.Transferencia));
		
		
	}
	
	public void testContrareembolso()
	{
		
		
		ByteArrayInputStream in1 = new ByteArrayInputStream("si".getBytes());
		System.setIn(in1);
		MetodoPago metodo = new MetodoPago();
		assertTrue("Contrarembolso debe salir true. \n", metodo.realizarPago(MetodoDePago.Contrarembolso));
		
		System.setIn(System.in);
		
		ByteArrayInputStream in2 = new ByteArrayInputStream("no".getBytes());
		System.setIn(in2);
		metodo = new MetodoPago();
		assertFalse("Contrarembolso debe salir false. \n", metodo.realizarPago(MetodoDePago.Contrarembolso));
		
	}
	
		
}
