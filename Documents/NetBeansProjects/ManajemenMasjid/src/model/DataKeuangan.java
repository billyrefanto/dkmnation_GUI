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
public class DataKeuangan {
    private int idData,nominal;
    private String kategoriData,keteranganData,tanggalData;

    public DataKeuangan(int idData, int nominal, String kategoriData, String keteranganData, String tanggalData) {
        this.idData = idData;
        this.nominal = nominal;
        this.kategoriData = kategoriData;
        this.keteranganData = keteranganData;
        this.tanggalData = tanggalData;
    }

    public int getIdData() {
        return idData;
    }

    public int getNominal() {
        return nominal;
    }

    public String getKategoriData() {
        return kategoriData;
    }

    public String getKeteranganData() {
        return keteranganData;
    }

    public String getTanggalData() {
        return tanggalData;
    }
    
    
}
