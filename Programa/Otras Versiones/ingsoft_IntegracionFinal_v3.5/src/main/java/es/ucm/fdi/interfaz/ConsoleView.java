package es.ucm.fdi.interfaz;

import java.util.Scanner;

import es.ucm.fdi.aplicationservice.GestionPedidos;
import es.ucm.fdi.integracion.EstadoActual;
import es.ucm.fdi.integracion.Localizacion;
import es.ucm.fdi.integracion.MetPago;
import es.ucm.fdi.integracion.TCliente;
import es.ucm.fdi.integracion.TPControl;
import es.ucm.fdi.integracion.TPedido;
import es.ucm.fdi.integracion.TSucursal;
import es.ucm.fdi.integracion.TipoEnvio;

public class ConsoleView {

	private Scanner leer;
	private GestionPedidos gPedidos;

	public ConsoleView(GestionPedidos gPedidos){
		this.leer=new Scanner(System.in);
		this.gPedidos= gPedidos;
	}
	
	
	public void  menu(){
		int n=0;
		boolean goOut=false;
		
		
		while(!goOut){
			
			System.out.println("1-Actualizar pedido: ");
			System.out.println("2-Alta pedido: ");
			System.out.println("3-Baja pedido: ");
			System.out.println("4-Mostrar sucursales: ");
			System.out.println("5-Mostrar pedidos: ");
			System.out.println("0-Salir: ");
			String s = this.leer.next();
			try{
				n=Integer.parseInt(s);
			//Segun el numero de acciones que tenga el menu, ajustar el rango.
			while(n <-1 && n > 6){
				System.out.println("Error al seleccionar accion ");	
				 s = this.leer.next();
				 n=Integer.parseInt(s);
				}//While Comprobacion.
			
				 
			if(n!=0){
				ejecutarCasoDeUso(n);
				this.leer.reset();
			}else
				goOut=true;
			
			}catch(NumberFormatException e){
				System.out.println("Error, introduce un numero. " + System.getProperty("line.separator") );
			}
		
		}//While Grande.
		
		this.leer.close();

	}
	
	
	
	
	private void ejecutarCasoDeUso(int n){
		String s,a="";
		boolean ok=false;
		switch(n){
		case 1:
			s=PedirID();
			a=PedirEstado();
			Localizacion loc=ValidarLocalizacionPControl();
			if(loc!=null){
				EstadoActual state=ValidarEstadoActualPControl();
				if(state!=null)
					ok=this.gPedidos.ActualizarPedido(s, new TPControl(a,loc,state));
				else
					System.out.println("Operacion finalizada. " + System.getProperty("line.separator"));
				
			}else	
				System.out.println("Operacion finalizada. " + System.getProperty("line.separator"));
			
			
			if(ok)
			System.out.println("Operacion realizada con exito. " + System.getProperty("line.separator"));
			else
				System.out.println("Error, el pedido a buscar no esta en la BD. " + System.getProperty("line.separator"));
		break;
		
		case 2:
			int telefono, Mpago, peso, urgencia, repartidor;
			String nombre, DNI, receptor, dirEnvio, dirLlegada;
			//Obtener cliente
			
			try{
			System.out.println("Cliente: ");
			System.out.println(" -nombre: ");
			nombre = this.leer.next();
			System.out.println(" -DNI(9 cifras): ");
			DNI = this.leer.next();
			System.out.println(" -Telefono: ");
			telefono  = this.leer.nextInt();
			//Obtener receptor
			System.out.println("Receptor(nombre): ");
			receptor = this.leer.next();
			//Obtener repartidor
			System.out.println("nº Repartidor: ");
			repartidor = Integer.parseInt(this.leer.next());
			//Metodo de pago
			System.out.println("Metodo de pago: ");
			System.out.println("1- Efectivo ");
			System.out.println("2- Contrareembolso ");
			System.out.println("3- Tarjeta ");
			Mpago = this.leer.nextInt();
			System.out.println("Codigo sucursal envio: ");
			dirEnvio = this.leer.next();
			System.out.println("Codigo sucursal llegada: ");
			dirLlegada = this.leer.next();
			System.out.println("Peso del paquete: ");
			peso = this.leer.nextInt();
			System.out.println("Urgencia: ");
			System.out.println("1- Normal ");
			System.out.println("2- Urgente ");
			urgencia = this.leer.nextInt();
			
			TCliente cliente = new TCliente(nombre, DNI, telefono);
			TSucursal sucursalEnvio = gPedidos.buscarSucursal(dirEnvio);
			TSucursal sucursalLlegada = gPedidos.buscarSucursal(dirLlegada);
			MetPago tPago = MetPago.parsearPago(Mpago);
			
			TPedido pedido =  new TPedido(cliente, repartidor, false, receptor, null,
					tPago, sucursalEnvio, sucursalLlegada,
								TipoEnvio.parsearTipoEnvio(urgencia), null, peso);
			
			
			if (tPago == MetPago.EFECTIVO)
			{
				String pago;
				
				System.out.println("¿Se ha efectuado el pago en efectivo?Si/No");
				pago  = leer.next();
				
				gPedidos.realizarPagoEfectivo(pedido, pago);
				
			}
			
				else if (tPago == MetPago.TRANSFERENCIA)
				{
					String fecha, CVC, numTarjeta;
					boolean salida=false;
					
					do{
					
					System.out.println("Introduzca su número de tarjeta de crédito o x para cancelar: ");
					numTarjeta = leer.next().toLowerCase();
					
					   
					System.out.println("Introduzca fecha de caducidad de la tarjeta o x para cancelar: ");
					fecha  = leer.next().toLowerCase();
					   
					 System.out.println("Introduzca el CVC de la tarjeta o x para cancelar: ");
					 CVC = leer.next().toLowerCase();
					 
					 salida=numTarjeta.equalsIgnoreCase("X")||fecha.equalsIgnoreCase("X")||CVC.equalsIgnoreCase("X");
					}while (!gPedidos.realizarPagoTransferencia(pedido, numTarjeta, fecha, CVC) && !salida);
					
					 if (gPedidos.realizarPagoTransferencia(pedido, numTarjeta, fecha, CVC))
					 {
						   System.out.println("Se ha efectuado la transferencia.");
					 }  
					 else
					 {
						 System.out.println("El pago ha sido cancelado.");
					 } 
				}
			else if (tPago == MetPago.CONTRA_REEMBOLSO)
			{
				String pago;
				
				System.out.println("¿Se ha efectuado el pago tras la entrega?Si/No");
				pago  = leer.next();
				
				gPedidos.realizarPagoEfectivo(pedido, pago);
				
			}
				

		
			if (sucursalEnvio == null || sucursalLlegada == null)
			{
				System.out.println("Sucursales no encontradas.");
			}
			else
			{
				if (this.gPedidos.AñadirPedido(pedido))
				{
					System.out.println("Pedido creado correctamente.");
				}
				else
				{
					System.out.println("Fallo en la creacion del pedido");
				}
			
			}
		}//Try
		catch(Exception e){
			System.out.println(e.getMessage());
		}
			break;
		
		
		case 3:
			String IDPedido = PedirID();
			if(eliminarPedido(IDPedido) == 1)
				System.out.println("Pedido eliminado con exito. \n");
			else if(eliminarPedido(IDPedido) == 0)
				System.out.println("Pedido en reparto o ya entregado, imposible de eliminar. \n");
			else 
				System.out.println("El pedido con el codigo introducido no existe. \n");
			
		break;
		
		case 4:
			System.out.println(this.gPedidos.toStringSucursal());
		break;
			
		case 5:
			System.out.println(this.gPedidos.toStringPedido());
		break;
		}
		
	}
	
	
	
	public String PedirID(){
	
		System.out.print("Escribe el ID del pedido a buscar: ");
		String s = this.leer.next();
		return s;
		
	}
	
	public String PedirEstado(){
		System.out.print("Escribe los detalles que creas convenientes sobre el estado del pedido, sin espacios: ");
		String s = this.leer.next();
		return s;
		
	}
	
	
	
	public EstadoActual ValidarEstadoActualPControl(){
		
		System.out.print("Detalle en que tipo de estado se encuentra el pedido: ");
		String s = this.leer.next();
		
		EstadoActual state=ComprobarEstadoActual(s);
		
		return state;
	}
	
	
	public EstadoActual ComprobarEstadoActual(String s){
		boolean goOut=false;
		EstadoActual state=null;
		state=EstadoActual.StringToEstadoActual(s);
		while( state==null && !goOut){
			System.out.print("Error al introducir el estado actual, "
					+ "vuelve a intentarlo o escribe salir: "+System.getProperty("line.separator"));
			s = this.leer.next();
			
			if(s.equalsIgnoreCase("salir"))
				goOut=true;
			else
				state=EstadoActual.StringToEstadoActual(s);
		}
		if(goOut)
			System.out.println("Operacion cancelada. " + System.getProperty("line.separator"));
		
		
		return goOut ? null: state;
		
	}
	
	
	public Localizacion ValidarLocalizacionPControl(){
		
		System.out.print("Detalla en que tipo de sucursal esta el pedido: ");
		String s = this.leer.next();
		
		Localizacion lugar=ComprobarLocalizacion(s);
		
		return lugar;
	}
	
	private Localizacion ComprobarLocalizacion(String s){
		boolean goOut=false;
		Localizacion loc=null;
		loc=Localizacion.StringToLocalizacion(s);
		while( loc==null && !goOut){
			System.out.println("Error al introducir un valor de localizacion, "
					+ "vuelve a intentarlo o escribe salir: "+System.getProperty("line.separator"));
			s = this.leer.next();
			
			if(s.equalsIgnoreCase("salir"))
				goOut=true;
			else
				loc=Localizacion.StringToLocalizacion(s);
		}
		
		if(goOut)
			System.out.println("Operacion cancelada. " + System.getProperty("line.separator"));
		
		
		
		return goOut ? null: loc;
	}
	
	public Integer eliminarPedido(String id){
		return this.gPedidos.eliminar(id);
		
	}
	
}
