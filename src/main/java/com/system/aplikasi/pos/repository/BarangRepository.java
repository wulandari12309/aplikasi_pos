package com.system.aplikasi.pos.repository;

import com.system.aplikasi.pos.model.entity.Barang;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BarangRepository extends CrudRepository<Barang, Long> {
    Optional<Barang> findByNamaBarang(String name);
    List<Barang> findAllByOrderByHargaAsc();
}
