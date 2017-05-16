package es.ucm.fdi.integracion;

public class TPControl {

	private String estado;
	private Localizacion localizacionActual;
	
	public TPControl(String estado,Localizacion localizacionActual){
		this.estado=estado;
		this.localizacionActual=localizacionActual;
	}

	public void setEstado(String estado){
		this.estado=estado;
	}

	public void setLocalizacionActual(Localizacion localizacionActual){
		this.localizacionActual=localizacionActual;
	}

	public String getEstado(){
		return this.estado;
	}
	
	public Localizacion getLocaclizacionActual(){
		return this.localizacionActual;
	}
	
	
	
}
