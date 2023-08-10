package controlador;

import java.util.List;

import entities.Cliente;
import entities.Sucursal;
import mapper.BaseMapper;

public abstract class ControladorBase<T> {
	protected List<T> readAll(String consulta, BaseMapper mapper){
		return null;
	};
	protected List<T> getByParameters(T request, String query){
		return null;
	};
	protected Integer insert(T t, String sql) {
		return null;
	}
	protected Integer insert(Cliente cliente, Sucursal sucursal, String sql) {
		// TODO Auto-generated method stub
		return null;
	};
}