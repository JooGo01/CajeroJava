package entities;

public class CajaDeAhorro extends Cuenta {
	
	public CajaDeAhorro(Long id, Cliente owner, String nroCuenta, Double balance, String tipoCuenta) {
		super(id, owner, nroCuenta, balance, tipoCuenta);
	}
	
}
