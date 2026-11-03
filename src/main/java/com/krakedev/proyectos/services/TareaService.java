package com.krakedev.proyectos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.proyectos.entidades.Tarea;
import com.krakedev.proyectos.repositories.TareaRepository;

@Service
public class TareaService {
	public final TareaRepository repository;

	public TareaService(TareaRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Tarea guardar(Tarea tarea) {
		return repository.save(tarea);
	}
	
	public List<Tarea> listar(){
		return repository.findAll();
	}
	
	public Tarea buscarPorId(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public boolean eliminar(int id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}
	
	public Tarea actualizar(int id, Tarea datos) {
		Tarea tarea = buscarPorId(id);
		
		if(tarea != null) {
			tarea.setDescripcion(datos.getDescripcion());
			tarea.setEmpleados(datos.getEmpleados());
			tarea.setFechaLimite(datos.getFechaLimite());
			tarea.setProyecto(datos.getProyecto());
			
			return repository.save(tarea);
		}else
			return null;
	}
}
