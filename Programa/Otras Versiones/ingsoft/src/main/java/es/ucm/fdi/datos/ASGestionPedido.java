package es.ucm.fdi.datos;

public interface ASGestionPedido
{
	public void BajaPedido();
	public ASGestionPedido AltaPedido(String emisor, String receptor, String sucursalOrigen, String sucursalDestino, int ID, MetodoDePago mPago,TipoDeEnvio tEnvio);
	public String CodificacionPedido(); //Devuelve un codigo
	public boolean pagarPedido();
	public void ActualizarPedido();
	public void ValidarID();
	public void ValidarP_Control();
	public void ValidarDatos();
	public double CalculoTarifa();

	
	
	
}
