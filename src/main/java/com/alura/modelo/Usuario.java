package com.alura.modelo;

import com.alura.DTOS.UsuariosDTOS.DatosActualizarUsuario;
import com.alura.DTOS.UsuariosDTOS.DatosRegistroUsuario;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Entity(name = "Usuario")
@Table(name = "usuarios")
@Embeddable
public class Usuario implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String email;
	private String contrasena;

	public Usuario(DatosRegistroUsuario datosRegistroUsuario) {
		this.email=datosRegistroUsuario.email();
		this.contrasena=datosRegistroUsuario.contrasena();
		this.nombre=datosRegistroUsuario.nombre();
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
		Usuario other = (Usuario) obj;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

    public void actualizar(DatosActualizarUsuario datosActualizarUsuario) {
		if(datosActualizarUsuario.nombre()!=null){
			this.nombre=datosActualizarUsuario.nombre();
		}
		if(datosActualizarUsuario.email()!=null){
			this.email=datosActualizarUsuario.email();
		}
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return this.contrasena;
	}

	@Override
	public String getUsername() {
		return this.nombre;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true
				;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
