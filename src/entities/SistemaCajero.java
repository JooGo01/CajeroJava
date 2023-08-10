package entities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import controlador.*;
import entities.*;

public class SistemaCajero {
	ClienteController cc = new ClienteController();
	TransaccionController tc= new TransaccionController();
	BaseControler controladorBase = new BaseControler();
	public Session iniciarSesion(String usuario, String contrasenia) {
    	Cliente cliente = new Cliente();
    	Session session = new Session();
    	cliente = (Cliente) controladorBase.readFrom("SELECT * FROM CLIENTE WHERE USUARIO='" +  usuario + "' AND CONTRASENIA='" + contrasenia + "'", cliente);
    	System.out.println(cliente.getId());
		if(cliente.getId()!=null) {
			session.crearSesion(cliente);
			return session;
		}else {
			return null;
		}
	}
	
	public void cerrarSesion(Session sesion) {
		sesion=null;
		if (sesion==null) {
			System.out.println("sesion terminada");
		}
	}
	
	public void cambiarPin(Cliente cliente, Integer pin) {
		long id_cliente;
		id_cliente=(long)cliente.getId();
		controladorBase.ejecutarUpdate("UPDATE CLIENTE SET PIN=" + pin + " WHERE ID_CLIENTE=" + id_cliente);
	}
	
	public boolean validatePin(Integer pin) {
		boolean validado=false;
		if(pin>=1000 && pin<=9999) {
			validado=true;
		}
		return validado;
	}
	
	public void depositar(Double monto, Cuenta cuenta) {
		Long numero_transaccion;
		Long id_transaccion;
		Date fechaActual = new Date();
		java.sql.Date sqlDate = new java.sql.Date(fechaActual.getTime());
		Random random = new Random();
        Long tokenNumber = random.nextLong(90000000) + 10000000;
        numero_transaccion=tokenNumber;
        id_transaccion=getLastIdTransaccion()+1;
        DepositoEfectivo de = new DepositoEfectivo(id_transaccion, sqlDate, monto, numero_transaccion, cuenta, "Deposito");
        controladorBase.ejecutarUpdate("UPDATE CUENTA SET BALANCE=" + (cuenta.getBalance()+monto) + " WHERE ID_CUENTA=" + cuenta.getId());
        tc.insert(de);
	}
	
	public void extraer(Double monto, Cuenta cuenta) {
		Long numero_transaccion;
		Long id_transaccion;
		Date fechaActual = new Date();
		java.sql.Date sqlDate = new java.sql.Date(fechaActual.getTime());
		Random random = new Random();
        Long tokenNumber = random.nextLong(90000000) + 10000000;
        numero_transaccion=tokenNumber;
        id_transaccion=getLastIdTransaccion()+1;
        RetiroEfectivo re = new RetiroEfectivo(id_transaccion, sqlDate, monto, numero_transaccion, cuenta, "Retiro");
        controladorBase.ejecutarUpdate("UPDATE CUENTA SET BALANCE=" + (cuenta.getBalance()-monto) + " WHERE ID_CUENTA=" + cuenta.getId());
        tc.insert(re);
	}
	
	public void transferir(Cuenta cuentaOrigen, Cuenta cuentaDestino, Double monto) {
		Long numero_transaccion;
		Long id_transaccion;
		Date fechaActual = new Date();
		java.sql.Date sqlDate = new java.sql.Date(fechaActual.getTime());
		Random random = new Random();
        Long tokenNumber = random.nextLong(90000000) + 10000000;
        numero_transaccion=tokenNumber;
        id_transaccion=getLastIdTransaccion()+1;
        Transferencia tforig_dest= new Transferencia(id_transaccion, sqlDate, monto*-1, tokenNumber, cuentaOrigen, cuentaDestino, "Envia Transferencia", "Transferencia");
        tc.insert(tforig_dest);
        controladorBase.ejecutarUpdate("UPDATE CUENTA SET BALANCE=" + (cuentaOrigen.getBalance()-monto) + " WHERE ID_CUENTA=" + cuentaOrigen.getId());
        id_transaccion=getLastIdTransaccion()+1;
        Transferencia tfdest_orig= new Transferencia(id_transaccion, sqlDate, monto, tokenNumber, cuentaDestino, cuentaOrigen, "Recibe Transferencia", "Transferencia");
        controladorBase.ejecutarUpdate("UPDATE CUENTA SET BALANCE=" + (cuentaDestino.getBalance()+monto) + " WHERE ID_CUENTA=" + cuentaDestino.getId());
        tc.insert(tfdest_orig);
	}
	
	private Long getLastIdTransaccion() {
		String ultimo_id=controladorBase.buscarUltimoId("SELECT MAX(ID_TRANSACCION) AS ID FROM TRANSACCION");
		return Long.parseLong(ultimo_id);
	}
	
	public Cuenta seleccionarCuenta(Cliente cliente) {
		Cuenta cuenta = new Cuenta();
		CuentaController cc= new CuentaController();
		List<Cuenta> listaCuentas= new ArrayList<Cuenta>();
		Scanner scanner = new Scanner(System.in);
		int opcion;
		listaCuentas=cc.getByOwner(cliente);
		if(listaCuentas.size()>0) {
			for (int i=0;i<listaCuentas.size();i++) {
				System.out.println(i + " - Nro Cuenta: " + listaCuentas.get(i).getNroCuenta());
			}
			System.out.print("Selecciona una cuenta: ");
			opcion = scanner.nextInt();
			do {
				cuenta=listaCuentas.get(opcion);
			}while(opcion<0 || opcion>=listaCuentas.size());
			return cuenta;
		}else {
			return null;
		}
	}
	
	public Cuenta seleccionarCuentaDestino() {
		Cuenta cuenta = new Cuenta();
		CuentaController sc= new CuentaController();
		List<Cuenta> listaCuentas= new ArrayList<Cuenta>();
		listaCuentas=sc.readAll();
		Scanner scanner = new Scanner(System.in);
		int opcion;
		if(listaCuentas.size()>0) {
			for (int i=0;i<listaCuentas.size();i++) {
				System.out.println(i + " - Nro Cuenta: " + listaCuentas.get(i).getNroCuenta());
			}
			System.out.print("Selecciona una cuenta destino a transferir: ");
			opcion = scanner.nextInt();
			do {
				cuenta=listaCuentas.get(opcion);
			}while(opcion<0 || opcion>=listaCuentas.size());
			return cuenta;
		}else {
			return null;
		}
	}
	
	public Sucursal seleccionarSucursal() {
		Sucursal sucursal = new Sucursal();
		SucursalController sc= new SucursalController();
		List<Sucursal> listaSucursal= new ArrayList<Sucursal>();
		listaSucursal=sc.readAll();
		Scanner scanner = new Scanner(System.in);
		int opcion;
		if(listaSucursal.size()>0) {
			for (int i=0;i<listaSucursal.size();i++) {
				System.out.println(i + " - Direccion: " + listaSucursal.get(i).getDireccion() + " - Nombre: " + listaSucursal.get(i).getNombre());
			}
			System.out.print("Selecciona una sucursal: ");
			opcion = scanner.nextInt();
			do {
				sucursal=listaSucursal.get(opcion);
			}while(opcion<0 || opcion>=listaSucursal.size());
			return sucursal;
		}else {
			return null;
		}
	}
	
	public void creacionCuentas(Cliente c) {
		CuentaController cc = new CuentaController();
		Cuenta cuenta = new Cuenta();
		String ultimo_id=controladorBase.buscarUltimoId("SELECT MAX(ID_CUENTA) AS ID FROM CUENTA");
		cuenta.setBalance((double) 0);
		cuenta.setId(Long.parseLong(ultimo_id)+1);
		//que se genere un random aca
		cuenta.setNroCuenta("11112222");
		cuenta.setOwner(c);
		cuenta.setTipoCuenta("Caja de Ahorro");
		cc.insert(cuenta);
		ultimo_id=controladorBase.buscarUltimoId("SELECT MAX(ID_CUENTA) AS ID FROM CUENTA");
		cuenta.setBalance((double) 0);
		cuenta.setId(Long.parseLong(ultimo_id)+1);
		//que se genere un random aca
		cuenta.setNroCuenta("11112223");
		cuenta.setOwner(c);
		cuenta.setTipoCuenta("Cuenta Corriente");
		cc.insert(cuenta);
	}
	
	public void creacionCliente(String nombre, String direccion, String contrasenia, String usuario, Integer pin) {
		String numero_tarjeta;
		numero_tarjeta="11112222";
		String ultimo_id=controladorBase.buscarUltimoId("SELECT MAX(ID_CLIENTE) AS ID FROM CLIENTE");
		//que numero_tarjeta tambien sea random
		Cliente cliente = new Cliente();
		ClienteController cc = new ClienteController();
		cliente.setId(Long.parseLong(ultimo_id)+1);
		cliente.setDireccion(direccion);
		cliente.setUsuario(usuario);
		cliente.setContrasenia(contrasenia);
		cliente.setPin(pin);
		cc.insert(cliente);
		creacionCuentas(cliente);
	}
}
