package com.krakedev.proyectos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.proyectos.entidades.Empleado;
import com.krakedev.proyectos.entidades.Proyecto;
import com.krakedev.proyectos.entidades.Tarea;
import com.krakedev.proyectos.repositories.EmpleadoRepository;
import com.krakedev.proyectos.repositories.ProyectoRepository;
import com.krakedev.proyectos.repositories.TareaRepository;

import jakarta.transaction.Transactional;

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
	
	@Transactional
	public Tarea guardar(Tarea tarea) {
	    if (tarea.getProyecto() == null) {
	        throw new IllegalArgumentException(
	            "Debe indicar el proyecto de la tarea");
	    }
	    int proyectoId = tarea.getProyecto().getId();
	    Proyecto proyecto = proyectorepositorio.findById(proyectoId)
	        .orElseThrow(() -> new IllegalArgumentException(
	            "No existe el proyecto con ID " + proyectoId));
	    List<Empleado> empleados = new ArrayList<>();
	    if (tarea.getEmpleados() != null) {
	        for (Empleado empleadoRecibido : tarea.getEmpleados()) {
	            Empleado empleado = empleadorepositorio
	                .findById(empleadoRecibido.getId())
	                .orElseThrow(() -> new IllegalArgumentException(
	                    "No existe el empleado con ID "
	                    + empleadoRecibido.getId()));
	            empleados.add(empleado);
	        }
	    }
	    tarea.setProyecto(proyecto);
	    tarea.setEmpleados(empleados);
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
