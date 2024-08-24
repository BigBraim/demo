package com.veiculo.demo.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MotoOperacoes implements OperacoesVeiculo {

    @Override
    public void acelerar() {
        log.info("A moto está acelerando.");
    }

    @Override
    public void movimentar() {
        log.info("A moto está se movimentando.");
    }

    @Override
    public void efetuarManutencao() {
        log.info("A moto está passando por manutenção.");
    }
}

