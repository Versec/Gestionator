package es.ucm.fdi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Scanner;

import es.ucm.fdi.aplicationservice.GestionPedidos;
import es.ucm.fdi.datos.BDMemoria;
import es.ucm.fdi.integracion.DAOPedido;
import es.ucm.fdi.integracion.DAOSucursal;
import es.ucm.fdi.integracion.EstadoActual;
import es.ucm.fdi.integracion.Localizacion;
import es.ucm.fdi.integracion.MetPago;
import es.ucm.fdi.integracion.TCliente;
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
		llenarBdPedidos(BDPedidos,BDSucursales);
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
		guardarPedidos(BDPedidos);
		guardarSucursales(BDSucursales);
		}
		catch(Exception e){
			System.out.println("Error al cargar los datos: " + e.getMessage() + System.getProperty("line.separator"));
		}
	}

	
	
	private static void guardarSucursales(BDMemoria<TSucursal> BDSucursales) {
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("DatosSucursales.txt")));
			String contenidoArchivo = "";
			List<TSucursal> a =BDSucursales.getList();
			for(int i = 0; i <a.size();i++){
				contenidoArchivo = contenidoArchivo +a.get(i) + System.getProperty("line.separator");
			}
			bw.write(contenidoArchivo);
			
			bw.close();
			}
			catch(IOException e){
				System.out.println("Hubo algun error al guardar la lista");
			}
	}



	private static void guardarPedidos(BDMemoria<TPedido> BDPedidos) {
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("DatosPedidos.txt")));
			String contenidoArchivo = "";
			List<TPedido> a =BDPedidos.getList();
			for(int i = 0; i <a.size();i++){
				contenidoArchivo = contenidoArchivo +a.get(i) + System.getProperty("line.separator");
			}
			bw.write(contenidoArchivo);
			
			bw.close();
			}
			catch(IOException e){
				System.out.println("Hubo algun error al guardar la lista");
			}
	}



	private static void llenarBdSucursales(BDMemoria<TSucursal> BD) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("DatosSucursales.txt"));
        String s="";
		while(sc.hasNextLine()){
        	s=sc.nextLine();
        	String[] words = s.split(" +");
        	TSucursal nuevo = generarTSucursalBD(words);
        	if(BD.find(words[0])==null)
        		BD.insert(nuevo, words[0]);
        	
		}
          
        sc.close();
		
	}



	private static  void llenarBdPedidos(BDMemoria<TPedido> BD,BDMemoria<TSucursal> BDSucursales ) throws Exception{
		File Archivo=new File("DatosPedidos.txt");
		FileReader fr =new FileReader(Archivo);
		BufferedReader br = new BufferedReader(fr);
		String s=null;
		s=br.readLine();
		while(s!=null && !s.equalsIgnoreCase("")){
        	String[] words = s.split(" +");
        	TPedido nuevo = generarTPedido(words ,BDSucursales);
        	BD.insert(nuevo, words[0]);
        	s=br.readLine();
		}
          
        br.close();
        fr.close();
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
	
	
	
	
	private static  TPedido generarTPedido(String[] words,BDMemoria<TSucursal> BDSucursales)throws Exception{
		
		
		/*
		 * Se sigue el orden de paso de parametros de la constructora.
		 * 
		 * TPedido(TCliente emisor,int repartidor,boolean pagado,String receptor,String Id,MetPago metPago,
			TSucursal SucursalSalida,TSucursal SucursalLlegada,TipoEnvio tipoDeEnvio,TPControl puntoControl, int precio){

		 * */
		
		
	
		
		return new TPedido(generarTCliente(words),Integer.parseInt(words[4]) ,Boolean.valueOf(words[5]),
				words[6],words[7], MetPago.stringToPago(words[8]),
				generarTSucursal(words[9], words[10], words[11],Integer.parseInt(words[12]),BDSucursales),
				generarTSucursal(words[13], words[14], words[15],Integer.parseInt(words[16]),BDSucursales),
				TipoEnvio.stringToPago(words[17]),
				generarTPControl(words[18], Localizacion.StringToLocalizacion(words[19]),EstadoActual.StringToEstadoActual(words[20])),
				Integer.parseInt(words[21]));
		
		
	
		
		
	}
	
	private static TCliente generarTCliente(String[] words){
		return new TCliente(words[1], words[2],Integer.parseInt(words[3]));
	}
		
	private static TEmpleado generarTEmpleado(String[] words)throws NumberFormatException{
		return new TEmpleado(Integer.parseInt(words[0]), words[1],words[2],Boolean.valueOf(words[3]));
	}
	
	/**
	 * Se añade a la BD de sucursal.
	 * */
	private static TSucursal generarTSucursal(String id, String nombre, String direccion,
			int codPostal,BDMemoria<TSucursal> BDSucursales){
		TSucursal a=new TSucursal(id, nombre,direccion,codPostal);
		BDSucursales.insert(a, a.getID());
		
		return a;
	}
	
	private static TSucursal generarTSucursalBD(String words[]){
		return new TSucursal(words[0], words[1], words[2], Integer.parseInt(words[3]));
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