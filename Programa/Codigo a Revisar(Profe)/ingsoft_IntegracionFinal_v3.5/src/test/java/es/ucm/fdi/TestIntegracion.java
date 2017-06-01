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


public class TestIntegracion extends TestCase {

	//Prueba de unidad DAO
	public void testIntregracionCasosDeUsoDAO()
	{
		BDMemoria<TPedido> pedido = new BDMemoria<TPedido>();
		DAOPedido daoPedido = new DAOPedido(pedido);
		
		assertTrue("La bd esta vacia", pedido.getIds().isEmpty());
		
		TCliente cliente1 = new TCliente("Fulgencio", "54068569U", 968515681);
		TPControl control =new TPControl("Desconocido", Localizacion.SUCURSAL_INICIO ,EstadoActual.NOENVIADO);
		TPedido pedidoPrueba = new TPedido(cliente1,5, true, "Alvaro", "0001" ,MetPago.CONTRA_REEMBOLSO, new TSucursal("28", "Valencia", "Desconocida" , 26841),
				new TSucursal("28", "Madrid", "Desconocida" , 24811),TipoEnvio.URGENTE, control,58);
		
		TPedido pedidoPrueba2 = new TPedido(cliente1,5, true, "Alvaro", "0002" ,MetPago.CONTRA_REEMBOLSO, new TSucursal("28", "Valencia", "Desconocida" , 26841),
				new TSucursal("28", "Madrid", "Desconocida" , 24811),TipoEnvio.URGENTE, control,58);
		
		//Compruebo si el pedido se ha añadido.
		daoPedido.add(pedidoPrueba, "0001");
		daoPedido.add(pedidoPrueba2, "0002");
		
		assertTrue("",pedido.find("0001").getId() == "0001");
		assertTrue("",pedido.find("0002").getId() == "0002");
		assertTrue("", pedido.getIds().size() == 2);
		
		//Modifico el punto de control del pedido de la BD.
		
		TPControl control2 =new TPControl("CalleJoseDominguez", Localizacion.SUCURSAL_INICIO ,EstadoActual.REPARTO);
		
		
		pedidoPrueba.setPControl(control2);
		daoPedido.actualizar(pedidoPrueba);
		//Compruebo que el Punto control tiene los valores actualizados.
		assertTrue("",pedido.find("0001").getPControl().getEstado() == "CalleJoseDominguez" 
				&& pedido.find("0001").getPControl().getEstadoActual()== EstadoActual.REPARTO);
		assertTrue("",pedido.find("0002").getPControl().getEstado() == "Desconocido" 
				&& pedido.find("0002").getPControl().getEstadoActual()== EstadoActual.NOENVIADO);
		
		//Procedo a la eliminación de un pedido.
		daoPedido.eliminar("0001");
		
		assertTrue("", pedido.getIds().size() == 1);
		assertTrue("",pedido.find("0001") == null);
		assertTrue("",pedido.find("0002").getId() == "0002");
		
	}
	//Test de unidad BusinessObject
	public void testIntgracionCasosDeUsoBO()
	{
		BDMemoria<TPedido> pedido = new BDMemoria<TPedido>();
		DAOPedido daoPedido = new DAOPedido(pedido);
		BusinessPedido BP = new BusinessPedido(daoPedido);
		
		TCliente cliente1 = new TCliente("Fulgencio", "54068569U", 968515681);
		TPControl control =new TPControl("Desconocido", Localizacion.SUCURSAL_INICIO ,EstadoActual.NOENVIADO);
		TPedido pedidoPrueba = new TPedido(cliente1,5, true, "Alvaro", "0001" ,MetPago.CONTRA_REEMBOLSO, new TSucursal("28", "Valencia", "Desconocida" , 26841),
				new TSucursal("28", "Madrid", "Desconocida" , 24811),TipoEnvio.URGENTE, control,58);
		
		TPedido pedidoPrueba2 = new TPedido(cliente1,5, true, "Alvaro", "0002" ,MetPago.CONTRA_REEMBOLSO, new TSucursal("28", "Valencia", "Desconocida" , 26841),
				new TSucursal("28", "Madrid", "Desconocida" , 24811),TipoEnvio.URGENTE, control,58);
		
		//Añado a la BD desde del Business
		BP.Añadir(pedidoPrueba, "0001");
		BP.Añadir(pedidoPrueba2, "0002");
		//Compruebo
		assertTrue("",pedido.find("0001").getId() == "0001");
		assertTrue("",pedido.find("0002").getId() == "0002");
		assertTrue("", pedido.getIds().size() == 2);
		
		TPControl control2 =new TPControl("CalleJoseDominguez", Localizacion.SUCURSAL_INICIO ,EstadoActual.REPARTO);
		pedidoPrueba.setPControl(control2);
		BP.updatePControl(pedidoPrueba);
		
		//Compruebo que el Punto control tiene los valores actualizados.
		assertTrue("",pedido.find("0001").getPControl().getEstado() == "CalleJoseDominguez" 
				&& pedido.find("0001").getPControl().getEstadoActual()== EstadoActual.REPARTO);
		assertTrue("",pedido.find("0002").getPControl().getEstado() == "Desconocido" 
				&& pedido.find("0002").getPControl().getEstadoActual()== EstadoActual.NOENVIADO);
				
		
		
		//Procedo a la eliminación de un pedido.
		BP.eliminar("0002");
				
		assertTrue("", pedido.getIds().size() == 1);
		assertTrue("",pedido.find("0002") == null);
		assertTrue("",pedido.find("0001").getId() == "0001");
				
		
	}
	
	//Test de sistema
	public void testIntgracionCasosDeUsoAS()
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
		
		

		TCliente cliente1 = new TCliente("Fulgencio", "54068569U", 968515681);
		TCliente cliente2 = new TCliente("Fulgencio", "54068577U", 968515681);
		TPControl control =new TPControl("Desconocido", Localizacion.SUCURSAL_INICIO ,EstadoActual.NOENVIADO);
		TPedido pedidoPrueba = new TPedido(cliente1,5, false, "Alvaro", "0001" ,MetPago.CONTRA_REEMBOLSO, new TSucursal("28", "Valencia", "Desconocida" , 26841),
				new TSucursal("28", "Madrid", "Desconocida" , 24811),TipoEnvio.URGENTE, null,58);
		
		TPedido pedidoPrueba2 = new TPedido(cliente2,5, false, "Alvaro", "0002" ,MetPago.CONTRA_REEMBOLSO, new TSucursal("28", "Valencia", "Desconocida" , 26841),
				new TSucursal("28", "Madrid", "Desconocida" , 24811),TipoEnvio.URGENTE, null,58);
		
		pedidoPrueba.setPControl(control);
		pedidoPrueba2.setPControl(control);
		/*Comprobacion de la entrada introducida sea si para que se efectue el pago*/
		GPedido.realizarPagoEfectivo(pedidoPrueba, "si");
		GPedido.realizarPagoEfectivo(pedidoPrueba2, "si");
		GPedido.AñadirPedido(pedidoPrueba);
		assertTrue("La BD debe tener al menos un elemento. \n" , pedido.getIds().size()==1);
		
		GPedido.AñadirPedido(pedidoPrueba2);
		assertTrue("La base de datos contiene dos elementos",pedido.getIds().size()==2);
		
		assertTrue("pago realizado", pedidoPrueba.getPagado());
		assertTrue("pago realizado", pedidoPrueba2.getPagado());
		
		
		//Pruebas actualizacion del punto de control desde el aplication service
		TPControl control2 =new TPControl("CalleJoseDominguez", Localizacion.SUCURSAL_INTERMEDIA ,EstadoActual.REPARTO);
		boolean ok= GPedido.ActualizarPedido("54068569Up", control2);
		
		//Compruebo el valor booleano de la funcion.
		assertTrue("No se ha encontrado el elemento y si esta ",ok);
		
		//Compruebo si se ha actualizado el pedido selecccionado.
		assertTrue("",pedido.find("54068569Up").getPControl().getEstado() == "CalleJoseDominguez" 
				&& pedido.find("54068569Up").getPControl().getEstadoActual()== EstadoActual.REPARTO 
				&& pedido.find("54068569Up").getPControl().getLocaclizacionActual()== Localizacion.SUCURSAL_INTERMEDIA );
		assertTrue("",pedido.find("54068577Up").getPControl().getEstado() == "EnSucursalInicial" 
				&& pedido.find("54068577Up").getPControl().getEstadoActual()== EstadoActual.NOENVIADO
				&& pedido.find("54068577Up").getPControl().getLocaclizacionActual()== Localizacion.SUCURSAL_INICIO);
				
		GPedido.eliminar("54068577Up");	
		
	assertTrue("", pedido.getIds().size() == 1);
	assertTrue("",pedido.find("54068569Up")!=null);
	assertTrue("",pedido.find("54068577Up") == null);
	}
	
	
	
}
