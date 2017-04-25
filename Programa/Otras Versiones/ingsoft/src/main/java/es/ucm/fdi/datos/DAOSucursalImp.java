package es.ucm.fdi.datos;

import java.util.Collection;

public class DAOSucursalImp implements DAOSucursal{
	
	private BDMemoria<TSucursal> BDSucusal = new BDMemoria<TSucursal>();
	

	public Integer crear(TSucursal sucursal, String id) {
		this.BDSucusal.insert(sucursal, id);
		return null;
	}

	public TSucursal leer(String id) {
		this.BDSucusal.find(id);
		return null;
	}

	public Collection<TSucursal> leerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer actualizar(TSucursal sucursal) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer eliminar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public TSucursal leer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
