package controlador;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConectorBD {
	private Connection connection;

	private static ConectorBD instance;

	private ConectorBD() {
		crearConnecionInicial();
	}

	private void crearConnecionInicial() {
		String url = "jdbc:mysql://localhost:3463/mydbb";
		String usuario = "root";
		String contraseña = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(url, usuario, contraseña);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ConectorBD getInstance() {

		if (instance == null) {
			instance = new ConectorBD();
		}
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}
}
