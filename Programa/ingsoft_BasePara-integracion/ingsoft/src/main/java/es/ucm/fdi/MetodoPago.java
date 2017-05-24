package es.ucm.fdi;


import java.util.Calendar;
import java.util.Scanner;

import es.ucm.fdi.datos.MetodoDePago;




public class MetodoPago {
	   
	Scanner sc = new Scanner(System.in);

	
	 /* public static void main( String[] args )
	   
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
	   	
	    } */

		public boolean compruebaEntrada(String valor) {
			return (valor.equalsIgnoreCase("si") || valor.equalsIgnoreCase("no"));
		}
		
		public boolean chequeaRespuesta(String valor) {
			if (valor.equalsIgnoreCase("si")){
				  System.out.println("El pago se efectuado correctamente");					
			}else if (valor.equalsIgnoreCase("no")){
				System.out.println("El pago no se a efectuado");
			}
			return valor.equalsIgnoreCase("si");
		}
	
	
	   public boolean realizarPago(MetodoDePago metPago){
		   boolean pagado = false;
		   boolean salida = false;
		 
		   if (metPago== MetodoDePago.Efectivo){
			   //El encargado pone "si" en la aplicación, si el cliente ha efectuado el pago
			   System.out.println("¿Se ha efectuado el pago en efectivo?Si/No");
			
			   String linea = sc.nextLine().toLowerCase();
				while(!compruebaEntrada(linea)){
					System.out.println("Introduce Si o No");
					//Lee una linea por teclado
					linea = sc.nextLine().toLowerCase();
				}
				return chequeaRespuesta(linea);
			  
			  
		   }
		   //De forma bancaria
		   else if (metPago== MetodoDePago.Transferencia){
			
			   String numTj = "";
			   String cadTj = "";
			   String cvcTj = "";
			   
			   System.out.println("Introduzca su número de tarjeta de crédito o x para cancelar");
			   numTj = sc.nextLine().toLowerCase();
			   do {
				   if (numTj.equalsIgnoreCase("x"))
					   salida = true;
			   } while ( !validarTarjeta(numTj) && !salida );
			   
			   if (!salida) {
				   System.out.println("Introduzca fecha de caducidad de la tarjeta o x para cancelar");
				   cadTj = sc.nextLine().toLowerCase();
				   do {
					   if (cadTj.equalsIgnoreCase("x"))
						   salida = true;
				   } while ( !validarFechaCaducidad(cadTj) && !salida );
			   }
			   
			   if (!salida) {
				   System.out.println("Introduzca el CVC de la tarjeta o x para cancelar");
				   cvcTj = sc.nextLine().toLowerCase();
				   do {
					   if (cvcTj.equalsIgnoreCase("x"))
						   salida = true;
				   } while ( !validarCvc(cvcTj) && !salida );
			   }
			   
			   if (salida) {
				   System.out.println("El pago ha sido cancelado");
			   } else {
				   System.out.println("Se ha efectuado la transferencia");
			   }
			   
			   return !salida;
				   
		   }
		   else if (metPago == MetodoDePago.Contrarembolso){
			   System.out.println("¿Se ha efectuado el pago a contrarembolso?Si/No");
				
			   String linea = sc.nextLine().toLowerCase();
				while(!compruebaEntrada(linea)){
					System.out.println("Introduce Si o No");
					//Lee una linea por teclado
					linea = sc.nextLine().toLowerCase();
				}
				return chequeaRespuesta(linea);
			  
			  
		   }
			
		   else{
			   
			   System.err.println("No se ha elegido ningún método de pago");
				
		   } return pagado; 
	   }
	   /*Comprueba si los digitos son correctos*/
	 
		   
		   public boolean validarTarjeta(String numTj){
			   int i;
			   boolean valido = false;
			   if (numTj.length() == 16){
				   valido=true;
				   for (i=0; i!= 16; i++) {
					   valido = valido && (numTj.charAt(i) >='0' && numTj.charAt(i) <= '9');
				   }   return valido;
			   }
			   
			
			   
		   return valido;
		   
	   }
	   /*Comprueba la fecha de caducidad de la tarjeta*/
	   public boolean validarFechaCaducidad(String cadTj){
		  
		   String[] fecha = cadTj.split("/");
		   Calendar fechaActual = Calendar.getInstance();
		   int mesAct = fechaActual.get(Calendar.MONTH)+1;
		   int agnoAct =fechaActual.get(Calendar.YEAR)%100;
		   
		   if (fecha.length == 2){
				 
			   try{
			   		int agnoFecha  = Integer.parseInt(fecha[1]);
			   		int mesFecha =	Integer.parseInt(fecha[0]);	
			   		if ((agnoFecha > agnoAct) || (agnoFecha == agnoAct && mesFecha >= mesAct)){
			   			
			   			return true;}
			   } catch (NumberFormatException nfe) {
				   return false;
			   }
		   }
		   return false;		   
	   }
	   /*Comprueba que se introduzca 3 digitos de cvc*/
	   public boolean validarCvc (String cvcTj){
		  
		   if (cvcTj.length() == 3){
			   try {
					Integer.parseInt(cvcTj);
					return true;
				} catch (NumberFormatException nfe) {
					return false;
				}
		   }
		   
		   return false;
		   
	   }
	   
	   
	 
	       
}
