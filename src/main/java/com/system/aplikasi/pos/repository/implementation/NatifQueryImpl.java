package com.system.aplikasi.pos.repository.implementation;

import com.system.aplikasi.pos.model.entity.Penjualan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NatifQueryImpl implements NativeQuery {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Penjualan> getTransactionPerCabang(String cabang, String todayDate, String interval) {
        String sql = "select * from penjualan where nama_cabang ='" + cabang + "' " +
                "and tanggal_penjualan >= '" + todayDate + "'\\:\\:date and tanggal_penjualan < ('" + todayDate + "'\\:\\:date + '" + interval + "'\\:\\:interval) order by tanggal_penjualan desc";

        Query query = entityManager.createNativeQuery(sql,Penjualan.class);
        return query.getResultList();
    }

    @Override
    public Double totalPendapatanPerCabang(String cabang, String todayDate, String interval) {
        String sql = "select sum(total) as total_pendapatan from penjualan where nama_cabang ='" + cabang + "' " +
                "and tanggal_penjualan >= '" + todayDate + "'\\:\\:date and tanggal_penjualan < ('" + todayDate + "'\\:\\:date + '" + interval + "'\\:\\:interval)";

        Query query = entityManager.createNativeQuery(sql,Double.class);
        return (Double) query.getSingleResult();
    }

    @Override
    public String strukOtomatisPerCabang(String cabang, String todayDate) {
        String sql = "select count(total) as maxTrans from penjualan where nama_cabang ='" + cabang + "' " +
                "and tanggal_penjualan >= '" + todayDate + "'\\:\\:date and tanggal_penjualan < ('" + todayDate + "'\\:\\:date + '1 day'\\:\\:interval)";

        Query query = entityManager.createNativeQuery(sql,String.class);
        return (String) query.getSingleResult();
    }
}
