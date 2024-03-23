package com.analoja.artesanato.services;

import com.analoja.artesanato.DTO.Admin.AdminCreateDTO;
import com.analoja.artesanato.DTO.Cliente.MensagemDTO;
import com.analoja.artesanato.DTO.Endereco.EnderecoResponseDTO;
import com.analoja.artesanato.entity.Admin;
import com.analoja.artesanato.entity.Endereco;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.repository.AdminRepository;
import com.analoja.artesanato.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.analoja.artesanato.enums.MensagemRetorno.*;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;
    private final EnderecoRepository enderecoRepository;
    private final EnderecoService enderecoService;

    private String MESSAGE_EMAIL_JA_CADASTRADO = "Email já cadastrado";
    private String MESSAGE_ADMIN_NAO_ENCONTRADO = "Admin não encontrado";

    public MensagemDTO criarAdmin(AdminCreateDTO adminDTO) throws RegraDeNegocioException {

        if (adminRepository.findByEmail(adminDTO.getEmail()).isPresent()) {
            throw new RegraDeNegocioException(MESSAGE_EMAIL_JA_CADASTRADO);
        }

        Admin admin = new Admin();

        admin.setNome(adminDTO.getNome());
        admin.setEmail(adminDTO.getEmail());
        admin.setSenha(passwordEncoder.encode(adminDTO.getSenha()));

        if (adminDTO.getEndereco() != null) {
            EnderecoResponseDTO enderecoResponseDTO = enderecoService.criarEndereco(adminDTO.getEndereco());
            Endereco endereco = objectMapper.convertValue(enderecoResponseDTO, Endereco.class);
            endereco = enderecoRepository.save(endereco);
            admin.setEndereco(endereco);
        } else {
            admin.setEndereco(null);
        }

        Admin adminSalvo = adminRepository.save(admin);

        return new MensagemDTO(CADASTRO_COM_SUCESSO_ADMIN.getMensagemRetorno(), adminSalvo.getIdAdmin());
    }

    public MensagemDTO update(Integer idAdmin, AdminCreateDTO adminDTO)throws RegraDeNegocioException {
        existsId(idAdmin);

        Admin adminReculperado = buscarAdminPorId(idAdmin);

        adminReculperado.setNome(adminDTO.getNome());
        adminReculperado.setEmail(adminDTO.getEmail());
        adminReculperado.setSenha(passwordEncoder.encode(adminDTO.getSenha()));

        Endereco enderecoReculperado = adminReculperado.getEndereco();

        enderecoReculperado.setRua(adminDTO.getEndereco().getRua());
        enderecoReculperado.setNumero(adminDTO.getEndereco().getNumero());
        enderecoReculperado.setCidade(adminDTO.getEndereco().getCidade());
        enderecoReculperado.setEstado(adminDTO.getEndereco().getEstado());
        enderecoReculperado.setCep(adminDTO.getEndereco().getCep());

        Endereco enderecoSalvo = enderecoRepository.save(enderecoReculperado);

        adminReculperado.setEndereco(enderecoSalvo);

        return new MensagemDTO(EDITADO_COM_SUCESSO_ADMIN.getMensagemRetorno(), adminReculperado.getIdAdmin());
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public MensagemDTO deletarAdmin(Integer idAdmin) throws RegraDeNegocioException {
        existsId(idAdmin);

        Admin admin = buscarAdminPorId(idAdmin);
        adminRepository.delete(admin);

        return new MensagemDTO(EXCLUIDA_COM_SUCESSO_ADMIN.getMensagemRetorno(), admin.getIdAdmin());
    }

    public Admin buscarAdminPorId(Integer idAdmin) throws RegraDeNegocioException {
        existsId(idAdmin);

        return adminRepository.findById(idAdmin)
                .orElseThrow(() -> new RegraDeNegocioException(MESSAGE_ADMIN_NAO_ENCONTRADO));
    }

    public Admin findByEmail(String email) throws RegraDeNegocioException {
        return adminRepository.findByEmail(email)
                .orElseThrow(() -> new RegraDeNegocioException(MESSAGE_ADMIN_NAO_ENCONTRADO));
    }

    private Admin existsId(Integer idAdmin) throws RegraDeNegocioException {
        return adminRepository.findById(idAdmin)
                .orElseThrow(() -> new RegraDeNegocioException(MESSAGE_ADMIN_NAO_ENCONTRADO));
    }
}
