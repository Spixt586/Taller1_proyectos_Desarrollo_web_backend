package com.krakedev.proyectos.entidades;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="tareas")
public class Tarea {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length = 100)
	private String descripcion;
	private LocalDate fechaLimite;
	private Proyecto proyecto;
	@ManyToMany
	@JoinTable(
			name = "tarea_empleados",
			joinColumns = @JoinColumn(name = "tarea_id"),
			inverseJoinColumns = @JoinColumn(name = "empleado_id")
	)
	private List<Empleado> empleados;
	
	public Tarea() {
		super();
	}
	public Tarea(int id, String descripcion, LocalDate fechaLimite, Proyecto proyecto, List<Empleado> empleados) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.fechaLimite = fechaLimite;
		this.proyecto = proyecto;
		this.empleados = empleados;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDate getFechaLimite() {
		return fechaLimite;
	}
	public void setFechaLimite(LocalDate fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	public List<Empleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
}
