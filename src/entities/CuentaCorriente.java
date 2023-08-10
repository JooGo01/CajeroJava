package entities;

public class CuentaCorriente extends Cuenta {

//Constructor
	public CuentaCorriente(Long id, Cliente owner, String nroCuenta, Double balance,String tipoCuenta) {
		super(id, owner, nroCuenta, balance, tipoCuenta);
	}

//Methods	
	private boolean checkSaldoNegativo(Double monto) {
		return monto < this.balance;
	}
	
	@Override
	public void salida(Double monto) {
		//TODO create exception.
		if(this.checkSaldoNegativo(monto)) {
			this.balance-=monto;
		}
	}
}
