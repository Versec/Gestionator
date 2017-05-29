package es.ucm.fdi.integracion;

public enum TipoEnvio {
	URGENTE,
	NORMAL;
	
	
	public static TipoEnvio parsearTipoEnvio(int n)
	{
		TipoEnvio modo;
		switch(n)
		{
			case 1:
			modo = TipoEnvio.NORMAL;
			break;
		case 2:
			modo = TipoEnvio.URGENTE;
			break;
		default: 
			modo = null;
		}
	
	return modo;
	}
	
	
	public static TipoEnvio stringToPago(String s) throws Exception{
		TipoEnvio modo=null;
		
		if(s.equalsIgnoreCase("NORMAL")){
			modo = TipoEnvio.NORMAL;
		}
		else if(s.equalsIgnoreCase("URGENTE")){
			modo = TipoEnvio.URGENTE;
		}
		else
			throw new Exception("Error al leer el archivo, formato de pago no valido. ");
	
	return modo;
	}
	
	
}
