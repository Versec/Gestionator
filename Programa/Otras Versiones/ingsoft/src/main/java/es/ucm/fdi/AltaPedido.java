package es.ucm.fdi;

import es.ucm.fdi.aplicationservice.GestionPedidos;
import es.ucm.fdi.datos.BDMemoria;
import es.ucm.fdi.datos.MetodoDePago;
import es.ucm.fdi.integracion.*;
import es.ucm.fdi.negocio.BuisnessPedido;

public class AltaPedido
{
	
	
	
	
    public static void main( String[] args )
 
    {
       /// Inicializar datos
    	es.ucm.fdi.datos.BDMemoria<es.ucm.fdi.integracion.TPedido> BDPedidos = new es.ucm.fdi.datos.BDMemoria<es.ucm.fdi.integracion.TPedido>();
    	// Esto es un ejemplo
    	/*es.ucm.fdi.datos.BDMemoria<String> tablaCadena=new es.ucm.fdi.datos.BDMemoria<String>();
    	tablaCadena.insert("dato1","1");
    	tablaCadena.insert("dato2", "2");    	
    	System.out.println(tablaCadena);*/
    	BDMemoria<TPedido> bdPedidos = new BDMemoria<TPedido>();
    	
    	// Inicializar integración
    	
    	DAOPedido DAOAltaPedido = new DAOPedido(bdPedidos);
        // TODO
    	BuisnessPedido BO = new BuisnessPedido(DAOAltaPedido);
    	// Inicializar negocio
    	GestionPedidos ASAltaPedido =  new GestionPedidos(BO);
        // TODO
    	
    	//es.ucm.fdi.datos.ASGestionPedidoImp ASGestionPedido = es.ucm.fdi.datos.ASGestionPedidoImp.
    	// Inicializar presentacion
    	
        // TODO    	
       
    	
    	
    }
    
}

