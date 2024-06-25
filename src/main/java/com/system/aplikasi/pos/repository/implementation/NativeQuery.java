package com.system.aplikasi.pos.repository.implementation;

import com.system.aplikasi.pos.model.entity.Penjualan;

import java.util.List;

public interface NativeQuery{
    List<Penjualan> getTransactionPerCabang(String cabang, String todayDate, String interval);
    Double totalPendapatanPerCabang(String cabang, String todayDate, String interval);
    String strukOtomatisPerCabang(String cabang, String todayDate);
}
