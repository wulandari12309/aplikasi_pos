package com.system.aplikasi.pos.controllers.rest;

import com.system.aplikasi.pos.model.entity.Barang;
import com.system.aplikasi.pos.repository.BarangRepository;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/barang")
public class BarangController {

    @Autowired
    private BarangRepository barangRepository;

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        try {
            Optional<Barang> byId = barangRepository.findById(id);
            if (!byId.isPresent()) {
                return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok().body(byId.get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        try {
            Iterable<Barang> results = barangRepository.findAllByOrderByHargaAsc();
            return ResponseEntity.ok().body(results);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find-by-namabarang/{namaBarang}")
    public ResponseEntity<?> findByNamaBarang(@PathVariable String namaBarang) {
        try {
            Optional<Barang> byId = barangRepository.findByNamaBarang(namaBarang);
            if (!byId.isPresent()) {
                return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok().body(byId.get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
            examples = {
                    @ExampleObject(value = "{" +
                            "  \"namaBarang\": \"THAI TEA\"," +
                            "  \"harga\": 5000" +
                            "}")
            }))@RequestBody Barang barang) {
        try {
            barangRepository.save(barang);
            return ResponseEntity.ok().body(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
