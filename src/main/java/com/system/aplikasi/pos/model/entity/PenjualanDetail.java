package com.system.aplikasi.pos.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "penjualan_detail")
@Data
@Accessors(chain = true)
public class PenjualanDetail {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "nama_barang")
	private String namaBarang;

	@Column(name = "harga")
	private Double harga;

	@Column(name = "qty")
	private Double qty;

}
