package com.system.aplikasi.pos.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "barang")
@Data
@Accessors(chain = true)
public class Barang {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "nama_barang")
    private String namaBarang;

	@Column(name = "harga")
	private Double harga;

}
