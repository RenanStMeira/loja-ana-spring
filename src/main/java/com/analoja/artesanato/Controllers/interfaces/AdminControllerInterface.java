package com.analoja.artesanato.Controllers.interfaces;

import com.analoja.artesanato.DTO.Admin.AdminCreateDTO;
import com.analoja.artesanato.entity.Admin;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface AdminControllerInterface {

    @Operation(summary = "Find admin by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admin found"),
            @ApiResponse(responseCode = "404", description = "Admin not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping("/{id}")
    ResponseEntity<Admin> findById(@PathVariable Integer id) throws RegraDeNegocioException;

    @Operation(summary = "Find all admins")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admins retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping("/all")
    ResponseEntity<List<Admin>> findAll();

    @Operation(summary = "Find admin by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admin found"),
            @ApiResponse(responseCode = "404", description = "Admin not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping("/email")
    ResponseEntity<Admin> findByEmail(@RequestParam String email) throws RegraDeNegocioException;

    @Operation(summary = "Create a new admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Admin created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PostMapping("/create")
    ResponseEntity<Admin> create(@Valid @RequestBody AdminCreateDTO adminDTO) throws RegraDeNegocioException;

    @Operation(summary = "Update an existing admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admin updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Admin not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PutMapping("/update/{id}")
    ResponseEntity<Admin> update(@PathVariable Integer id, @Valid @RequestBody AdminCreateDTO adminDTO) throws RegraDeNegocioException;

    @Operation(summary = "Delete an admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admin deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Admin not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> delete(@PathVariable Integer id) throws RegraDeNegocioException;
}