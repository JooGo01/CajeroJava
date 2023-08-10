package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.Cuenta;
import mapper.BaseMapper;
import mapper.CuentaMapper;
import entities.Cliente;

public class CuentaController extends ControladorBase<Cuenta>{
	public CuentaController() {
		
	}
	
	protected List<Cuenta> getByParameters(Cuenta request, String query){
		List<Cuenta> response = new ArrayList<>();
		CuentaMapper mappeador = new CuentaMapper();
		try {
			PreparedStatement prepareStatement = ConectorBD.getInstance().getConnection().prepareStatement(query);
			if(request.getId()!=null) {
				prepareStatement.setLong(1, request.getId());
			}
			if(request.getOwner()!=null) {
				prepareStatement.setLong(1, request.getOwner().getId());
			}
			if(request.getNroCuenta()!=null) {
				prepareStatement.setString(1, request.getNroCuenta());
			}
			if(request.getBalance()!=null) {
				prepareStatement.setDouble(1, request.getBalance());
			}
			if(request.getTipoCuenta()!=null) {
				prepareStatement.setString(1, request.getTipoCuenta());
			}
			
			ResultSet resultado = prepareStatement.executeQuery();
			while(resultado.next()) {
				response.add(mappeador.toEntity(resultado));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public List<Cuenta> getById(Long id){
		Cuenta cuenta = new Cuenta();
		cuenta.setId(id);
		String query = "Select * from CUENTA where CUENTA.id_cuenta = ?";
		List <Cuenta> response = getByParameters(cuenta, query);
		return response;
	}
	
	public List<Cuenta> getByOwner(Cliente cliente){
		Cuenta cuenta = new Cuenta();
		cuenta.setOwner(cliente);
		String query = "Select * from CUENTA where CUENTA.cliente_id_cliente= ?";
		List<Cuenta> response = getByParameters(cuenta, query);
		return response;
	}
	
	public List<Cuenta> getByNroCuenta(String nroCuenta){
		Cuenta cuenta = new Cuenta();
		cuenta.setNroCuenta(nroCuenta);
		String query = "Select * from CUENTA where CUENTA.nro_cuenta = ?";
		List <Cuenta> response = getByParameters(cuenta, query);
		return response;
	}
	
	public List<Cuenta> getByBalance(Double balance){
		Cuenta cuenta = new Cuenta();
		cuenta.setBalance(balance);
		String query = "Select * from CUENTA where CUENTA.balance = ?";
		List <Cuenta> response = getByParameters(cuenta, query);
		return response;
	}
	
	public List<Cuenta> getByTipoCuenta(String tipoCuenta){
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuenta(tipoCuenta);
		String query = "Select * from CUENTA where CUENTA.tipo_cuenta = ?";
		List <Cuenta> response = getByParameters(cuenta, query);
		return response;
	}
	
	public List<Cuenta> readAll(){
		CuentaMapper mappeador = new CuentaMapper();
		List<Cuenta> cuenta = new ArrayList<Cuenta>();
		cuenta=readAll("SELECT * FROM CUENTA", mappeador);
		return cuenta;
	}
	
	@Override
	protected List readAll(String consulta, BaseMapper mapper) {
		List cuenta = new ArrayList<>();
		try {
			Statement statement = ConectorBD.getInstance().getConnection().createStatement();
			ResultSet resultado = statement.executeQuery(consulta);
			while(resultado.next()) {
				cuenta.add(mapper.toEntity(resultado));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cuenta;
	}
	
	public Integer insert(Cuenta request) {
		String query="INSERT INTO CUENTA(id_cuenta, cliente_id_cliente, nro_cuenta, balance, tipo_cuenta) VALUES (?,?,?,?,?)";
		return insert(request,query);
	}
	
	@Override
	
	protected Integer insert(Cuenta cuenta, String sql) {
		Integer response=0;
		try {
			PreparedStatement prepareStatement = ConectorBD.getInstance().getConnection().prepareStatement(sql);
			prepareStatement.setLong(1, cuenta.getId());
			prepareStatement.setLong(2, cuenta.getOwner().getId());
			prepareStatement.setString(3, cuenta.getNroCuenta());
			prepareStatement.setDouble(4,cuenta.getBalance());
			prepareStatement.setString(5, cuenta.getTipoCuenta());
			response=prepareStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return response;
	}
}
