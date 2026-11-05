package com.krakedev.proyectos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.proyectos.entidades.Empleado;
import com.krakedev.proyectos.entidades.Tarea;
import com.krakedev.proyectos.repositories.EmpleadoRepository;

@Service
public class EmpleadoService {
	
	public final EmpleadoRepository repository;

	public EmpleadoService(EmpleadoRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Empleado guardar(Empleado empleado) {
		return repository.save(empleado);
	}
	
	public List<Empleado> listar(){
		return repository.findAll();
	}
	
	public Empleado buscarPorId(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public boolean eliminar(int id) {
		Empleado empleado = buscarPorId(id);
		if(empleado == null) {
			return false;
		}
		for(Tarea tarea : empleado.getTareas()) {
			tarea.getEmpleados().remove(empleado);
		}
		repository.delete(empleado);
		return true;
	}
	
	public Empleado actualizar(int id, Empleado datos) {
		Empleado empleado = buscarPorId(id);
		
		if(empleado != null) {
			empleado.setNombre(datos.getNombre());
			empleado.setCargo(datos.getCargo());
			empleado.setTareas(datos.getTareas());
			
			return repository.save(empleado);
		}else {
			return null;
		}
	}
}
