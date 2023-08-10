package entities;

import java.util.Scanner;

public class Vista {
	
	SistemaCajero sc = new SistemaCajero();
	public void vistaConsulta(Cuenta c) {
		System.out.println("===Consultar saldo===");
		System.out.println(c.consultarSaldo());
	}
	
	public void vistaTransacciones(Cuenta c, Double monto) {
		Scanner scanner = new Scanner(System.in);
		boolean continua;
		Integer opcion;
		do {
			continua = true;
			System.out.println("1. Transferencia");
			System.out.println("2. Deposito");
			System.out.println("3. Retiro");
			System.out.print("Seleccione una opción: ");
			opcion = scanner.nextInt();
			switch (opcion) {
				case 1:
					System.out.println("==Transferencia==");
					Cuenta cuentaDestino = new Cuenta();
					cuentaDestino=sc.seleccionarCuentaDestino();
					sc.transferir(c, cuentaDestino, monto);
					continua = false;
					break;
				case 2:
					System.out.println("==Deposito de efectivo==");
					sc.depositar(monto, c);
					continua = false;
					break;
				case 3:
					System.out.println("==Retiro de efectivo==");
					sc.extraer(monto, c);
					continua = false;
					break;
				default:
					System.out.println("Opción inválida. Selecciona una opción válida.");
					break;
			}
		} while (continua);
	}
	
	public void vistaPin(Cliente cl, Integer pin) {
		System.out.println("==Cambiar Pin==");
		if(sc.validatePin(pin)) {
			sc.cambiarPin(cl, pin);
			System.out.println("Se pudo realizar el cambio del pin");
		}else{
			System.out.println("No se pudo realizar el cambio del pin vuelva a intentar");
		}
	}
	
	public void vistaCerrarSesion(Session session) {
		if(session!=null) {
			sc.cerrarSesion(session);
			System.out.println("Sesion cerrada, hasta luego!");
		}
	}
	
	public void vistaCrearUsuario(String nombre, String direccion, String contrasenia, String usuario, Integer pin) {
		sc.creacionCliente(nombre, direccion, contrasenia, usuario, pin);
	}
}
