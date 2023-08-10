package entities;

import java.util.Random;

public class Session {
	private Cliente cliente;
	private String token;
	
	public Session(Cliente cliente, String token) {
		super();
		this.cliente = cliente;
		this.token = token;
	}
	public Session() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public void crearSesion(Cliente cliente) {
		Random random = new Random();
        Integer tokenNumber = random.nextInt(90000000) + 10000000;
        this.setToken(String.valueOf(tokenNumber));
        this.cliente=cliente;
	}
	
}
