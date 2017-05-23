package es.ucm.fdi.datos;

import java.util.Collection;

public class DAOEmpleadoImp implements DAOEmpleado{
	
	private BDMemoria<TEmpleado> BDEmpleados; 

	public DAOEmpleadoImp(){
		this.BDEmpleados = new BDMemoria<TEmpleado>();
	}
	
	public Integer crear(TEmpleado empleado, String id) {
		
		this.BDEmpleados.insert(empleado, id);
		return null;
	}

	public TEmpleado leer(String id) {
		
		return this.BDEmpleados.find(id);
	}

	public Collection<TEmpleado> leerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer actualizar(TEmpleado empleado) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer eliminar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
