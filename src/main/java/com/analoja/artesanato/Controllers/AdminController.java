package com.analoja.artesanato.Controllers;

import com.analoja.artesanato.Controllers.interfaces.AdminControllerInterface;
import com.analoja.artesanato.DTO.Admin.AdminCreateDTO;
import com.analoja.artesanato.DTO.Cliente.MensagemDTO;
import com.analoja.artesanato.entity.Admin;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.services.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Endpoint Para Admin")
@RequestMapping("/admin")
public class AdminController implements AdminControllerInterface {

    private final AdminService adminService;

    @GetMapping("/{id}")
    public ResponseEntity<Admin> findById(@PathVariable Integer id) throws RegraDeNegocioException {
        Admin admin = adminService.buscarAdminPorId(id);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Admin>> findAll() {
        List<Admin> adminList = adminService.findAll();
        return new ResponseEntity<>(adminList, HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<Admin> findByEmail(@RequestParam String email) throws RegraDeNegocioException {
        Admin admin = adminService.findByEmail(email);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<MensagemDTO> create(@Valid @RequestBody AdminCreateDTO adminDTO) throws RegraDeNegocioException {
        MensagemDTO admin = adminService.criarAdmin(adminDTO);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MensagemDTO> update(@PathVariable Integer id, @Valid @RequestBody AdminCreateDTO adminDTO) throws RegraDeNegocioException {
        MensagemDTO admin = adminService.update(id, adminDTO);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MensagemDTO> delete(@PathVariable Integer id) throws RegraDeNegocioException {
        adminService.deletarAdmin(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
