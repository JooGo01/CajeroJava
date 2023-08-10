package entities;

import java.sql.ResultSet;

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
		
	}


	@Override
	public ResultSet toResultSet(Cuenta resultSet) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
