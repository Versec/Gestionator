package es.ucm.fdi.interfaz;

import java.util.Scanner;

import es.ucm.fdi.aplicationservice.GestionPedidos;
import es.ucm.fdi.datos.BDMemoria;
import es.ucm.fdi.integracion.Localizacion;
import es.ucm.fdi.integracion.TSucursal;

public class ConsoleView {

	private Scanner leer;
	private GestionPedidos gestionPedidos;

	public ConsoleView(GestionPedidos gestionPedidos){
		this.leer=new Scanner(System.in);
		this.gestionPedidos = gestionPedidos;
	}
	
	
	public void  menu(){
		int n=0;
		boolean goOut=false;
		System.out.println("-----------MENU--------");
		System.out.println("1-Actualizar Pedido. ");
		System.out.println("2-Alta Pedido. ");
		System.out.println("3-Baja Pedido. ");
		System.out.println("4-Baja Empleados. ");
		System.out.println("5-Alta Empleados. ");
		System.out.println("0-Salir. \n");
		System.out.println("Introduzca una opcion: ");
		while(!goOut){
			String s = this.leer.nextLine();
			try{n=Integer.parseInt(s);
			
			//Segun el numero de acciones que tenga el menu, ajustar el rango.
			while(n < 0 || n > 5){
				System.out.println("Error al seleccionar opcion, introduzacala de nuevo: ");	
				 s = this.leer.nextLine();
				 n=Integer.parseInt(s);
				 if(n==0)
					 goOut=true;
				}
			
			if(n!=0)
				ejecutarCasoDeUso(n);
			
			}//Try
			catch(NumberFormatException e){
				System.out.println("Error, introduce un numero: " + System.getProperty("line.separator") );
			}
		
			
		}
		
		
	}
	
	
	
	public void ejecutarCasoDeUso(int n){
		switch(n){
		case 0: System.exit(0);
		case 1: 
		case 2:
			int telefono, Mpago, peso, urgencia;
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
			
			
			
			if (this.gestionPedidos.AñadirPedido(nombre, DNI, telefono,receptor,Mpago,
					dirEnvio, dirLlegada,  peso, urgencia))
			{
					System.out.println("Pedido creado correctamente");
			}
			else
			{
				System.out.println("Fallo en la creacion del pedido");
			}
			this.menu();
			break;
			
		/*case 3: 
			String ID = ValidarID();
			if(eliminarPedido(ID) == 1)
				System.out.println("Pedido eliminado con exito. \n");
			else if(eliminarPedido(ID) == 0)
				System.out.println("Pedido en reparto o ya entregado, imposible de eliminar. \n");
			else 
				System.out.println("El pedido con el codigo introducido no existe. \n");
			this.menu();
			break;*/
		
		case 4:
		case 5:
		case 6:
	}
		
	}
	
	public String ValidarID(){
		System.out.print("Escribe el ID del pedido a buscar: ");
		String s = this.leer.nextLine();
		return s;
		
	}
	
	public boolean ValidarPControl(){
		
		System.out.print("Escribe el ID del punto de control a buscar: ");
		String s = this.leer.nextLine();
		
		Localizacion lugar=ComprobarLocalizacion(s);
		
		return lugar==null ? false : true;
	}
	
	private Localizacion ComprobarLocalizacion(String s){
		boolean goOut=false;
		Localizacion loc=null;
		while( loc==null && !goOut){
			System.out.print("Error al introducir un valor de localizacion, "
					+ "vuelve a intentarlo o introduce un 0 para salir(que el 0 no tenga espacios): ");
			s = this.leer.nextLine();
			
			if(!s.equalsIgnoreCase("0"))
				goOut=true;
			//else
				//loc=Localizacion.StringToLocalizacion(s);
		}
		
		
		return goOut ? null: loc;
	}
	

	
}
