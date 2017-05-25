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
}
