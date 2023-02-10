package com.example.quanlithuvien.DTO;

public class ThanhToanDTO {
    String id;
    private String ten;
    private String soluong;
    private String gia;

    public ThanhToanDTO(String ten, String soluong, String gia) {
        this.ten = ten;
        this.soluong = soluong;
        this.gia = gia;
    }

    public ThanhToanDTO(String id, String ten, String soluong, String gia) {
        this.id = id;
        this.ten = ten;
        this.soluong = soluong;
        this.gia = gia;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
