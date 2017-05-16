package es.ucm.fdi;

public class AltaPedido
{
    public static void main( String[] args )
 
    {
        // Inicializar datos
    	
    	// Esto es un ejemplo
    	es.ucm.fdi.datos.BDMemoria<String> tablaCadena=new es.ucm.fdi.datos.BDMemoria<String>();
    	tablaCadena.insert("dato1","1");
    	tablaCadena.insert("dato2", "2");    	
    	System.out.println(tablaCadena);
    	
    	// Inicializar integración
    	
    	es.ucm.fdi.datos.DAOPedido DAOPedido = new es.ucm.fdi.datos.DAOPedidoImp();
        // TODO
    	
    	// Inicializar negocio
    	
        // TODO
    	es.ucm.fdi.datos.ASGestionPedidoImp ASGestionPedido = es.ucm.fdi.datos.ASGestionPedidoImp.
    	// Inicializar presentacion
    	
        // TODO    	
    	
        
    }
    
}

