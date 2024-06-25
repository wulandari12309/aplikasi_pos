package com.system.aplikasi.pos.repository;

import com.system.aplikasi.pos.model.entity.Barang;
import com.system.aplikasi.pos.model.entity.Cabang;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CabangRepository extends CrudRepository<Cabang, Long> {
    Optional<Cabang> findByNamaCabang(String name);
}
