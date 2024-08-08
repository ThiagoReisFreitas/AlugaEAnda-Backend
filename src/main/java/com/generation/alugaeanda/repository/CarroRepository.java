package com.generation.alugaeanda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.generation.alugaeanda.models.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long>{

	List<Carro> findAllByMarca(@Param("marca") String marca);
	
	List<Carro> findAllByModelo(@Param("marca") String modelo);
	
	List<Carro> findAllByAno(@Param("ano") String ano);
	
	List<Carro> findAllByAlugado(@Param("alugado") Boolean alugado);
}
