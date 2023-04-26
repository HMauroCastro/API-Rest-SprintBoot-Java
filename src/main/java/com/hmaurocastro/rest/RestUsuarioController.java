package com.hmaurocastro.rest;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.hmaurocastro.model.Usuario;
import com.hmaurocastro.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controlador para manejar las solicitudes HTTP para usuarios
 * 
 * @author HMauroCastro
 *
 */
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE})
public class RestUsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	@PostMapping
	public Usuario crearUsuario(Usuario usuario) {
		usuario.setFechaCreacion(new Date());
		return usuarioRepository.save(usuario);
	}

	@PutMapping("/{id}")
	public Usuario actualizarUsuario(@PathVariable("id") Long id, Usuario usuario) {
		Usuario usuarioExistente = usuarioRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
		usuarioExistente.setNombre(usuario.getNombre());
		usuarioExistente.setApellido(usuario.getApellido());
		usuarioExistente.setDni(usuario.getDni());
		usuarioExistente.setCedula(usuario.getCedula());
		usuarioExistente.setDireccion(usuario.getDireccion());
		usuarioExistente.setSalario(usuario.getSalario());
		usuarioExistente.setCargo(usuario.getCargo());
		return usuarioRepository.save(usuarioExistente);
	}

	@GetMapping("/{id}")
	public Usuario buscarUsuario(@PathVariable("id") Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
	}

	@DeleteMapping("/{id}")
	public void eliminarUsuario(@PathVariable("id") Long id) {
		usuarioRepository.deleteById(id);
	}

}
