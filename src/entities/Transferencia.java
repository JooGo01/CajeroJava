package entities;

import java.util.Date;

public class Transferencia extends Transaccion {
		
	public Cuenta cuentaDestino;
	public String motivo;

	public Transferencia(Long id, Date fechaTransaccion, Double monto, Long nroTransaccion, Cuenta cuentaOrigen, Cuenta cuentaDestino, String motivo, String tipoTransaccion) {
		super(id, fechaTransaccion, monto, nroTransaccion, cuentaOrigen, tipoTransaccion);
		this.cuentaDestino = cuentaDestino;
		this.motivo = motivo;
	}
}
