package es.ucm.fdi;

import es.ucm.fdi.aplicationservice.GestionPedidos;
import es.ucm.fdi.datos.BDMemoria;
import es.ucm.fdi.integracion.*;
import es.ucm.fdi.interfaz.ConsoleView;
import es.ucm.fdi.negocio.BuisnessPedido;

public class AltaPedido
{
	
	
	
	
    public static void main( String[] args )
 
    {
       /// Inicializar datos
    	BDMemoria<TPedido> bdPedidos = new BDMemoria<TPedido>();
    	
    	// Inicializar integración
    	DAOPedido DAOAltaPedido = new DAOPedido(bdPedidos);
        // TODO
    	BuisnessPedido BO = new BuisnessPedido(DAOAltaPedido);
    	// Inicializar negocio
    	GestionPedidos GestPedidos =  new GestionPedidos(BO);
        // TODO
    	
    	//es.ucm.fdi.datos.ASGestionPedidoImp ASGestionPedido = es.ucm.fdi.datos.ASGestionPedidoImp.
    	// Inicializar presentacion
    	ConsoleView vista = new ConsoleView(GestPedidos);
        // TODO    	
       
    	
    	
    }
    
}

