package es.ucm.fdi.integracion;

public enum Localizacion {

	SUCURSAL_INICIO,
	SUCURSAL_INTERMEDIA,
	SUCURSAL_LLEGADA;
	
	public static Localizacion StringToLocalizacion(String s){
		Localizacion loc=null;
		if(s.equalsIgnoreCase("SUCURSAL_INICIO"))
			loc=SUCURSAL_INICIO;
		else if(s.equalsIgnoreCase("SUCURSAL_INTERMEDIA"))
			loc=SUCURSAL_INTERMEDIA;
		else if(s.equalsIgnoreCase("SUCURSAL_LLEGADA"))
			loc=SUCURSAL_LLEGADA;
		
		return loc;
	}
}
