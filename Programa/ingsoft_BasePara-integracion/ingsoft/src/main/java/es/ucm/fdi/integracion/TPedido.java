package es.ucm.fdi.integracion;

import es.ucm.fdi.datos.MetodoDePago;
import es.ucm.fdi.datos.TipoDeEnvio;

public class TPedido {

	private String emisor;
	private int repartidor;
	private boolean pagado;
	private String receptor;
	private String codigo;//Es el ID.
	private MetodoDePago metPago;
	private TSucursal SucursalSalida;
	private TSucursal SucursalLlegada;
	private TipoDeEnvio tipoDeEnvio;
	private TPControl puntoControl;
	
	
	public TPedido(String emisor,int repartidor,boolean pagado,
			String receptor,String codigo,MetodoDePago metPago2,
			TSucursal SucursalSalida,TSucursal SucursalLlegada,
			TipoDeEnvio urgenciaPaquete,TPControl puntoControl)
	{
		this.codigo=codigo;
		this.repartidor=repartidor;
		this.emisor=emisor;
		this.metPago=metPago2;
		this.pagado=pagado;
		this.puntoControl=puntoControl;
		this.receptor=receptor;
		this.SucursalLlegada=SucursalLlegada;
		this.SucursalSalida=SucursalSalida;
		this.tipoDeEnvio=urgenciaPaquete;
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
	
	public void setCodigo(String codigo){
		this.codigo=codigo;
	}
	
	public void setMetPago(MetodoDePago metPago){
		this.metPago=metPago;
	}
	
	public void setSucursalSalida(TSucursal SucursalSalida){
		this.SucursalSalida=SucursalSalida;
	}
	
	public void setSucursalLlegada(TSucursal SucursalLlegada){
		this.SucursalLlegada=SucursalLlegada;
	
	}
	
	public void setTipoEnvio(TipoDeEnvio tipoDeEnvio){
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
	
	public String getCodigo(){
		return this.codigo;
	}
	
	public MetodoDePago getMetPago(){
		return this.metPago;
	}
	
	public TSucursal getSucursalSalida(){
		return this.SucursalSalida;
	}
	
	public TSucursal getSucursalLlegada(){
		return this.SucursalLlegada;
	}
	
	
	public TipoDeEnvio getTipoEnvio(){
		return this.tipoDeEnvio;
	}
	
	public TPControl getPControl(){
		return this.puntoControl;
	}
	
}//Class.
