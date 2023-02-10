package com.example.quanlithuvien.DTO;

public class Book {
    private String id;
    private String theloai;
    private String tensach;
    private String tacgia;
    private String gia;
    private String sotrang;
    private String soluongton;

    public Book(String id, String theloai, String tensach, String tacgia,String gia, String sotrang, String soluongton) {
        this.id = id;
        this.theloai = theloai;
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.gia = gia;
        this.sotrang = sotrang;
        this.soluongton = soluongton;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getSotrang() {
        return sotrang;
    }

    public void setSotrang(String sotrang) {
        this.sotrang = sotrang;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(String soluongton) {
        this.soluongton = soluongton;
    }
}
