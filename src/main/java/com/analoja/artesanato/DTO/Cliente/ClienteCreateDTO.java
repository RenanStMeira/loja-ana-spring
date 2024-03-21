package com.analoja.artesanato.DTO.Cliente;

import com.analoja.artesanato.DTO.Endereco.EnderecoCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ClienteCreateDTO {

    @NotNull
    @Schema(description = "Nome do cliente", example = "João", required = true)
    private String nome;

    @NotNull
    @Schema(description = "Sobrenome do cliente", example = "Silva", required = true)
    private String sobrenome;

    @NotNull
    @Schema(description = "CPF do cliente", example = "123.456.789-00", required = true)
    private String cpf;

    @NotNull
    @Schema(description = "Celular do cliente", example = "(11) 99999-9999", required = true)
    private String celular;

    @NotNull
    @Schema(description = "Data de nascimento do cliente", example = "01/01/2000", required = true)
    private Date dataNascimento;

    @NotNull
    @Schema(description = "Email do cliente", example = "cliente@email.com", required = true)
    private String email;

    @NotNull
    @Schema(description = "Senha do cliente", example = "senha123", required = true)
    private String senha;

    private EnderecoCreateDTO endereco;

}
