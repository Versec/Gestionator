package es.ucm.fdi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import es.ucm.fdi.aplicationservice.GestionPedidos;
import es.ucm.fdi.datos.BDMemoria;
import es.ucm.fdi.integracion.DAOPedido;
import es.ucm.fdi.integracion.DAOSucursal;
import es.ucm.fdi.integracion.EstadoActual;
import es.ucm.fdi.integracion.Localizacion;
import es.ucm.fdi.integracion.MetPago;
import es.ucm.fdi.integracion.TEmpleado;
import es.ucm.fdi.integracion.TPControl;
import es.ucm.fdi.integracion.TPedido;
import es.ucm.fdi.integracion.TSucursal;
import es.ucm.fdi.integracion.TipoEnvio;
import es.ucm.fdi.interfaz.ConsoleView;
import es.ucm.fdi.negocio.BusinessPedido;
import es.ucm.fdi.negocio.BusinessSucursal;

public class Gestionator {

	public static void main(String[] args) throws FileNotFoundException {
		
		//Crear la base de datos de pedidos y empleados.
		BDMemoria<TPedido> BDPedidos=new es.ucm.fdi.datos.BDMemoria<TPedido>();
		BDMemoria<TEmpleado> BDEmpleados=new es.ucm.fdi.datos.BDMemoria<TEmpleado>();
		BDMemoria<TSucursal> BDSucursales=new es.ucm.fdi.datos.BDMemoria<TSucursal>();
		//Llenar las dos bases de datos.
		try{
		llenarBdPedidos(BDPedidos);
		llenarBdEmpleados(BDEmpleados);
		llenarBdSucursales(BDSucursales);
		
		//Inicializar integracion:
		DAOPedido daoPedido = new DAOPedido(BDPedidos);
		DAOSucursal daoSucursal = new DAOSucursal(BDSucursales);
		
		
		//Inicializar negocio:
		BusinessPedido BOPedido = new BusinessPedido(daoPedido);
		BusinessSucursal BOSucursal = new BusinessSucursal(daoSucursal);
		
		//Inicializar Apliccation Service:
		GestionPedidos gestionPedidos = new GestionPedidos(BOPedido, BOSucursal, daoPedido);
		
		//Inicializar interfaz:
		
		ConsoleView vista = new ConsoleView(gestionPedidos);
		vista.menu();
		}
		catch(FileNotFoundException|NumberFormatException e){
			System.out.println("Error al cargar los datos: " + e.getMessage() + System.getProperty("line.separator"));
		}
	}

	
	
	private static void llenarBdSucursales(BDMemoria<TSucursal> bDSucursales) {
		// TODO Auto-generated method stub
		
	}



	private static  void llenarBdPedidos(BDMemoria<TPedido> BD ) throws FileNotFoundException,NumberFormatException{
		Scanner sc = new Scanner(new File("DatosPedidos.txt"));
        String s="";
		while(sc.hasNextLine()){
        	s=sc.nextLine();
        	String[] words = s.split(" +");
        	TPedido nuevo = generarTPedido(words);
        	BD.insert(nuevo, words[0]);
		}
          
        sc.close();
    
	}
	
	private static  void llenarBdEmpleados(BDMemoria<TEmpleado> BD ) throws FileNotFoundException,NumberFormatException{
		Scanner sc = new Scanner(new File("DatosEmpleados.txt"));
        String s="";
		while(sc.hasNextLine()){
        	s=sc.nextLine();
        	String[] words = s.split(" +");
        	TEmpleado nuevo = generarTEmpleado(words);
        	BD.insert(nuevo, words[0]);
		}
          
        sc.close();
    
	}
	
	
	
	
	private static  TPedido generarTPedido(String[] words)throws NumberFormatException{
		
		
		//El value of puede petar en metPago
	
		return new TPedido(words[1], words[2], Boolean.valueOf(words[3]), words[4], Integer.parseInt(words[5]), 
				MetPago.valueOf(words[6]), generarTSucursal(Integer.parseInt(words[7]), words[8], words[9], 
				Integer.parseInt(words[10])), generarTSucursal(Integer.parseInt(words[11]), words[12], words[13], 
				Integer.parseInt(words[14])), TipoEnvio.valueOf(words[15]),  
				generarTPControl(words[16], Localizacion.valueOf(words[17]), EstadoActual.valueOf(words[28])));
		
		
		
	}
	
	
		
	private static TEmpleado generarTEmpleado(String[] words)throws NumberFormatException{
		return new TEmpleado(Integer.parseInt(words[0]), words[1],words[2],Boolean.valueOf(words[3]));
	}
	
	private static TSucursal generarTSucursal(String id, String nombre, String direccion, int codPostal){
		return new TSucursal(id, nombre,direccion,codPostal);
	}
		
	
	private static TPControl generarTPControl(String estado, Localizacion localizacionActual, EstadoActual estadoActual){
		
		return new TPControl(estado, localizacionActual, estadoActual);
	}
	
	/*
	public void readFich(String fich) throws FileNotFoundException, ArrayException, IOException {
        
        this.sProgram.deleteAll();
        
	}
	*/
	
}