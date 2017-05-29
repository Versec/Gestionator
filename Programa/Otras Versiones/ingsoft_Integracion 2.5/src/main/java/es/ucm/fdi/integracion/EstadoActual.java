package es.ucm.fdi.integracion;

public enum EstadoActual {
	NOENVIADO,
	REPARTO, 
	ENTREGADO;
	
	
	public static EstadoActual StringToEstadoActual(String s){
		EstadoActual state=null;
		if(s.equalsIgnoreCase("NOENVIADO"))
			state=NOENVIADO;
		else if(s.equalsIgnoreCase("REPARTO"))
			state=REPARTO;
		else if(s.equalsIgnoreCase("ENTREGADO"))
			state=ENTREGADO;
		
		return state;
	}
	
}
