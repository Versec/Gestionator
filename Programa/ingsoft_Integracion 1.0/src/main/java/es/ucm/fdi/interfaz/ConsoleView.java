package es.ucm.fdi.interfaz;

import java.util.Scanner;

import es.ucm.fdi.aplicationservice.GestionPedidos;
import es.ucm.fdi.integracion.Localizacion;
import es.ucm.fdi.integracion.MetPago;
import es.ucm.fdi.integracion.TCliente;
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
		System.out.println("1-Actualizar Pedido: ");
		System.out.println("2-Alta Pedido: ");
		System.out.println("3-Baja Pedido: ");
		System.out.println("4-Baja Empleados: ");
		System.out.println("5-Alta Empleados: ");
		System.out.println("0-Salir: ");
		while(!goOut){
			String s = this.leer.nextLine();
			try{n=Integer.parseInt(s);
			
			//Segun el numero de acciones que tenga el menu, ajustar el rango.
			while(n >-1 && n < 6){
				System.out.println("Error al seleccionar accion ");	
				 s = this.leer.nextLine();
				 n=Integer.parseInt(s);
				 if(n==0)
					 goOut=true;
				}
			
			if(n!=0)
				ejecutarCasoDeUso(n);
			
			}//Try
			catch(NumberFormatException e){
				System.out.println("Error, introduce un numero. " + System.getProperty("line.separator") );
			}
		
			
		}
		
		
	}
	
	
	
	
	private void ejecutarCasoDeUso(int n){
		String s="";
		switch(n){
		case 1:
			s=ValidarID();
			Localizacion loc=ValidarLocalizacionPControl();
			this.gPedidos.ActualizarPedido(s, newTPControl());
		
		break;
		
		case 2:
			int telefono, Mpago, peso, urgencia, repartidor;
			String nombre, DNI, receptor, dirEnvio, dirLlegada;
			//Obtener cliente
			System.out.println("Cliente: \n");
			System.out.println(" -nombre: ");
			nombre = this.leer.nextLine();
			System.out.println(" -DNI: ");
			DNI = this.leer.nextLine();
			System.out.println(" -Telefono: ");
			telefono  = this.leer.nextInt();
			//Obtener receptor
			System.out.println("Receptor: \n");
			receptor = this.leer.nextLine();
			//Obtener repartidor
			System.out.println("Repartidor: \n");
			repartidor = this.leer.nextInt();
			//Metodo de pago
			System.out.println("Metodo de pago: \n");
			System.out.println("1- Efectivo \n");
			System.out.println("2- Contrareembolso \n");
			System.out.println("3- Tarjeta \n");
			Mpago = this.leer.nextInt();
			System.out.println("Direccion de envio: \n");
			dirEnvio = this.leer.nextLine();
			System.out.println("Direccion de llegada: \n");
			dirLlegada = this.leer.nextLine();
			System.out.println("Peso del paquete: \n");
			peso = this.leer.nextInt();
			System.out.println("Urgencia: \n");
			System.out.println("1- Normal \n");
			System.out.println("2- Urgente \n");
			urgencia = this.leer.nextInt();
			
			TCliente cliente = new TCliente(nombre, DNI, telefono);
			TSucursal sucursalEnvio = gPedidos.buscarSucursal(dirEnvio);
			TSucursal sucursalLlegada = gPedidos.buscarSucursal(dirLlegada);
			
			
			TPedido pedido =  new TPedido(cliente, repartidor, true, receptor, null,
								MetPago.parsearPago(n), sucursalEnvio, sucursalLlegada,
								TipoEnvio.parsearTipoEnvio(urgencia), null, 0, peso);
			
			
			
			if (sucursalEnvio == null || sucursalLlegada == null)
			{
				System.out.println("Sucursales no encontradas");
			}
			else
			{
				if (this.gPedidos.AñadirPedido(pedido)
				{
					System.out.println("Pedido creado correctamente");
				}
				else
				{
					System.out.println("Fallo en la creacion del pedido");
				}
				this.menu();
			}
			break;
		
		
		case 3:
			String IDPedido = ValidarID();
			if(eliminarPedido(IDPedido) == 1)
				System.out.println("Pedido eliminado con exito. \n");
			else if(eliminarPedido(IDPedido) == 0)
				System.out.println("Pedido en reparto o ya entregado, imposible de eliminar. \n");
			else 
				System.out.println("El pedido con el codigo introducido no existe. \n");
			this.menu();
			
		break;
		
		case 4:
			
		break;
		
		case 5:
			
		break;
			
		
		}
		
	}
	
	
	
	public String ValidarID(){
		System.out.print("Escribe el ID del pedido a buscar: ");
		String s = this.leer.nextLine();
		return s;
		
	}
	
	public Localizacion ValidarLocalizacionPControl(){
		
		System.out.print("Añadir: ");
		String s = this.leer.nextLine();
		
		Localizacion lugar=ComprobarLocalizacion(s);
		
		return lugar;
	}
	
	private Localizacion ComprobarLocalizacion(String s){
		boolean goOut=false;
		Localizacion loc=null;
		loc=Localizacion.StringToLocalizacion(s);
		while( loc==null && !goOut){
			System.out.print("Error al introducir un valor de localizacion, "
					+ "vuelve a intentarlo o introduce un 0 para salir(que el 0 no tenga espacios): ");
			s = this.leer.nextLine();
			
			if(!s.equalsIgnoreCase("0"))
				goOut=true;
			else
				loc=Localizacion.StringToLocalizacion(s);
		}
		
		
		return goOut ? null: loc;
	}
	
	public Integer eliminarPedido(String id){
		return this.gPedidos.eliminar(id);
		
	}
	
}
