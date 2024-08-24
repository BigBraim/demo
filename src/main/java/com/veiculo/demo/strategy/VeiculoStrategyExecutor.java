package com.veiculo.demo.strategy;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VeiculoStrategyExecutor {

    private final List<OperacoesVeiculo> operacoesVeiculos;

    public VeiculoStrategyExecutor(List<OperacoesVeiculo> operacoesVeiculos) {
        this.operacoesVeiculos = operacoesVeiculos;
    }

    public void executarOperacao(String tipo, String operacao) {
        for (OperacoesVeiculo operacaoVeiculo : operacoesVeiculos) {
            if (operacaoVeiculo.getClass().getSimpleName().equalsIgnoreCase(tipo + "Operacoes")) {
                switch (operacao.toLowerCase()) {
                    case "acelerar":
                        operacaoVeiculo.acelerar();
                        break;
                    case "movimentar":
                        operacaoVeiculo.movimentar();
                        break;
                    case "manutencao":
                        operacaoVeiculo.efetuarManutencao();
                        break;
                    default:
                        throw new IllegalArgumentException("Operação não suportada: " + operacao);
                }
            }
        }
    }
}
