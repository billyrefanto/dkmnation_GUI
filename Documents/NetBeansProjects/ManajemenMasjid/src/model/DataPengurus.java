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
public class DataPengurus {

    private int idData;
    private String namaPengurusData, jenisKelaminData, tanggalLahir, kontak, alamatData, jabatanData, statusData;

    public DataPengurus(int idData, String namaPengurusData, String jenisKelaminData, String tanggalLahir, String kontak, String alamatData, String jabatanData, String statusData) {
        this.idData = idData;
        this.namaPengurusData = namaPengurusData;
        this.jenisKelaminData = jenisKelaminData;
        this.tanggalLahir = tanggalLahir;
        this.kontak = kontak;
        this.alamatData = alamatData;
        this.jabatanData = jabatanData;
        this.statusData = statusData;
    }

    public int getIdData() {
        return idData;
    }

    public String getNamaPengurusData() {
        return namaPengurusData;
    }

    public String getJenisKelaminData() {
        return jenisKelaminData;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public String getKontak() {
        return kontak;
    }

    public String getAlamatData() {
        return alamatData;
    }

    public String getJabatanData() {
        return jabatanData;
    }

    public String getStatusData() {
        return statusData;
    }
    
    
    

}
