package com.alura.modelo;

import com.alura.DTOS.TopicosDTOS.DatosActualizarTopico;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity(name="Topico")
@Table(name="topicos")
public class Topico {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(unique = true)
	private String titulo;

	@Column(unique = true)
	private String mensaje;


	private LocalDateTime fechaCreacion = LocalDateTime.now();

	@Enumerated(EnumType.STRING)
	private StatusTopico status = StatusTopico.NO_RESPONDIDO;



	@JoinColumn(name = "usuario")
	@ManyToOne
	private Usuario usuario;


	@JoinColumn(name = "curso")
	@ManyToOne
	private Curso curso;


	@OneToMany(mappedBy = "topico")
	private List<Respuesta> respuestas = new ArrayList<>();

	public Topico(String titulo, String mensaje, Curso curso,Usuario usuario) {
		this.titulo = titulo;
		this.mensaje = mensaje;
		this.usuario=usuario;
		this.curso = curso;
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
		Topico other = (Topico) obj;
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public LocalDateTime getfechaCreacion() {
		return fechaCreacion;
	}

	public void setfechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public void setStatus(StatusTopico status) {
		this.status = status;
	}

	public Usuario getAutor() {
		return usuario;
	}

	public void setAutor(Usuario autor) {
		this.usuario = autor;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
		if(datosActualizarTopico.mensaje()!=null){
			this.mensaje= datosActualizarTopico.mensaje();
		}
		if(datosActualizarTopico.titulo()!=null){
			this.titulo= datosActualizarTopico.titulo();
		}
	}
}
