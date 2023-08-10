package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controlador.ClienteController;
public class Cuenta implements BaseEntity<Cuenta> {

//Properties
	public Long id;
	public Cliente owner;
	public String nroCuenta;
	public Double balance;
	public String tipoCuenta;
	
//Constructor
	public Cuenta(Long id, Cliente owner, String nroCuenta, Double balance, String tipoCuenta) {
		super();
		this.id = id;
		this.owner = owner;
		this.nroCuenta = nroCuenta;
		this.balance = balance;
		this.tipoCuenta=tipoCuenta;
	}

	public Cuenta() {
		
	}
	
	
//Getters
public Long getId() {
		return id;
	}



	public Cliente getOwner() {
		return owner;
	}



	public String getNroCuenta() {
		return nroCuenta;
	}



	public Double getBalance() {
		return balance;
	}
	
	public String getTipoCuenta() {
		return tipoCuenta;
	}


//Setters
	public void setId(Long id) {
		this.id = id;
	}



	public void setOwner(Cliente owner) {
		this.owner = owner;
	}



	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}



	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta=tipoCuenta;
	}


//Methods
	
	public String consultarSaldo() {
		return "Saldo: "+balance;
	}
	
	public void entrada(Double monto) {
		this.setBalance(balance+monto);
	}
	
	public void salida(Double monto) {
		this.setBalance(balance-monto);
	}
	
	@Override
	public void toEntity(ResultSet resultSet) {
		// TODO Auto-generated method stub
		ClienteController cc= new ClienteController();
		Cliente c = new Cliente();
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		try {
			this.setId(resultSet.getLong("id_cuenta"));
			this.setNroCuenta(resultSet.getString("nro_cuenta"));
			this.setBalance(resultSet.getDouble("balance"));
			listaCliente=cc.getById(resultSet.getLong("cliente_id_cliente"));
			c=listaCliente.get(0);
			this.setOwner(c);
			this.setTipoCuenta(resultSet.getString("tipo_string"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public ResultSet toResultSet(Cuenta resultSet) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
