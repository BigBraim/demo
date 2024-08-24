package com.veiculo.demo.controller;

import com.lowagie.text.DocumentException;
import com.veiculo.demo.model.Veiculo;
import com.veiculo.demo.repository.VeiculoRepository;
import com.veiculo.demo.service.PdfService;
import com.veiculo.demo.service.VeiculoService;
import com.veiculo.demo.strategy.VeiculoStrategyExecutor;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private VeiculoStrategyExecutor veiculoStrategyExecutor;

    @Operation(summary = "Criar um novo veículo", description = "Cria um novo veiculo no banco de dados, não esqueça de definir seu o veículo é do tipo 'carro', 'moto' ou 'carroEletrico'.")
    @PostMapping("/create")
    public Veiculo criar(@RequestBody Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    @Operation(summary = "Obter veículo por ID", description = "Digite o ID para procurar por um veículo")
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Veiculo> obterPorId(@PathVariable Long id) {
        Optional<Veiculo> veiculo = veiculoRepository.findById(id);
        return veiculo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obter veículo por Marca", description = "Digite a marca do veículo e aparecerá seus correspondentes")
    @GetMapping("/find-by-brand/{tipo}")
    public List<Veiculo> obterPorTipo(@PathVariable String tipo) {
        return veiculoRepository.findByMarca(tipo);
    }

    @Operation(summary = "Listar todos os veículos ordenados", description = "Mostra todos os veiculos cadastrados no banco de dados")
    @GetMapping("/list-by-ordered")
    public List<Veiculo> listarTodosOrdenados() {
        return veiculoRepository.findAllByOrderByMarcaAsc();
    }

    @Operation(summary = "Atualizar um veículo existente", description = "Atualiza um veiculo existente. Mas não esqueça de fornecer sua marca e seu modelo.")
    @PutMapping("/update/{id}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @RequestBody Veiculo veiculoAtualizado) {
        if (veiculoService.obterPorId(id).isPresent()) {
            veiculoAtualizado.setId(id);
            return ResponseEntity.ok(veiculoService.salvar(veiculoAtualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deletar um veículo", description = "Deleta um veículo")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (veiculoRepository.existsById(id)) {
            veiculoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Executar operação", description = "Utilize apenas as seguintes operações: 'acelerar', 'movimentar' e 'efetuarManutencao'. ")
    @PostMapping("/strategy/operation/{tipo}/{operacao}")
    public ResponseEntity<Void> executarOperacao(@PathVariable String tipo, @PathVariable String operacao) {
        veiculoStrategyExecutor.executarOperacao(tipo, operacao);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Gerar um PDF", description = "Gera um PDF com todos os veiculos listados no banco de dados")
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> gerarPdfVeiculos() {
        try {
            byte[] pdf = pdfService.gerarPdfVeiculos();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "veiculos.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdf);

        } catch (DocumentException | IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}
