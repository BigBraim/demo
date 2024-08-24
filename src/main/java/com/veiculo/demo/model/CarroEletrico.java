package com.veiculo.demo.model;

import jakarta.persistence.Entity;

@Entity
public class CarroEletrico extends Carro {

    public CarroEletrico() {
    }

    public CarroEletrico(String marca, String modelo) {
        super(marca, modelo);
    }

    @Override
    public void mover() {
        System.out.println("O carro elétrico está se movendo silenciosamente.");
    }
}
