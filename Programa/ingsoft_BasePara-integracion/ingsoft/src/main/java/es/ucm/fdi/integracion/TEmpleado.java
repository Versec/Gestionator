package es.ucm.fdi.integracion;

public class TEmpleado {

	private int Id;
	private String nombre;
	private String email;
	private boolean activo;
	
	public TEmpleado( int Id,String nombre,String email,boolean activo){
		this.activo=activo;
		this.email=email;
		this.Id=Id;
		this.nombre=nombre;
	}
	
	
	
	public int getID(){
		return this.Id;
	}

	public String getNombre(){
		return this.nombre;
	}

	public String getEmail(){
		return this.email;
	}

	public boolean getActivo(){
		return this.activo;
	}

	public void setID(int id){
		this.Id=id;
	}

	public void setNombre(String nombre){
		this.nombre=nombre;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public void setActivo(boolean activo){
		this.activo=activo;
	}


}
