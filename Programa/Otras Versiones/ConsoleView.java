package es.ucm.fdi.interfaz;

import java.util.Scanner;

import es.ucm.fdi.aplicationservice.GestionPedidos;
import es.ucm.fdi.integracion.Localizacion;

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
		switch(n){
		case 1:
		
		
		break;
		
		case 2:
		
		break;
		
		case 3:
			
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
	
	public boolean ValidarLocalizacionPControl(){
		
		System.out.print("Añadir: ");
		String s = this.leer.nextLine();
		
		Localizacion lugar=ComprobarLocalizacion(s);
		
		return lugar==null ? false : true;
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
	
}
