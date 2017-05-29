package es.ucm.fdi.integracion;

public class TCliente {

	private String nombre;
	private String DNI;
	private int telefono;
	
	
	
	public TCliente(String nombre, String DNI, int telefono){
		this.nombre=nombre;
		this.DNI=DNI;
		this.telefono = telefono;
	}
	
	
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	
	public void setDNI(String DNI){
		this.DNI=DNI;
	}
	public void setTelefono(int telefono)
	{
		this.telefono = telefono;
	}
	
	public String getDNI(){
		return this.DNI;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	public int getTelefono()
	{
		return this.telefono;
	}
	
	
}
