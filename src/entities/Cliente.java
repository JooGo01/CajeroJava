package entities;

import java.sql.ResultSet;

public class Cliente implements BaseEntity<Cliente>{

//Propiedades
	public Long id;
	public String nombre;
	public String direccion;
	private String numeroTarjeta;
	private String contrasenia;
	private String usuario;
	private Integer pin;
	public Long getId() {
		return id;
	}

//Constructor
	public Cliente(Long id, String nombre, String direccion, String numeroTarjeta, String contrasenia, String usuario, Integer pin) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.numeroTarjeta = numeroTarjeta;
		this.contrasenia = contrasenia;
		this.usuario = usuario;
		this.pin = pin;
	}
	
//Getters
	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public String getUsuario() {
		return usuario;
	}
	public Integer getPin() {
		return pin;
	}
	
//Setters
	public void setId(Long id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public void setContrasenia(String contrasenia) {	
			this.contrasenia = contrasenia;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public void setPin(Integer pin) {
		this.pin = pin;
	}
	
	
//Methods
	public boolean verificarContrasenia(String contrasenia) {
		boolean valid = false;
		if(this.contrasenia.length() < 8) {
			System.out.println("Contraseña demasiado corta intente nuevamente."); 
		} else {
			System.out.println("Contraseña válida");
			valid = true;
		}
		
		return valid;
	}

	@Override
	public void toEntity(ResultSet resultSet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet toResultSet(Cliente resultSet) {
		// TODO Auto-generated method stub
		return null;
	}
}
