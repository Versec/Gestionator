package es.ucm.fdi;

public class prueba {

	public static void main(String[] args) {
		MetodoPago metodo = new MetodoPago();
		
		if(metodo.validarFechaCaducidad("04/17")){
			System.out.println("ok");
	}

}}
