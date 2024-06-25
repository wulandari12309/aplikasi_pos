package com.system.aplikasi.pos.controllers.rest;

import com.system.aplikasi.pos.model.entity.Barang;
import com.system.aplikasi.pos.model.entity.Cabang;
import com.system.aplikasi.pos.repository.CabangRepository;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cabang")
public class CabangController {

    @Autowired
    private CabangRepository cabangRepository;

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        try {
            Optional<Cabang> byId = cabangRepository.findById(id);
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
            Iterable<Cabang> results = cabangRepository.findAll();
            return ResponseEntity.ok().body(results);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find-by-namacabang/{namaCabang}")
    public ResponseEntity<?> findByNamaBarang(@PathVariable String namaCabang) {
        try {
            Optional<Cabang> byId = cabangRepository.findByNamaCabang(namaCabang);
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
                            "  \"namaCabang\": \"Cabang 3\"," +
                            "  \"namaPemilik\": \"Solihin Akbar\"," +
                            "  \"alamat\": \"Desa Rajagaluh, RT/RW 002/004, Kec Pasaleman, Kab Cirebon\"," +
                            "  \"noHp\": \"087767672439\"" +
                            "}")
            }))@RequestBody Cabang cabang) {
        try {
            cabangRepository.save(cabang);
            return ResponseEntity.ok().body(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
