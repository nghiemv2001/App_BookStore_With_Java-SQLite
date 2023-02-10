package com.example.quanlithuvien.DTO;

public class KhachHangDTO {
    private String makhachhang;
    private String tenkhachhang;
    private String sodientgoai;
    private String ngay;
    private String thang;
    private String nam;
    private String gio;
    private String tongtien;
    private String tennhanvien;

    public KhachHangDTO(String makhachhang, String tenkhachhang, String sodientgoai, String ngay, String gio, String tongtien) {
        this.makhachhang = makhachhang;
        this.tenkhachhang = tenkhachhang;
        this.sodientgoai = sodientgoai;
        this.ngay = ngay;
        this.gio = gio;
        this.tongtien = tongtien;
    }

    public KhachHangDTO(String tenkhachhang, String sodientgoai, String ngay, String thang, String nam, String gio, String tongtien, String tennhanvien) {
        this.tenkhachhang = tenkhachhang;
        this.sodientgoai = sodientgoai;
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
        this.gio = gio;
        this.tongtien = tongtien;
        this.tennhanvien = tennhanvien;
    }

    public KhachHangDTO(String tenkhachhang, String sodientgoai) {
        this.tenkhachhang = tenkhachhang;
        this.sodientgoai = sodientgoai;
    }

    public String getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(String makhachhang) {
        this.makhachhang = makhachhang;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public String getSodientgoai() {
        return sodientgoai;
    }

    public void setSodientgoai(String sodientgoai) {
        this.sodientgoai = sodientgoai;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getTennhanvien() {
        return tennhanvien;
    }

    public void setTennhanvien(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }
}


