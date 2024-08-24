package com.veiculo.demo.repository;

import com.veiculo.demo.model.CarroEletrico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroEletricoRepository extends JpaRepository<CarroEletrico, Long> {
}
