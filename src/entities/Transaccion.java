package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controlador.ClienteController;
import controlador.CuentaController;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaccion implements BaseEntity<Transaccion> {
//Properties
	public Long id;
	public Date fechaTransaccion;
	public Double monto;
	public Long nroTransaccion;
	public Cuenta cuentaOrigen;
	public String tipoTransaccion;
	
	
//Constructor	
	public Transaccion(Long id, Date fechaTransaccion, Double monto, Long nroTransaccion, Cuenta cuentaOrigen, String tipoTransaccion) {
		super();
		this.id = id;
		this.fechaTransaccion = fechaTransaccion;
		this.monto = monto;
		this.nroTransaccion = nroTransaccion;
		this.cuentaOrigen = cuentaOrigen;
		this.tipoTransaccion=tipoTransaccion;
	}
	
	public Transaccion() {
		
	}

//Getters
	public Long getId() {
		return id;
	}

	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	public Double getMonto() {
		return monto;
	}

	public Long getNroTransaccion() {
		return nroTransaccion;
	}

	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}
	
	public String getTipoTransaccion() {
		return tipoTransaccion;
	}
//Setters
	public void setId(Long id) {
		this.id = id;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public void setNroTransaccion(Long nroTransaccion) {
		this.nroTransaccion = nroTransaccion;
	}

	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}
	
	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion=tipoTransaccion;
	}

//Methods
	public void imprimirComprobante() {
		//TODO enable function
	}
	
	@Override
	public void toEntity(ResultSet resultSet) {
		// TODO Auto-generated method stub
		CuentaController cc= new CuentaController();
		Cuenta c = new Cuenta();
		List<Cuenta> listaCuenta = new ArrayList<Cuenta>();
		try {
			this.setId(resultSet.getLong("id_transaccion"));
			this.setFechaTransaccion(resultSet.getDate("fecha_transaccion"));
			this.setTipoTransaccion(resultSet.getString("tipo_transaccion"));
			this.setMonto(resultSet.getDouble("monto"));
			this.setNroTransaccion(resultSet.getLong("nro_transaccion"));
			listaCuenta=cc.getById(resultSet.getLong("cuenta_id_cuenta"));
			c=listaCuenta.get(0);
			this.setCuentaOrigen(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public ResultSet toResultSet(Transaccion resultSet) {
		// TODO Auto-generated method stub
		return null;
	}
}
