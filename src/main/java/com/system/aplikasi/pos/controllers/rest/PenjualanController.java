package com.system.aplikasi.pos.controllers.rest;

import com.system.aplikasi.pos.model.entity.Penjualan;
import com.system.aplikasi.pos.repository.PenjualanDetailRepository;
import com.system.aplikasi.pos.repository.PenjualanRepository;
import com.system.aplikasi.pos.repository.implementation.NativeQuery;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/penjualan")
public class PenjualanController {
    @Autowired
    private PenjualanRepository penjualanRepository;
    @Autowired
    private PenjualanDetailRepository penjualanDetailRepository;
    @Autowired
    private NativeQuery nativeQuery;

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        try {
            Iterable<Penjualan> results = penjualanRepository.findAll();
            return ResponseEntity.ok().body(results);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/transaksi-cabang/{namaCabang}/{interval}")
    public ResponseEntity<?> transaksiCabang(@PathVariable String namaCabang, @PathVariable String interval) {
        try {
            String todayDate = sdf.format(new Date());
            List<Penjualan> results = nativeQuery.getTransactionPerCabang(namaCabang, todayDate, interval);
            if (results.isEmpty()) {
                return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok().body(results);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pendapatan-cabang/{namaCabang}/{interval}")
    public ResponseEntity<?> pendapatanCabang(@PathVariable String namaCabang, @PathVariable String interval) {
        try {
            String todayDate = sdf.format(new Date());
            Double totalPendapatanPerCabang = nativeQuery.totalPendapatanPerCabang(namaCabang, todayDate, interval);
            if (totalPendapatanPerCabang==null) {
                return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok().body(totalPendapatanPerCabang);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nostruk/{namaCabang}")
    public ResponseEntity<?> noStruk(@PathVariable String namaCabang) {
        String noStrukOtomatis = "PEL";
        try {
            String todayDate = sdf.format(new Date());
            String noStruk = nativeQuery.strukOtomatisPerCabang(namaCabang, todayDate);
            if (noStruk==null) {
                return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND);
            }
            noStrukOtomatis = noStrukOtomatis+todayDate.replace("-","")+noStruk;
            return ResponseEntity.ok().body(noStrukOtomatis);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
            examples = {
                    @ExampleObject(value = "{" +
                            "    \"namaCabang\": \"Cabang 2\"," +
                            "    \"noPelangan\": \"PEL1\"," +
                            "    \"penjualanDetail\": [" +
                            "      {" +
                            "        \"namaBarang\": \"ORIGINAL TEA\"," +
                            "        \"harga\": 2000," +
                            "        \"qty\": 1" +
                            "      }," +
                            "      {" +
                            "        \"namaBarang\": \"THAI TEA\"," +
                            "        \"harga\": 7000," +
                            "        \"qty\": 1" +
                            "      }" +
                            "    ]" +
                            "  }")
            }))@RequestBody Penjualan penjualan) {
        try {
            Double total = 0d;
            for (int i = 0; i < penjualan.getPenjualanDetail().size(); i++) {
                Double harga = penjualan.getPenjualanDetail().get(i).getHarga() * penjualan.getPenjualanDetail().get(i).getQty();
                total = total + harga;
            }
            penjualan.setTotal(total);
            penjualan.setTanggalPenjualan(new Date());
            Penjualan save = penjualanRepository.save(penjualan);
            penjualanDetailRepository.saveAll(save.getPenjualanDetail());
            return ResponseEntity.ok().body(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
