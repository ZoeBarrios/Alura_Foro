package com.alura.modelo;

import com.alura.DTOS.RespuestaDTOS.DatosActualizarRespuesta;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name = "respuestas")
public class Respuesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensaje;

	@ManyToOne
	@JoinColumn(name = "topico")
	private Topico topico;

	private LocalDateTime fechaCreacion = LocalDateTime.now();

	@ManyToOne
	@JoinColumn(name = "usuario")
	private Usuario autor;
	private Boolean solucion = false;

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
		Respuesta other = (Respuesta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Respuesta(String mensaje,Topico topico,Usuario usuario){
		this.autor=usuario;
		this.mensaje=mensaje;
		this.topico=topico;
	}

	public void actualizar(DatosActualizarRespuesta datosActualizarRespuesta){
		if(datosActualizarRespuesta.mensaje()!=null){
			this.mensaje=datosActualizarRespuesta.mensaje();
		}
		if(datosActualizarRespuesta.solucion()!=null){
			this.solucion=datosActualizarRespuesta.solucion();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Topico getTopico() {
		return topico;
	}

	public void setTopico(Topico topico) {
		this.topico = topico;
	}

	public LocalDateTime getfechaCreacion() {
		return fechaCreacion;
	}

	public void setfechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Boolean getSolucion() {
		return solucion;
	}

	public void setSolucion(Boolean solucion) {
		this.solucion = solucion;
	}

}
