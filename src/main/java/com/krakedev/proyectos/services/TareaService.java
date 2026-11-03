package com.krakedev.proyectos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.proyectos.entidades.Tarea;
import com.krakedev.proyectos.repositories.EmpleadoRepository;
import com.krakedev.proyectos.repositories.ProyectoRepository;
import com.krakedev.proyectos.repositories.TareaRepository;

@Service
public class TareaService {
	public final TareaRepository tarearepositorio;
	public final EmpleadoRepository empleadorepositorio;
	public final ProyectoRepository proyectorepositorio;
	
	public TareaService(TareaRepository tarearepositorio, EmpleadoRepository empleadorepositorio,
			ProyectoRepository proyectorepositorio) {
		super();
		this.tarearepositorio = tarearepositorio;
		this.empleadorepositorio = empleadorepositorio;
		this.proyectorepositorio = proyectorepositorio;
	}
	
	public Tarea guardar(Tarea tarea) {
		return tarearepositorio.save(tarea);
	}

	public List<Tarea> listar(){
		return tarearepositorio.findAll();
	}
	
	public Tarea buscarPorId(int id) {
		return tarearepositorio.findById(id).orElse(null);
	}
	
	public boolean eliminar(int id) {
		if(tarearepositorio.existsById(id)) {
			tarearepositorio.deleteById(id);
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
			tarea.setCostoEstimado(datos.getCostoEstimado());
			
			return tarearepositorio.save(tarea);
		}else {
			return null;
		}
	}
}
