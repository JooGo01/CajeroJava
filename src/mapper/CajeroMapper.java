package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controlador.ConectorBD;
import entities.Cajero;
import entities.Sucursal;
import controlador.SucursalController;

public class CajeroMapper implements BaseMapper<Cajero>{

	@Override
	public Cajero toEntity(ResultSet resultSet) {
		// TODO Auto-generated method stub
		Cajero resultado = new Cajero();
		List<Sucursal> sucursal = new ArrayList<>();
		SucursalController sc= new SucursalController();
		try {
			//faltan los .set
			resultado.setId(resultSet.getLong("id_cajero"));
			resultado.setUbicacion(resultSet.getString("ubicacion"));
			sucursal=sc.getById(resultSet.getLong("id_sucursal"));
			resultado.setSucursal(sucursal.get(0));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public PreparedStatement toPrepareStatement(Cajero t, String sql) {
		// TODO Auto-generated method stub
		try {
			//falta llenar el preparedstatement con los datos del sql y del controlador
			PreparedStatement prepareStatement=ConectorBD.getInstance().getConnection().prepareStatement(sql);
			prepareStatement.setLong(1, t.getId());
			prepareStatement.setString(2, t.getUbicacion());
			prepareStatement.setLong(3, t.getSucursal().getId());
			return prepareStatement;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
