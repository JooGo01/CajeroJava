package test;
import entities.*;
import java.util.Scanner;
import controlador.*;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SistemaCajero sc = new SistemaCajero();
		Session s = new Session();
		Cuenta cuenta = new Cuenta();
		Cuenta cuentaDestino= new Cuenta();
		s=sc.iniciarSesion("jgomez", "123");
		if(s!=null) {
			//el usuario existe
			System.out.println(s.getToken());
			//valida el pin tiene 4 digitos
			if(sc.validatePin(3214)) {
				//cambiar pin
				sc.cambiarPin(s.getCliente(), 2341);
			}
			cuenta=sc.seleccionarCuenta(s.getCliente());
			cuentaDestino=sc.seleccionarCuentaDestino();
			sc.depositar(1000.00, cuenta);
			sc.extraer(100.00, cuenta);
			sc.transferir(cuenta, cuentaDestino, 5000.00);
			//termina la sesion
			sc.cerrarSesion(s);
		}else {
			//el usuario no existe
		}
	}

}