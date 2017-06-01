package es.ucm.fdi.integracion;

public class TPControl {

	private String estado;
	private Localizacion localizacionActual;
	private EstadoActual estadoActual;
	
	public TPControl(String estado,Localizacion localizacionActual, EstadoActual estadoActual){
		this.estado=estado;
		this.localizacionActual=localizacionActual;
		this.estadoActual = estadoActual;
	}

	public void setEstado(String estado){
		this.estado=estado;
	}

	public void setLocalizacionActual(Localizacion localizacionActual){
		this.localizacionActual=localizacionActual;
	}
	
	public void setEstadoActual(EstadoActual estadoActual){
		this.estadoActual = estadoActual;
	}

	public String getEstado(){
		return this.estado;
	}
	
	public Localizacion getLocaclizacionActual(){
		return this.localizacionActual;
	}
	
	public EstadoActual getEstadoActual(){
		return this.estadoActual;
	}
	
	public String toStringPControl(){
		String s=" Estado: "+this.estado+" Localizacion Actual: "+this.localizacionActual.toString()
				+" Estado Actual: "+this.estadoActual.toString()+System.getProperty("line.separator");

		return s;
	}
	
	public String toString(){
		return this.estado+" "+this.localizacionActual.toString()+" "+this.estadoActual.toString();
	}
	
	
}
