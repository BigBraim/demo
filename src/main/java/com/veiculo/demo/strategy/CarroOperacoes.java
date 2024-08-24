package com.veiculo.demo.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CarroOperacoes implements OperacoesVeiculo {

    @Override
    public void acelerar() {
        log.info("O carro está acelerando.");
    }

    @Override
    public void movimentar() {
        log.info("O carro está se movimentando.");
    }

    @Override
    public void efetuarManutencao() {
        log.info("O carro está passando por manutenção.");
    }
}

