package com.veiculo.demo.model;

import jakarta.persistence.Entity;

@Entity
public class Carro extends Veiculo {

    public Carro() {
    }

    public Carro(String marca, String modelo) {
        super(marca, modelo);
    }

    @Override
    public void mover() {
        System.out.println("O carro está se movendo.");
    }
}

