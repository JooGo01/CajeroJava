package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controlador.ConectorBD;
import entities.Cuenta;
import entities.Cliente;
import controlador.ClienteController;

public class CuentaMapper implements BaseMapper<Cuenta>{

	@Override
	public Cuenta toEntity(ResultSet resultSet) {
		// TODO Auto-generated method stub
		Cuenta resultado = new Cuenta();
		List<Cliente> cliente= new ArrayList<>();
		ClienteController cc = new ClienteController();
		Cliente cli = new Cliente();
		
		try {
			//faltan los .set
			resultado.setId(resultSet.getLong("id_cuenta"));
			cliente=cc.getById(resultSet.getLong("cliente_id_cliente"));
			if(cliente.size()>0) {
				cli=cliente.get(0);
				resultado.setOwner(cli);
			}
			resultado.setNroCuenta(resultSet.getString("nro_cuenta"));
			resultado.setBalance(resultSet.getDouble("balance"));
			resultado.setTipoCuenta(resultSet.getString("tipo_cuenta"));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public PreparedStatement toPrepareStatement(Cuenta t, String sql) {
		// TODO Auto-generated method stub
		try {
			//falta llenar el preparedstatement con los datos del sql y del controlador
			PreparedStatement prepareStatement = ConectorBD.getInstance().getConnection().prepareStatement(sql);
			prepareStatement.setLong(1, t.getId());
			prepareStatement.setLong(2, t.getOwner().getId());
			prepareStatement.setString(3, t.getNroCuenta());
			prepareStatement.setDouble(4, t.getBalance());
			prepareStatement.setString(5, t.getTipoCuenta());
			return prepareStatement;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
