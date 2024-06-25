package com.system.aplikasi.pos.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "cabang")
@Data
@Accessors(chain = true)
public class Cabang {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "nama_cabang")
    private String namaCabang;

	@Column(name = "nama_pemilik")
	private String namaPemilik;

	@Column(name = "alamat")
	private String alamat;

	@Column(name = "no_hp")
	private String noHp;


}
