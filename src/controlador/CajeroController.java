package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.Cajero;
import entities.Sucursal;
import mapper.BaseMapper;
import mapper.CajeroMapper;

public class CajeroController extends ControladorBase<Cajero>{
	
	public CajeroController() {
	}
	
	protected List<Cajero> getByParameters(Cajero request, String query){
		List<Cajero> response = new ArrayList<>();
		CajeroMapper mappeador= new CajeroMapper();
		try {
			PreparedStatement prepareStatement = ConectorBD.getInstance().getConnection().prepareStatement(query);
			if(request.getId()!=0) {
				prepareStatement.setLong(1, request.getId());
			}
			
			if(request.getUbicacion()!=null) {
				prepareStatement.setString(3, request.getUbicacion());
			}
			
			if(request.getSucursal()!=null) {
				prepareStatement.setObject(2, request.getSucursal());
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
	
	public List<Cajero> getById(Long id){
		Cajero cajero= new Cajero();
		cajero.setId(id);
		String query ="Select * from CAJERO where CAJERO.id_cajero = ?";
		List<Cajero> response = getByParameters(cajero,query);
		return response;
	}
	
	public List<Cajero> getByUbicacion(String ubicacion){
		Cajero cajero=new Cajero();
		cajero.setUbicacion(ubicacion);
		String query ="Select * from CAJERO where CAJERO.ubicacion = ?";
		List<Cajero> response = getByParameters(cajero,query);
		return response;
	}
	
	public List<Cajero> getBySucursal(Sucursal sucursal){
		Cajero cajero=new Cajero();
		cajero.setSucursal(sucursal);
		String query ="Select * from CAJERO where CAJERO.id_sucursal = ?";
		List<Cajero> response = getByParameters(cajero,query);
		return response;
	}
	
	public List<Cajero> readAll(){
		CajeroMapper mappeador = new CajeroMapper();
		List<Cajero> cajero=new ArrayList<Cajero>();
		cajero=readAll("SELECT * FROM CAJERO", mappeador);
		return cajero;
	}
	
	@Override
	protected List readAll(String consulta, BaseMapper mapper) {
		List cajero = new ArrayList<>();
		try {
			Statement statement = ConectorBD.getInstance().getConnection().createStatement();
			ResultSet resultado = statement.executeQuery(consulta);
			while(resultado.next()) {
				cajero.add(mapper.toEntity(resultado));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cajero;
	}
	
	public Integer insert (Cajero request) {
		String query="INSERT INTO CAJERO(id_cajero, id_sucursal, ubicacion) VALUES (?,?,?)";
		return insert(request, query);
	}
	
	@Override
	protected Integer insert(Cajero cajero, String sql) {
		Integer response=0;
		try {
			PreparedStatement prepareStatement=ConectorBD.getInstance().getConnection().prepareStatement(sql);
			prepareStatement.setLong(1, cajero.getId());
			prepareStatement.setString(3, cajero.getUbicacion());
			prepareStatement.setLong(2, cajero.getSucursal().getId());
			response = prepareStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return response;
	}
	
}
