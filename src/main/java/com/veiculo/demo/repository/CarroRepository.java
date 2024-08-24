package com.veiculo.demo.repository;

import com.veiculo.demo.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}

