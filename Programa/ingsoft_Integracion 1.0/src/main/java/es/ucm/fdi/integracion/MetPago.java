package es.ucm.fdi.integracion;

public enum MetPago {
	
	TRASFERNCIA,
	CONTRA_REEMBOLSO,
	EFECTIVO;
	
	public static MetPago parsearPago(int n)
	{
		MetPago modo;
		switch(n)
		{
			case 1:
			modo = MetPago.TRASFERNCIA;
			break;
		case 2:
			modo = MetPago.CONTRA_REEMBOLSO;
			break;
		case 3:
			modo = MetPago.EFECTIVO;
		default: 
			modo = null;
		}
	
	return modo;
	}
}