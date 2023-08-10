package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.Cliente;
import entities.Sucursal;
import mapper.BaseMapper;
import mapper.ClienteMapper;

public class ClienteController extends ControladorBase<Cliente>{
	public ClienteController() {
		
	}
	
	protected List<Cliente> getByParameters(Cliente request, String query){
		List<Cliente> response = new ArrayList<>();
		ClienteMapper mappeador = new ClienteMapper();
		try {
			PreparedStatement prepareStatement = ConectorBD.getInstance().getConnection().prepareStatement(query);
			if(request.getId()!=null) {
				prepareStatement.setLong(1, request.getId());
			}
			if(request.getNombre()!=null) {
				prepareStatement.setString(1, request.getNombre());
			}
			if(request.getDireccion()!=null) {
				prepareStatement.setString(1, request.getDireccion());
			}
			if(request.getNumeroTarjeta()!=null) {
				prepareStatement.setString(1, request.getNumeroTarjeta());
			}
			if(request.getContrasenia()!=null) {
				prepareStatement.setString(1, request.getContrasenia());
			}
			if(request.getUsuario()!=null) {
				prepareStatement.setString(1, request.getUsuario());
			}
			if(request.getPin()!=null) {
				prepareStatement.setInt(1, request.getPin());
			}
			
			ResultSet resultado = prepareStatement.executeQuery();
			while(resultado.next()) {
				response.add(mappeador.toEntity(resultado));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return response;
	}
	
	public List<Cliente> getById(Long id){
		Cliente cliente = new Cliente();
		cliente.setId(id);
		String query="Select * from CLIENTE where CLIENTE.id_cliente = ?";
		List<Cliente> response=getByParameters(cliente,query);
		return response;
	}
	
	public List<Cliente> getByNombre(String nombre){
		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		String query = "Select * from CLIENTE where CLIENTE.nombre = ?";
		List<Cliente> response = getByParameters(cliente, query);
		return response;
	}
	
	public List<Cliente> getByDireccion(String direccion){
		Cliente cliente = new Cliente();
		cliente.setDireccion(direccion);
		String query="Select * from CLIENTE where CLIENTE.direccion = ?";
		List<Cliente> response=getByParameters(cliente,query);
		return response;
	}
	
	public List<Cliente> getByNumeroTarjeta(String numeroTarjeta){
		Cliente cliente = new Cliente();
		cliente.setNumeroTarjeta(numeroTarjeta);
		String query = "Select * from CLIENTE where CLIENTE.numero_tarjeta = ?";
		List<Cliente> response = getByParameters(cliente, query);
		return response;
	}
	
	public List<Cliente> getByContrasenia(String contrasenia){
		Cliente cliente = new Cliente();
		cliente.setContrasenia(contrasenia);
		String query = "Select * from CLIENTE where CLIENTE.contrasenia = ?";
		List<Cliente> response = getByParameters(cliente, query);
		return response;
	}
	
	public List<Cliente> getByUsuario(String usuario){
		Cliente cliente = new Cliente();
		cliente.setUsuario(usuario);
		String query="Select * from CLIENTE where CLIENTE.usuario = ?";
		List<Cliente> response=getByParameters(cliente,query);
		return response;
	}
	
	public List<Cliente> getByPin(Integer pin){
		Cliente cliente = new Cliente();
		cliente.setPin(pin);
		String query = "Select * from CLIENTE where CLIENTE.pin = ?";
		List<Cliente> response = getByParameters(cliente, query);
		return response;
	}
	
	public List<Cliente> readAll(){
		ClienteMapper mappeador = new ClienteMapper();
		List<Cliente> cliente = new ArrayList<Cliente>();
		cliente=readAll("SELECT * FROM CLIENTE", mappeador);
		return cliente;
	}
	
	@Override
	
	protected List readAll(String consulta, BaseMapper mapper) {
		List cliente = new ArrayList<>();
		try {
			Statement statement = ConectorBD.getInstance().getConnection().createStatement();
			ResultSet resultado = statement.executeQuery(consulta);
			while(resultado.next()) {
				cliente.add(mapper.toEntity(resultado));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cliente;
	}
	
	public Integer insert(Cliente request) {
		String query="INSERT INTO CLIENTE(id_cliente, nombre, direccion, numero_tarjeta, contrasenia, usuario, pin) VALUES (?, ?, ?, ?, ?, ?, ?)";
		return insert(request, query);
	}
	
	@Override
	protected Integer insert(Cliente cliente, String sql) {
		Integer response=0;
		try {
			PreparedStatement prepareStatement = ConectorBD.getInstance().getConnection().prepareStatement(sql);
			prepareStatement.setLong(1, cliente.getId());
			prepareStatement.setString(2, cliente.getNombre());
			prepareStatement.setString(3, cliente.getDireccion());
			prepareStatement.setString(4, cliente.getNumeroTarjeta());
			prepareStatement.setString(5, cliente.getContrasenia());
			prepareStatement.setString(6, cliente.getUsuario());
			prepareStatement.setInt(7, cliente.getPin());
			response=prepareStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public Integer insert(Cliente request, Sucursal sucursal) {
		String query="INSERT INTO CLIENTE(id_cliente, nombre, direccion, numero_tarjeta, contrasenia, usuario, pin, sucursal_id_sucursal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		return insert(request, sucursal, query);
	}
	
	@Override
	protected Integer insert(Cliente cliente, Sucursal sucursal, String sql) {
		Integer response=0;
		try {
			PreparedStatement prepareStatement = ConectorBD.getInstance().getConnection().prepareStatement(sql);
			prepareStatement.setLong(1, cliente.getId());
			prepareStatement.setString(2, cliente.getNombre());
			prepareStatement.setString(3, cliente.getDireccion());
			prepareStatement.setString(4, cliente.getNumeroTarjeta());
			prepareStatement.setString(5, cliente.getContrasenia());
			prepareStatement.setString(6, cliente.getUsuario());
			prepareStatement.setInt(7, cliente.getPin());
			prepareStatement.setLong(8, sucursal.getId());
			response=prepareStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return response;
	}
}
