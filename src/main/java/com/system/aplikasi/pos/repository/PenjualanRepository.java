package com.system.aplikasi.pos.repository;

import com.system.aplikasi.pos.model.entity.Penjualan;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PenjualanRepository extends CrudRepository<Penjualan, Long> {
}
