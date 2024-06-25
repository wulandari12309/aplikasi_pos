package com.system.aplikasi.pos.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "penjualan")
@Data
@Accessors(chain = true)
public class Penjualan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tanggal_penjualan")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalPenjualan;

    @Column(name = "nama_cabang")
    private String namaCabang;

    @Column(name = "no_pelangan")
    private String noPelangan;

    @Column(name = "total")
    private Double total;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_penjualan_detail", referencedColumnName = "id")
    List<PenjualanDetail> PenjualanDetail = new ArrayList<>();
}
