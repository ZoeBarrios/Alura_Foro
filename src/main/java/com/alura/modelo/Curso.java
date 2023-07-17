package com.alura.modelo;

import com.alura.DTOS.CursosDTOS.DatosCrearCurso;
import com.alura.DTOS.CursosDTOS.DatosActualizarCurso;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity(name = "Curso")
@Table(name = "cursos")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String categoria;

	public Curso(String nombre, String categoria) {
		this.nombre = nombre;
		this.categoria = categoria;
	}

	public Curso(DatosCrearCurso datosCrearCurso) {
		this.nombre=datosCrearCurso.nombre();
		this.categoria=datosCrearCurso.categoria();
	}

	public void actualizarCurso(DatosActualizarCurso datosCrearCurso){
		if(datosCrearCurso.categoria()!=null){
			this.categoria=datosCrearCurso.categoria();
		}
		if(datosCrearCurso.nombre()!=null){
			this.nombre= datosCrearCurso.nombre();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
