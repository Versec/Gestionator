package es.ucm.fdi;

import es.ucm.fdi.integracion.MetPago;

public class MetodoPago {
	   

	private static final MetPago TRANSFERENCIA = null;

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
		   MetPago metodoPago=TRANSFERENCIA;
		if (metodoPago== MetPago.TRANSFERENCIA){
			   pagado=true;
			   System.out.println("El pago se ha efectuado correctamente");
			   return pagado;
		   }
		   else if (metodoPago == MetPago.CONTRAREEMBOLSO){
			   pagado = false;
			   System.out.println("El pago se realizará en la cogida del pedido");
			   return pagado;
		   }
		   else{
			   pagado=false;
			   System.err.println("No se ha elegido ningún método de pago");
				
		   } return pagado; 
	   }
	 
	   
}
