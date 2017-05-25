package es.ucm.fdi.integracion;

public class TCliente {

	private String Nombre;
	private String DNI;
	private int Telefono;
	
	
	
	public TCliente(String Nombre,String DNI,int Telefono)
	{
		this.Nombre = Nombre;
		this.DNI =DNI;
		this.Telefono = Telefono;
	}
	
	public String getNombre()
	{
		return this.Nombre;
	}
	public String getDNI()
	{
		return this.DNI;
	}
	public int getTelefono()
	{
		return this.Telefono;
	}
	public void setNombre(String Nombre)
	{
		this.Nombre =Nombre;
	}
	public void setDNI(String DNI)
	{
		this.DNI = DNI;
	}
	public void setTelefono(int tel)
	{
		this.Telefono = tel;
	}
	
	
	
}
