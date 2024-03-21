package com.analoja.artesanato.Controllers;

import com.analoja.artesanato.DTO.Admin.AdminCreateDTO;
import com.analoja.artesanato.entity.Admin;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.services.AdminService;
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
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/{id}")
    public ResponseEntity<Admin> findById(@PathVariable Integer id) throws RegraDeNegocioException {
        Admin admin = adminService.findById(id);
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
    public ResponseEntity<Admin> create(@Valid @RequestBody AdminCreateDTO adminDTO) throws RegraDeNegocioException {
        Admin admin = adminService.createAdmin(adminDTO);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Admin> update(@PathVariable Integer id, @Valid @RequestBody AdminCreateDTO adminDTO) throws RegraDeNegocioException {
        Admin admin = adminService.update(id, adminDTO);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws RegraDeNegocioException {
        adminService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
