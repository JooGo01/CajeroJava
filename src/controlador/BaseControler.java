package controlador;

import java.sql.ResultSet;
import java.sql.Statement;
import controlador.ConectorBD;

import entities.BaseEntity;
public class BaseControler {
	public BaseControler() {
		
	}
	
	public BaseEntity readFrom(String consulta, BaseEntity entidadBase) {
		try {
			Statement statement = ConectorBD.getInstance().getConnection().createStatement();

			ResultSet resultado = statement.executeQuery(consulta);

			while (resultado.next()) {
				entidadBase.toEntity(resultado);				 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entidadBase;
	}
	
	public void ejecutarUpdate(String consulta) {
		try {
			Statement statement = ConectorBD.getInstance().getConnection().createStatement();

			int filasAfectadas = statement.executeUpdate(consulta);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String buscarUltimoId(String consulta) {
		String ultimo_id="-1";
		try {
			Statement statement = ConectorBD.getInstance().getConnection().createStatement();

			ResultSet resultado = statement.executeQuery(consulta);
			System.out.println(consulta);
			while (resultado.next()) {
				ultimo_id=resultado.getString("ID");
			}
			if(ultimo_id==null) {
				ultimo_id="0";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ultimo_id;
	}
}