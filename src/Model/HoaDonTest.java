/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author NGUYEN DINH BACH
 */
public class HoaDonTest {

    private int maHD;
    private int maKH;
    private int maNV;
    private int maHTTT;
    private Boolean trangThai;
    private String ngayTao;

    public HoaDonTest(int maHD1, String trangThai1, String ngaytao) {
    }

    public HoaDonTest(int maHD, int maKH, int maNV, int maHTTT, Boolean trangThai, String ngayTao) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.maNV = maNV;
        this.maHTTT = maHTTT;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getMaHTTT() {
        return maHTTT;
    }

    public void setMaHTTT(int maHTTT) {
        this.maHTTT = maHTTT;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public boolean isTrangThai() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  

  

   
}
