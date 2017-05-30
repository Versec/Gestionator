package es.ucm.fdi.integracion;

public enum MetPago {
	
	TRANSFERENCIA,
	CONTRA_REEMBOLSO,
	EFECTIVO;
	
	public static MetPago parsearPago(int n)
	{
		MetPago modo;
		switch(n)
		{
			case 1:
			modo = MetPago.EFECTIVO;
			break;
		case 2:
			modo = MetPago.CONTRA_REEMBOLSO;
			break;
		case 3:
			modo = MetPago.TRANSFERENCIA;
			break;
		default: 
			modo = null;
		}
	
	return modo;
	}
	
	
	public static MetPago stringToPago(String s) throws Exception{
		MetPago modo=null;
		
		if(s.equalsIgnoreCase("TRANSFERENCIA")){
			modo = MetPago.TRANSFERENCIA;
		}
		else if(s.equalsIgnoreCase("CONTRA_REEMBOLSO")){
			modo = MetPago.CONTRA_REEMBOLSO;
		}
		else if(s.equalsIgnoreCase("EFECTIVO")){
			modo = MetPago.EFECTIVO;
		}
		else
			throw new Exception("Error al leer el archivo, formato de pago no valido. ");
		
		
		
	return modo;
	}
	
	
}