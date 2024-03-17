package com.analoja.artesanato.repository;

import com.analoja.artesanato.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByLogin(String username);
}
