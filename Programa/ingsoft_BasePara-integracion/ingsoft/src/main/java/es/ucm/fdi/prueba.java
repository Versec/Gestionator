package es.ucm.fdi;

public class prueba {

	public static void main(String[] args) {
		MetodoPago metodo = new MetodoPago();
		
		if(metodo.validarTarjeta("11111111")){
			System.out.println("ok");
	}

}}
