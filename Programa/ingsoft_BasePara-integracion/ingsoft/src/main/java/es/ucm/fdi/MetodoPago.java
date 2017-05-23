package es.ucm.fdi;

import java.util.Scanner;

import es.ucm.fdi.datos.MetodoDePago;



public class MetodoPago {
	   
	Scanner sc = new Scanner(System.in);
	
	public static void main( String[] args )
	   
	    {
		   
		   // Inicializar datos
		   es.ucm.fdi.datos.BDMemoria<String> tablaCadena=new es.ucm.fdi.datos.BDMemoria<String>();
	    	tablaCadena.insert("dato1","1");
	    	tablaCadena.insert("dato2", "2");    	
	    	System.out.println(tablaCadena);
	    	
	    	
	    	// Inicializar integración
	    	
	    	 es.ucm.fdi.integracion.DAOPedido DAOPedido = new es.ucm.fdi.integracion.DAOPedido();
	    	
	    	// Inicializar negocio
	    	
	    	 es.ucm.fdi.negocio.BuisnessPedido BuisnessPedido = new es.ucm.fdi.negocio.BuisnessPedido();
	    	
	    	// Inicializar presentacion  	
	    	
	    }
	    
	   public boolean Pagado(MetodoDePago metPago){
		   boolean pagado =false;
		 
		   if (metPago== MetodoDePago.Efectivo){
			   System.out.println("¿Se ha efectuado el pago en efectivo?");
			   //Mostrar un mensaje preguntando  si se ha pagado si es que si se puede poner pagado 
			   //si es no a false.
			   pagado=true;
			   System.out.println("El pago se efectuado correctamente");
			   return pagado;
		   }
		if (metPago== MetodoDePago.Transferencia){
			//Pedir datos
			   pagado=true;
			   System.out.println("El pago se ha efectuado correctamente");
			   return pagado;
		   }
		   else if (metPago == MetodoDePago.Contrarembolso){
			   //Envio lo paga al recibir el envio.
			   pagado = false;
			   System.out.println("El pago se realizará en la recogida del pedido");
			   return pagado;
		   }
		   else{
			   pagado=false;
			   System.err.println("No se ha elegido ningún método de pago");
				
		   } return pagado; 
	   }
	 
	       
}
