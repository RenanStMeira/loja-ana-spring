package com.analoja.artesanato.enums;

import lombok.Data;


public enum Categoria {
    BANHEIRO, QUARTO, COZINHA, SALA, PASSADEIRA, DIVERSOS;

    public static Categoria toEnum(String categoria) {
        if (categoria == null) {
            return null;
        }

        for (Categoria x : Categoria.values()) {
            if (categoria.equals(x.name())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Categoria inválida: " + categoria);
    }

}
