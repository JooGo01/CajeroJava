package test;
import entities.*;
import java.util.Scanner;
import controlador.*;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sucursal sucursal = new Sucursal();
		sucursal.setId((long) 1);
		sucursal.setCodigo("015");
		sucursal.setDireccion("pirulo 123");
		sucursal.setNombre("BBVA");
		SucursalController sc= new SucursalController();
		sc.insert(sucursal);
	}

}