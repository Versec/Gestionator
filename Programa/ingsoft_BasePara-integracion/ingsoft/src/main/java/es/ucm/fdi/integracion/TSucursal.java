package es.ucm.fdi.integracion;

public class TSucursal {
	
	private int Id;
	private String nombre;
	private String direccion;
	private int codPostal;
	
	
	
	
	public TSucursal(int Id,String nombre,String direccion,int codPostal){
		this.Id=Id;
		this.nombre=nombre;
		this.direccion=direccion;
		this.codPostal=codPostal;
	}
	
	public void setID(int ID){
		this.Id=Id;
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
	
	public int getID(){
		return this.Id;
	}
	
	public String getDireccion(){
		return this.direccion;
	}
	
	public int getCodPostal(){
		return this.codPostal;
	}
	
}

