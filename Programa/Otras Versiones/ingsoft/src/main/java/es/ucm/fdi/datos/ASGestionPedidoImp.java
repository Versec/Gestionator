package es.ucm.fdi.datos;



public class ASGestionPedidoImp implements ASGestionPedido{

	private String emisor;
	private String receptor;
	private String sucursalOrigen;
	private String sucursalDestino;
	private int ID;
	private MetodoDePago mPago;
	private TipoDeEnvio tEnvio;
	private int codigo;
	
		
	public ASGestionPedidoImp(String emisor, String receptor, 
			String sucursalOrigen, String sucursalDestino, int ID, 
			MetodoDePago mPago,TipoDeEnvio tEnvio)
	{
		this.emisor = emisor;
		this.receptor = receptor;
		this.sucursalDestino = sucursalDestino;
		this.sucursalOrigen = sucursalOrigen;
		this.ID = ID;
		this.mPago = mPago;
		this.tEnvio = tEnvio;
		this.codigo = -1; //codigo vacio
		
		
	}
	
	public void BajaPedido() {
		// TODO Auto-generated method stub
		
	}

	public ASGestionPedido AltaPedido(String emisor, String receptor, String sucursalOrigen, String sucursalDestino, int ID, MetodoDePago mPago,TipoDeEnvio tEnvio)
	{
		// TODO Auto-generated method stub
		return new ASGestionPedidoImp(emisor,  receptor,sucursalOrigen, sucursalDestino, ID, mPago,tEnvio);
	}

	public String CodificacionPedido() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean pagarPedido() {
		// TODO Auto-generated method stub
		return false;
	}

	public void ActualizarPedido() {
		// TODO Auto-generated method stub
		
	}

	public void ValidarID() {
		// TODO Auto-generated method stub
		
	}

	public void ValidarDatos() {
		// TODO Auto-generated method stub
		
	}

	public double CalculoTarifa() {
		// TODO Auto-generated method stub
		
		return 0;
	}

	public void ValidarP_Control() {
		// TODO Auto-generated method stub
		
	}

	
	
}
