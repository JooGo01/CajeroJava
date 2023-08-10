package test;
import entities.*;
import java.util.Scanner;
import controlador.*;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SistemaCajero sc = new SistemaCajero();
		Vista v = new Vista();
		Session s = new Session();
		Boolean sessionCuenta;
		Cuenta cuenta = new Cuenta();
		Scanner scanner = new Scanner(System.in);
		sessionCuenta=true;
		do {
			int opcionLogin;
			System.out.println("Bienvenido!");
			System.out.println("1. Crear cuenta");
			System.out.println("2. Ingresar");
			System.out.println("3. Salir");
			opcionLogin=scanner.nextInt();
			switch (opcionLogin) {
				case 1:
					//aca falta pedir que ingrese los datos correspondientes para crear el cliente y las cuentas
					//v.vistaCrearUsuario(nombre, direccion, contrasenia, usuario, pin) esta es la funcion a llamar
				case 2:
					//aca quedaria pedir el usuario y contraseña para poder iniciar la sesion
					s=sc.iniciarSesion("jgomez", "123");
					if(s!=null) {
						boolean continua;
						Integer opcion;
						do {
							continua = true;
							System.out.println("1. Realizar una transaccion");
							System.out.println("2. Cambiar de PIN");
							System.out.println("3. Consultar saldo de Cuenta");
							System.out.println("4. Finalizar la sesion");
							System.out.print("Seleccione una opción: ");
							opcion = scanner.nextInt();
							switch (opcion) {
								case 1:
										Double montoTransaccion;
										System.out.println("=== Menú de transacciones ===");
										cuenta=sc.seleccionarCuenta(s.getCliente());
										System.out.println("Ingrese el monto de transaccion: ");
										montoTransaccion=scanner.nextDouble();
										v.vistaTransacciones(cuenta, montoTransaccion);
										continua = false;
										break;
								case 2:
										System.out.println("Ingrese pin(4 digitos entre 1000 y 9999): ");
										Integer pin; 
										pin=scanner.nextInt();
										v.vistaPin(s.getCliente(), pin);
										continua = false;
										break;
								case 3:
									cuenta=sc.seleccionarCuenta(s.getCliente());
									v.vistaConsulta(cuenta);
									continua = false;
									break;
								case 4:
									v.vistaCerrarSesion(s);
									opcionLogin=4;
									continua = false;
									break;
								default:
									System.out.println("Opción inválida. Selecciona una opción válida.");
									break;
							}
						} while (continua);
						sessionCuenta=false;
					}else {
						//el usuario no existe
					}
					break;
				case 3:
					sessionCuenta=false;
					opcionLogin=4;
					break;
			}while(opcionLogin<1||opcionLogin>3);
		}while(sessionCuenta);
	}

}