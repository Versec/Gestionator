package es.ucm.fdi.integracion;

public class TPControl {

	private EstadoPedido estado;
	private Localizacion localizacionActual;
	
	public TPControl(EstadoPedido estado,Localizacion localizacionActual){
		this.estado=estado;
		this.localizacionActual=localizacionActual;
	}

	public void setEstado(EstadoPedido estado){
		this.estado=estado;
	}

	public void setLocalizacionActual(Localizacion localizacionActual){
		this.localizacionActual=localizacionActual;
	}

	public EstadoPedido getEstado(){
		return this.estado;
	}
	
	public Localizacion getLocaclizacionActual(){
		return this.localizacionActual;
	}
	
	
	
}
