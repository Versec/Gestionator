package es.ucm.fdi.integracion;

public class TPedido {

	private String emisor;
	private int repartidor;
	private boolean pagado;
	private String receptor;
	private int codigo;//Es el ID.
	private MetPago metPago;
	private TSucursal SucursalSalida;
	private TSucursal SucursalLlegada;
	private TipoEnvio tipoDeEnvio;
	private TPControl puntoControl;
	
	
	public TPedido(String emisor,int repartidor,boolean pagado,String receptor,int codigo,MetPago metPago,
			TSucursal SucursalSalida,TSucursal SucursalLlegada,TipoEnvio tipoDeEnvio,TPControl puntoControl){
		this.codigo=codigo;
		this.repartidor=repartidor;
		this.emisor=emisor;
		this.metPago=metPago;
		this.pagado=pagado;
		this.puntoControl=puntoControl;
		this.receptor=receptor;
		this.SucursalLlegada=SucursalLlegada;
		this.SucursalSalida=SucursalSalida;
		this.tipoDeEnvio=tipoDeEnvio;
	}
	
	public void setRepartidor(int repartidor){
		this.repartidor=repartidor;
	}
	
	public void setEmisor(String emisor){
		this.emisor=emisor;
	}
	
	public void setPagado(boolean pagado){
		this.pagado=pagado;
	}
	
	public void setReceptor(String receptor){
		this.receptor=receptor;
	}
	
	public void setCodigo(int codigo){
		this.codigo=codigo;
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
	
	
	//Getters
	public String getEmisor(){
		return this.emisor;
	}
	
	public boolean getPagado(){
		return this.pagado;
	}
	
	public String getReceptor(){
		return this.receptor;
	}	
	
	public int getCodigo(){
		return this.codigo;
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
	
}//Class.
