package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controlador.SucursalController;

public class Cajero implements BaseEntity<Cajero>{

//Propiedades
	public Long id;
	public String ubicacion;
	public Sucursal sucursal;
	
//Constructor
	public Cajero(Long id, String ubicacion, Sucursal sucursal) {
		super();
		this.id = id;
		this.ubicacion = ubicacion;
		this.sucursal = sucursal;
	}
	
	public Cajero() {
		
	}

//Getters
	public Long getId() {
		return id;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

//Setters
	public void setId(Long id) {
		this.id = id;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	@Override
	public void toEntity(ResultSet resultSet) {
		// TODO Auto-generated method stub
		SucursalController sc= new SucursalController();
		Sucursal s = new Sucursal();
		List<Sucursal> listaSucursal = new ArrayList<Sucursal>();
		try {
			this.setId(resultSet.getLong("id_cajero"));
			listaSucursal=sc.getById(resultSet.getLong("sucursal_id_sucursal"));
			s=listaSucursal.get(0);
			this.setUbicacion(resultSet.getString("ubicacion"));
			this.setSucursal(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet toResultSet(Cajero resultSet) {
		// TODO Auto-generated method stub
		return null;
	}
		
	
}
