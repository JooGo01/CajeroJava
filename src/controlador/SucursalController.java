package controlador;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Sucursal;
import mapper.BaseMapper;
import mapper.SucursalMapper;

public class SucursalController extends ControladorBase<Sucursal>{
	
	public SucursalController() {
		
	}
	
	protected List<Sucursal> getByParameters(Sucursal request, String query){
		List<Sucursal> response = new ArrayList<>();
		SucursalMapper mappeador = new SucursalMapper();
		try {
			PreparedStatement prepareStatement = ConectorBD.getInstance().getConnection().prepareStatement(query);
			if(request.getId()!=0) {
				prepareStatement.setLong(1, request.getId());
			}
			
			if(request.getCodigo()!=null) {
				prepareStatement.setString(2, request.getCodigo());
			}
			
			if(request.getDireccion()!=null) {
				prepareStatement.setString(3, request.getDireccion());
			}
			
			if(request.getNombre()!=null) {
				prepareStatement.setString(4, request.getNombre());
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
	
	public List<Sucursal> getById(Long id){
		Sucursal sucursal = new Sucursal();
		sucursal.setId(id);
		String query ="Select * from SUCURSAL where SUCURSAL.id_sucursal = ?";
		List<Sucursal> response = getByParameters(sucursal,query);
		return response;
	}
	
	public List<Sucursal> getByCodigo(String codigo){
		Sucursal sucursal = new Sucursal();
		sucursal.setCodigo(codigo);;
		String query ="Select * from SUCURSAL where SUCURSAL.codigo = ?";
		List<Sucursal> response = getByParameters(sucursal,query);
		return response;
	}
	
	public List<Sucursal> getByDireccion(String direccion){
		Sucursal sucursal = new Sucursal();
		sucursal.setDireccion(direccion);
		String query ="Select * from SUCURSAL where SUCURSAL.direccion = ?";
		List<Sucursal> response = getByParameters(sucursal,query);
		return response;
	}
	
	public List<Sucursal> getByNombre(String nombre){
		Sucursal sucursal = new Sucursal();
		sucursal.setDireccion(nombre);
		String query ="Select * from SUCURSAL where SUCURSAL.nombre = ?";
		List<Sucursal> response = getByParameters(sucursal,query);
		return response;
	}
	
	public List<Sucursal> readAll(){
		SucursalMapper mappeador= new SucursalMapper();
		List<Sucursal> sucursal = new ArrayList<Sucursal>();
		sucursal= readAll("SELECT * FROM SUCURSAL", mappeador);
		return sucursal;
	}
	
	@Override
	protected List readAll(String consulta, BaseMapper mapper) {
		List sucursal = new ArrayList<>();
		try {
			Statement statement = ConectorBD.getInstance().getConnection().createStatement();
			ResultSet resultado = statement.executeQuery(consulta);
			while(resultado.next()) {
				sucursal.add(mapper.toEntity(resultado));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sucursal;
	}
	
	public Integer insert (Sucursal request) {
		String query="INSERT INTO SUCURSAL(id_sucursal, codigo, direccion, nombre) VALUES (?,?,?,?)";
		return insert(request, query);
	}
	
	@Override
	protected Integer insert(Sucursal sucursal, String sql) {
		Integer response=0;
		try {
			PreparedStatement prepareStatement=ConectorBD.getInstance().getConnection().prepareStatement(sql);
			prepareStatement.setLong(1, sucursal.getId());
			prepareStatement.setString(2, sucursal.getCodigo());
			prepareStatement.setString(3, sucursal.getDireccion());
			prepareStatement.setString(4, sucursal.getNombre());
			response = prepareStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return response;
	}
}
