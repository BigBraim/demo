package com.veiculo.demo.model;

import jakarta.persistence.Entity;

@Entity
public class Moto extends Veiculo {

    public Moto() {
    }

    public Moto(String marca, String modelo) {
        super(marca, modelo);
    }

    @Override
    public void mover() {
        System.out.println("A moto está se movendo.");
    }
}

