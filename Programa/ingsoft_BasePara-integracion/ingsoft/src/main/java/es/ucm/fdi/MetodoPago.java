package es.ucm.fdi;

public class MetodoPago {
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
	   
	   public boolean Pagado(){
		   boolean pagado =false;
		   if (metodoPago== TRANSFERENCIA){
			   pagado=true;
			   return pagado;
		   }
		   else if (metodoPago == CONTRAREEMBOLSO){
			   pagado = false;
			   return pagado;
		   }
		   else
			   System.out.println("No hay método de pago");
		
		   return pagado;  
	   }
	 
	   
}
