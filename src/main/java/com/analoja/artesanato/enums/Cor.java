package com.analoja.artesanato.enums;

public enum Cor {
    VERMELHO, AZUL, VERDE, PRETO, BRANCO, CRU, DOIS_CORES, TRES_CORES;

    public static Cor toEnum(String cor) {
        if (cor == null) {
            return null;
        }

        for (Cor x : Cor.values()) {
            if (cor.equals(x.name())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Cor inválida: " + cor);
    }
}
