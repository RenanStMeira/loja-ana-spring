package com.analoja.artesanato.DTO.Endereco;

import com.analoja.artesanato.entity.Cliente;
import lombok.Data;

@Data
public class EnderecoResponseDTO {
    private Integer idEndereco;
    private Cliente cliente;
    private String rua;
    private Integer numero;
    private String cidade;
    private String estado;
    private String cep;
}
