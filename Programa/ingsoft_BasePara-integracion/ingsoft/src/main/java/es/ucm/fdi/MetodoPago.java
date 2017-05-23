package es.ucm.fdi;


import java.util.Calendar;
import java.util.Scanner;

import es.ucm.fdi.datos.MetodoDePago;




public class MetodoPago {
	   
	Scanner sc = new Scanner(System.in);

	
	/*public static void main( String[] args )
	   
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
	    	
	    }*/
	    
	   public boolean realizarPago(MetodoDePago metPago){
		   boolean pagado = false;
		   boolean salida = false;
		 
		   if (metPago== MetodoDePago.Efectivo){
			   System.out.println("¿Se ha efectuado el pago en efectivo?Si/No");
			
				while(salida==false){
				//Lee una linea por teclado
				String linea = sc.nextLine().toLowerCase();
				if (linea.equals("Si")){
					  pagado = true;
					  System.out.println("El pago se efectuado correctamente");
					  salida = true;  
					
				}else if (linea.equals("No")){
					pagado = false;
					System.out.println("El pago no se a efectuado");
					salida = true;
				}
				else
					System.out.println("Introduce Si o No");
				}
				return pagado;
			  
			  
		   }
		   else if (metPago== MetodoDePago.Transferencia){
			
			   String numTj = "";
			   String cadTj = "";
			   String cvcTj = "";
			   boolean salidaTj = false;
			   boolean salidaCad = false;
			   boolean salidaCvc = false;
			   pagado=true;
			   
			   while (salidaTj == false){
				   System.out.println("Introduzca su número de tarjeta de crédito");
				   numTj = sc.nextLine().toLowerCase();
				   
				   boolean tarjetaValida = validarTarjeta(numTj);
				   
				   if(tarjetaValida == true){
					   
					   while (salidaCad == false){
						   System.out.println("Introduzca fecha de caducidad de la tarjeta");
						   cadTj = sc.nextLine().toLowerCase();
						   boolean caducidadValida = validarFechaCaducidad(cadTj);//funcionInventada(cadTj);
						   if(caducidadValida == true){
							   
							   while (salidaCvc == false){
								   System.out.println("Introduzca el CVC de la tarjeta");
								   cvcTj = sc.nextLine().toLowerCase();
								   boolean cvcValida = validarCvc(cvcTj);//funcionInventada(cvcTj);
								   if(cvcValida == true){
									   salidaCvc = true;
									   salidaCad = true;
									   salidaTj = true;
									   pagado = true;
									   System.out.println("Se ha pagao");
								   }else{
									   salidaCvc = true;
									   salidaCad = true;
									   System.out.println("Use otra tarjeta");   
								   }
							   }
							   
							   
						   }else{
							   System.out.println("Fecha de caducidad no valida.");
							   System.out.println("Use otra tarjeta.");
							   salidaCad = true;
						   }
					   }
					   
					   
				   }else{
					   System.out.println("Número no valido.");
				   }
					   
					  
				
				   
				   
			   }
			   
			   
			   
			   System.out.println("El pago se ha efectuado correctamente");
			   return pagado;
		   }
		   else if (metPago == MetodoDePago.Contrarembolso){
			 
			
			   pagado = false;
			   System.out.println("El pago se realizará en la recogida del pedido");
			   return pagado;
		   }
		   else{
			   pagado=false;
			   System.err.println("No se ha elegido ningún método de pago");
				
		   } return pagado; 
	   }
	   private boolean validarTarjeta(String numTj){
		   
		   if (numTj.length() == 16)
			   return true;
		   
		   
		   return false;
		   
	   }
	   private boolean validarFechaCaducidad(String cadTj){
		  
		   String[] fecha = cadTj.split("/");
		   int mesAct = Calendar.MONTH;
		   int agnoAct = Calendar.YEAR;
		   
		   if(Integer.parseInt(fecha[0]) == agnoAct)
			   if (Integer.parseInt(fecha[1]) >= mesAct)
				   return true;
		   else if(Integer.parseInt(fecha[0]) > agnoAct)
			   return true;
		   
		   return false;
		   
	   }
	   private boolean validarCvc (String cvcTj){
		  
		   if (cvcTj.length() == 3)
			   return true;
		   
		   
		   return false;
		   
	   }
	   
	   
	 
	       
}
