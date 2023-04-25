package com.hmaurocastro.rest;

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

import com.hmaurocastro.model.Cargo;
import com.hmaurocastro.repository.CargoRepository;

/**
 * Controlador para manejar las solicitudes HTTP para cargos
 * 
 * @author HMauroCastro
 *
 */
@RestController
@RequestMapping("/cargos")
public class RestCargoController {

	@Autowired
	private CargoRepository cargoRepository;

	@GetMapping
	public List<Cargo> listarCargos() {
		return cargoRepository.findAll();
	}

	@PostMapping
	public Cargo crearCargo(Cargo cargo) {
		return cargoRepository.save(cargo);
	}

	@PutMapping("/{id}")
	public Cargo actualizarCargo(@PathVariable("id") Long id, Cargo cargo) {
		Cargo cargoExistente = cargoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cargo no encontrado"));
		cargoExistente.setNombre(cargo.getNombre());
		return cargoRepository.save(cargoExistente);
	}

	@GetMapping("/{id}")
	public Cargo buscarCargo(@PathVariable("id") Long id) {
		return cargoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cargo no encontrado"));
	}

	@DeleteMapping("/{id}")
	public void eliminarCargo(@PathVariable("id") Long id) {
		cargoRepository.deleteById(id);
	}

}
