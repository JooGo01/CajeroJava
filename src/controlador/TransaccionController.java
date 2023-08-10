package controlador;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Transaccion;
import entities.Cuenta;
import mapper.BaseMapper;
import mapper.TransaccionMapper;

public class TransaccionController extends ControladorBase<Transaccion>{
	public TransaccionController() {
		
	}
	
	protected List<Transaccion> getByParameters(Transaccion request, String query){
		List<Transaccion> response= new ArrayList<>();
		TransaccionMapper mappeador = new TransaccionMapper();
		try {
			PreparedStatement prepareStatement = ConectorBD.getInstance().getConnection().prepareStatement(query);
			if(request.getId()!=0) {
				prepareStatement.setLong(1, request.getId());
			}
			
			if(request.getFechaTransaccion()!=null) {
				prepareStatement.setDate(2, (java.sql.Date) request.getFechaTransaccion());
			}
			
			if(request.getMonto()!=0) {
				prepareStatement.setDouble(3, request.getMonto());
			}
			
			if(request.getNroTransaccion()!=null) {
				prepareStatement.setLong(4, request.getNroTransaccion());
			}
			
			if(request.getCuentaOrigen()!=null) {
				prepareStatement.setLong(5, request.getCuentaOrigen().getId());
			}
			
			if(request.getTipoTransaccion()!=null) {
				prepareStatement.setString(6, request.getTipoTransaccion());
			}
			
			ResultSet resultado=prepareStatement.executeQuery();
			while(resultado.next()) {
				response.add(mappeador.toEntity(resultado));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public List<Transaccion> getById(Long id){
		Transaccion transaccion = new Transaccion();
		transaccion.setId(id);
		String query = "Select * from TRANSACCIONES where TRANSACCIONES.id_transaccion = ?";
		List<Transaccion> response = getByParameters(transaccion, query);
		return response;
	}
	
	public List<Transaccion> getByFechaTransaccion(Date fechaTransaccion){
		Transaccion transaccion = new Transaccion();
		transaccion.setFechaTransaccion(fechaTransaccion);
		String query="Select * from TRANSACCIONES where TRANSACCIONES.fecha_transaccion = ?";
		List<Transaccion> response = getByParameters(transaccion, query);
		return response;
	}
	
	public List<Transaccion> getByMonto(Double monto){
		Transaccion transaccion = new Transaccion();
		transaccion.setMonto(monto);
		String query = "Select * from TRANSACCIONES where TRANSACCIONES.monto = ?";
		List<Transaccion> response = getByParameters(transaccion, query);
		return response;
	}
	
	public List<Transaccion> getByNroTransaccion(Long nroTransaccion){
		Transaccion transaccion = new Transaccion();
		transaccion.setNroTransaccion(nroTransaccion);
		String query = "Select * from TRANSACCIONES where TRANSACCIONES.nro_transaccion = ?";
		List<Transaccion> response = getByParameters(transaccion, query);
		return response;
	}
	
	public List<Transaccion> getByCuentaOrigen(Cuenta cuentaOrigen){
		Transaccion transaccion = new Transaccion();
		transaccion.setCuentaOrigen(cuentaOrigen);
		String query = "Select * from TRANSACCIONES where TRANSACCIONES.cuenta_id_cuenta = ?";
		List<Transaccion> response = getByParameters(transaccion, query);
		return response;
	}
	
	public List<Transaccion> getByTipoTransaccion(String tipoTransaccion){
		Transaccion transaccion = new Transaccion();
		transaccion.setTipoTransaccion(tipoTransaccion);
		String query = "Select * from TRANSACCIONES where TRANSACCIONES.tipo_transaccion = ?";
		List<Transaccion> response = getByParameters(transaccion, query);
		return response;
	}
	
	public List<Transaccion> readAll(){
		TransaccionMapper mappeador = new TransaccionMapper();
		List<Transaccion> transaccion = new ArrayList<Transaccion>();
		transaccion=readAll("SELECT * FROM TRANSACCIONES", mappeador);
		return transaccion;
	}
	
	@Override
	
	protected List readAll(String consulta, BaseMapper mapper) {
		List transaccion = new ArrayList<>();
		try {
			Statement statement= ConectorBD.getInstance().getConnection().createStatement();
			ResultSet resultado = statement.executeQuery(consulta);
			while(resultado.next()) {
				transaccion.add(mapper.toEntity(resultado));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return transaccion;
	}
	
	public Integer insert(Transaccion request) {
		String query = "INSERT INTO TRANSACCION(id_transaccion, fecha_transaccion, monto, nro_transaccion, cuenta_id_cuenta, tipo_transaccion) VALUES (?,?,?,?,?,?)";
		return insert(request, query);
	}
	
	@Override
	protected Integer insert(Transaccion transaccion, String sql) {
		Integer response=0;
		try {
			PreparedStatement prepareStatement=ConectorBD.getInstance().getConnection().prepareStatement(sql);
			prepareStatement.setLong(1, transaccion.getId());
			prepareStatement.setDate(2, (java.sql.Date) transaccion.getFechaTransaccion());
			prepareStatement.setDouble(3, transaccion.getMonto());
			prepareStatement.setLong(4, transaccion.getNroTransaccion());
			prepareStatement.setLong(5, transaccion.getCuentaOrigen().getId());
			prepareStatement.setString(6, transaccion.getTipoTransaccion());
			System.out.println(prepareStatement);
			response=prepareStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return response;
	}
}
