package com.analoja.artesanato.services;

import com.analoja.artesanato.DTO.AdminCreateDTO;
import com.analoja.artesanato.entity.Admin;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.repository.AdminRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final ObjectMapper objectMapper;

    private String MESSAGE_EMAIL_JA_CADASTRADO = "Email já cadastrado";
    private String MESSAGE_ADMIN_NAO_ENCONTRADO = "Admin não encontrado";

    public Admin createAdmin(AdminCreateDTO adminDTO) throws RegraDeNegocioException {
        Admin admin = objectMapper.convertValue(adminDTO, Admin.class);

        if (adminRepository.existsByEmail(admin.getEmail())) {
            throw new RegraDeNegocioException(MESSAGE_EMAIL_JA_CADASTRADO);
        }

        admin.setEmail(admin.getEmail().toLowerCase());
        admin.setSenha(admin.getSenha());
        admin.setNome(admin.getNome());

        return adminRepository.save(admin);
    }

    public Admin update(Integer idAdmin, AdminCreateDTO adminDTO)throws RegraDeNegocioException {
        existsId(idAdmin);

        Admin adminReculperado = findById(idAdmin);

        adminReculperado.setNome(adminDTO.getNome());
        adminReculperado.setEmail(adminDTO.getEmail());
        adminReculperado.setSenha(adminDTO.getSenha());

        return adminRepository.save(adminReculperado);
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public void delete(Integer idAdmin) throws RegraDeNegocioException {
        existsId(idAdmin);

        Admin admin = findById(idAdmin);
        adminRepository.delete(admin);
    }

    public Admin findById(Integer idAdmin) throws RegraDeNegocioException {
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
