package mapper;
import entities.Sucursal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controlador.ConectorBD;

public class SucursalMapper implements BaseMapper<Sucursal>{

	@Override
	public Sucursal toEntity(ResultSet resultSet) {
		Sucursal resultado = new Sucursal();
		try {
			//faltan los .set
			resultado.setId(resultSet.getLong("id_sucursal"));
			resultado.setCodigo(resultSet.getString("codigo"));
			resultado.setDireccion(resultSet.getString("direccion"));
			resultado.setNombre(resultSet.getString("nombre"));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public PreparedStatement toPrepareStatement(Sucursal t, String sql) {
		// TODO Auto-generated method stub
		try {
			//falta llenar el preparedstatement con los datos del sql y del controlador
			PreparedStatement prepareStatement = ConectorBD.getInstance().getConnection().prepareStatement(sql);
			prepareStatement.setLong(1, t.getId());
			prepareStatement.setString(2, t.getCodigo());
			prepareStatement.setString(3, t.getDireccion());
			prepareStatement.setString(4, t.getNombre());
			return prepareStatement;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
