package es.ucm.fdi.integracion;



//He cambiado algunos tipos de atributos, revisar diagramas.
public class TPedido {

	private TCliente emisor;
	private int repartidor;
	private boolean pagado;
	private String receptor;
	private String Id;//Es el ID.--Cambiado.
	private MetPago metPago;
	private TSucursal SucursalSalida;
	private TSucursal SucursalLlegada;
	private TipoEnvio tipoDeEnvio;
	private TPControl puntoControl;
	private int precio;
	private int peso;
	
	public TPedido(TCliente emisor,int repartidor,boolean pagado,String receptor,String Id,MetPago metPago,
			TSucursal SucursalSalida,TSucursal SucursalLlegada,TipoEnvio tipoDeEnvio,TPControl puntoControl, int precio){
		this.Id=Id;
		this.repartidor=repartidor;
		this.emisor=emisor;
		this.metPago=metPago;
		this.pagado=pagado;
		this.puntoControl=puntoControl;
		this.receptor=receptor;
		this.SucursalLlegada=SucursalLlegada;
		this.SucursalSalida=SucursalSalida;
		this.tipoDeEnvio=tipoDeEnvio;
		this.precio = precio;
	}
	


	public void setRepartidor(int repartidor){
		this.repartidor=repartidor;
	}
	
	public void setEmisor(TCliente emisor){
		this.emisor=emisor;
	}
	
	public void setPagado(boolean pagado){
		this.pagado=pagado;
	}
	
	public void setReceptor(String receptor){
		this.receptor=receptor;
	}
	
	public void setId(String codigo){
		this.Id=codigo;
	}
	
	public void setMetPago(MetPago metPago){
		this.metPago=metPago;
	}
	
	public void setSucursalSalida(TSucursal SucursalSalida){
		this.SucursalSalida=SucursalSalida;
	}
	
	public void setSucursalLlegada(TSucursal SucursalLlegada){
		this.SucursalLlegada=SucursalLlegada;
	
	}
	
	public void setTipoEnvio(TipoEnvio tipoDeEnvio){
		this.tipoDeEnvio=tipoDeEnvio;
	}
	
	public void setPControl(TPControl puntoControl){
		this.puntoControl=puntoControl;
	}
	
	public void setPrecio(int precio)
	{
		this.precio = precio;
	}
	public void setPeso(int peso)
	{
		this.peso = peso;
	}
	
	
	//Getters
	public TCliente getEmisor(){
		return this.emisor;
	}
	
	public int getRepartidor(){
		return this.repartidor;
	}
	
	public boolean getPagado(){
		return this.pagado;
	}
	
	public String getReceptor(){
		return this.receptor;
	}	
	
	public String getId(){
		return this.Id;
	}
	
	public MetPago getMetPago(){
		return this.metPago;
	}
	
	public TSucursal getSucursalSalida(){
		return this.SucursalSalida;
	}
	
	public TSucursal getSucursalLlegada(){
		return this.SucursalLlegada;
	}
	
	
	public TipoEnvio getTipoEnvio(){
		return this.tipoDeEnvio;
	}
	
	public TPControl getPControl(){
		return this.puntoControl;
	}
	public int getPrecio(){
		return this.precio;
	}
	public int getPeso(){
		return this.peso;
	}
}//Class.
