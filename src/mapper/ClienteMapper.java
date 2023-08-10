package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entities.Cliente;
import controlador.ConectorBD;

public class ClienteMapper implements BaseMapper<Cliente>{

	@Override
	public Cliente toEntity(ResultSet resultSet) {
		// TODO Auto-generated method stub
		Cliente resultado=new Cliente();
		try {
			resultado.setId(resultSet.getLong("id_cliente"));
			resultado.setNombre(resultSet.getString("nombre"));
			resultado.setDireccion(resultSet.getString("direccion"));
			resultado.setNumeroTarjeta(resultSet.getString("numero_tarjeta"));
			resultado.setContrasenia(resultSet.getString("contrasenia"));
			resultado.setUsuario(resultSet.getString("usuario"));
			resultado.setPin(resultSet.getInt("pin"));
			//faltan los .set
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public PreparedStatement toPrepareStatement(Cliente t, String sql) {
		// TODO Auto-generated method stub
		try {
			//falta llenar el preparedstatement con los datos del sql y del controlador
			PreparedStatement prepareStatement = ConectorBD.getInstance().getConnection().prepareStatement(sql);
			prepareStatement.setLong(1, t.getId());
			prepareStatement.setString(2, t.getNombre());
			prepareStatement.setString(3, t.getDireccion());
			prepareStatement.setString(4, t.getNumeroTarjeta());
			prepareStatement.setString(5, t.getContrasenia());
			prepareStatement.setString(6, t.getUsuario());
			prepareStatement.setInt(7, t.getPin());
			return prepareStatement;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
