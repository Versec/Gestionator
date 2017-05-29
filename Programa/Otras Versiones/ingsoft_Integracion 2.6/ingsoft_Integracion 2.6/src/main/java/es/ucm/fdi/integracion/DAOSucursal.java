package es.ucm.fdi.integracion;

import java.util.List;

import es.ucm.fdi.datos.BDMemoria;

public class DAOSucursal implements DAO<TSucursal> {

	private BDMemoria<TSucursal> BDSucursal;
	
	public DAOSucursal(BDMemoria<TSucursal> BDSucursal){
		this.BDSucursal = BDSucursal;
	}
	
	public void add(TSucursal sucursal, String cod) {
		// TODO Auto-generated method stub
		this.BDSucursal.insert(sucursal, cod);
	}

	public void eliminar(String id)
	{
		// TODO Auto-generated method stub
		this.BDSucursal.removeId(id);
	}


	public TSucursal leer(String Id) {
		// TODO Auto-generated method stub
		return this.BDSucursal.find(Id);
	}

	public List<TSucursal> leerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void actualizar(TSucursal datoActualizado) {
		// TODO Auto-generated method stub
		this.BDSucursal.insert(datoActualizado, datoActualizado.getID());
	}

}
