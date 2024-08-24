package com.veiculo.demo.repository;

import com.veiculo.demo.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findAllByOrderByMarcaAsc();
    List<Veiculo> findByMarca(String marca);
}



