/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Moch Billy Refanto
 */
public class DataInventaris {
    private int idInventaris,qtyData;
    private String namaBarang,merekBarang,keterangan,satuan,kondisi,hargaBarang;

    public DataInventaris(int idInventaris, int qtyData, String namaBarang, String merekBarang, String keterangan, String satuan, String kondisi, String hargaBarang) {
        this.idInventaris = idInventaris;
        this.qtyData = qtyData;
        this.namaBarang = namaBarang;
        this.merekBarang = merekBarang;
        this.keterangan = keterangan;
        this.satuan = satuan;
        this.kondisi = kondisi;
        this.hargaBarang = hargaBarang;
    }

    public int getIdInventaris() {
        return idInventaris;
    }

    public int getQtyData() {
        return qtyData;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public String getMerekBarang() {
        return merekBarang;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getSatuan() {
        return satuan;
    }

    public String getKondisi() {
        return kondisi;
    }

    public String getHargaBarang() {
        return hargaBarang;
    }
    
    
}
