package com.generation.alugaeanda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.alugaeanda.models.Aluguel;
import com.generation.alugaeanda.repository.AluguelRepository;
import com.generation.alugaeanda.repository.CarroRepository;
import com.generation.alugaeanda.repository.UsuarioRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/aluguel")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AluguelController {
	
	@Autowired
	private AluguelRepository aluguelRepository;
	
	@Autowired
	private CarroRepository carroRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public ResponseEntity<List<Aluguel>> getAll(){
		return ResponseEntity.ok(aluguelRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluguel> getById(@PathVariable("id") Long id){
		return aluguelRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PostMapping
	public ResponseEntity<Aluguel> post(@Valid @RequestBody Aluguel aluguel){
		if(carroRepository.existsById(aluguel.getCarro().getId())) {
			if(usuarioRepository.existsById(aluguel.getUsuario().getId())) {				
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(aluguelRepository.save(aluguel));
			}
		}
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carro não existe", null);
	}
 
	@PutMapping
	public ResponseEntity<Aluguel> put (@Valid @RequestBody Aluguel aluguel){
		if(aluguelRepository.existsById(aluguel.getId())) {
			if(usuarioRepository.existsById(aluguel.getUsuario().getId())) {
				if(carroRepository.existsById(aluguel.getCarro().getId())) {					
					return ResponseEntity.status(HttpStatus.OK)
							.body(aluguelRepository.save(aluguel));
				}
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carro não existe", null);
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario não existe", null);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		Optional<Aluguel> aluguel = aluguelRepository.findById(id);
		
		if(aluguel.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		aluguelRepository.deleteById(id);
	}
}





















