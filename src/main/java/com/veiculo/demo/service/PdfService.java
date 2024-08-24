package com.veiculo.demo.service;

import com.lowagie.text.DocumentException;
import com.veiculo.demo.model.Veiculo;
import com.veiculo.demo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private TemplateEngine templateEngine;

    public byte[] gerarPdfVeiculos() throws DocumentException, IOException {
        List<Veiculo> veiculos = veiculoRepository.findAll();

        // Criar contexto para Thymeleaf
        Context context = new Context();
        context.setVariable("veiculos", veiculos);

        // Processar template HTML com Thymeleaf
        String htmlContent = templateEngine.process("veiculos", context);

        // Converter HTML para PDF usando Flying Saucer
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        renderer.createPDF(outputStream);

        return outputStream.toByteArray();
    }
}
