package com.veiculo.demo.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CarroEletricoOperacoes implements OperacoesVeiculo {

    @Override
    public void acelerar() {
        log.info("O carro elétrico está acelerando.");
    }

    @Override
    public void movimentar() {
        log.info("O carro elétrico está se movimentando silenciosamente.");
    }

    @Override
    public void efetuarManutencao() {
        log.info("O carro elétrico está passando por manutenção.");
    }
}

