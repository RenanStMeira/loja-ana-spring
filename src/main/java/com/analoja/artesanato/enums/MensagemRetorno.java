package com.analoja.artesanato.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MensagemRetorno {

    //Cliente
    CADASTRO_COM_SUCESSO("Cliente cadastrado com sucesso"),
    EDITADO_COM_SUCESSO("Cliente Editado com sucesso"),
    EXCLUIDA_COM_SUCESSO("Cliente Deletado com sucesso"),

    //Admin
    CADASTRO_COM_SUCESSO_ADMIN("Admin cadastrado com sucesso"),
    EDITADO_COM_SUCESSO_ADMIN("Admin Editado com sucesso"),
    EXCLUIDA_COM_SUCESSO_ADMIN("Admin Deletado com sucesso");



    private String mensagemRetorno;
}
