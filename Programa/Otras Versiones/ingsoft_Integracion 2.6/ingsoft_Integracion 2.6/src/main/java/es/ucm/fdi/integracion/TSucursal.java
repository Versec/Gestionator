package es.ucm.fdi.integracion;

public class TSucursal {
	
	private String Id;
	private String nombre;
	private String direccion;
	private int codPostal;
	
	
	
	
	public TSucursal(String Id,String nombre,String direccion,int codPostal){
		this.Id=Id;
		this.nombre=nombre;
		this.direccion=direccion;
		this.codPostal=codPostal;
	}
	
	public void setID(String ID){
		this.Id=ID;
	}
	
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	
	public void setDireccion(String dir){
		this.direccion=dir;
	}
	
	public void setCodPostal(int codPostal){
		this.codPostal=codPostal;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getID(){
		return this.Id;
	}
	
	public String getDireccion(){
		return this.direccion;
	}
	
	public int getCodPostal(){
		return this.codPostal;
	}
	
}

