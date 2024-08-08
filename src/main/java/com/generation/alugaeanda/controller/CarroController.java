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

import com.generation.alugaeanda.models.Carro;
import com.generation.alugaeanda.repository.CarroRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carros")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CarroController {

	@Autowired
	private CarroRepository carroRepository;
	
	@GetMapping
	public ResponseEntity<List<Carro>> getAll(){
		return ResponseEntity.ok(carroRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Carro> getById(@PathVariable("id") Long id){
		return carroRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/marca/{marca}")
	public ResponseEntity<List<Carro>> getByMarca(@PathVariable("marca") String marca){
		return ResponseEntity.ok(carroRepository.findAllByMarca(marca));
	}
	
	@GetMapping("/modelo/{modelo}")
	public ResponseEntity<List<Carro>> getByModelo(@PathVariable("modelo") String modelo){
		return ResponseEntity.ok(carroRepository.findAllByModelo(modelo));
	}
	
	@GetMapping("/ano/{ano}")
	public ResponseEntity <List<Carro>> getByAno(@PathVariable("ano") String ano){
		return ResponseEntity.ok(carroRepository.findAllByAno(ano));
	}
	
	
	@PostMapping
	public ResponseEntity<Carro> post(@Valid @RequestBody Carro carros){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(carroRepository.save(carros));
	}
	
	@PutMapping
	public ResponseEntity<Carro> put(@Valid @RequestBody Carro carros){
		return carroRepository.findById(carros.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
						.body(carroRepository.save(carros)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		Optional<Carro> carros = carroRepository.findById(id);
		if(carros.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		carroRepository.deleteById(id);
	}
	
}
