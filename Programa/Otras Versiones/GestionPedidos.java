package es.ucm.fdi.aplicationservice;

import es.ucm.fdi.integracion.DAOPedido;
import es.ucm.fdi.integracion.TPControl;
import es.ucm.fdi.integracion.TPedido;
import es.ucm.fdi.negocio.BuisnessPedido;

public class GestionPedidos {

	private BuisnessPedido negocioPedido;
	private DAOPedido daoPedido;
	
	
	public GestionPedidos(BuisnessPedido negocioPedido,DAOPedido daoPedido){
		this.negocioPedido=negocioPedido;
		this.daoPedido=daoPedido;
	}
	
	public boolean ActualizarPedido(String Id, TPControl Pcontrol){
		boolean found=true;
		TPedido pedido=this.daoPedido.leer(Id);
		if(pedido==null)
			found=false;
		else{
		pedido.setPControl(Pcontrol);
		this.negocioPedido.updatePControl(pedido);
		}
		
		return found;
	}
}
