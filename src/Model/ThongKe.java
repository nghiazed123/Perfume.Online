/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author 84347
 */
public class ThongKe {
        private int maTK;
        private Date NgayTK;
        private String tenSP;
        private int soLuong;
        private int doanhThu;

    public ThongKe() {
    }

    public ThongKe(int maTK, Date NgayTK, String tenSP, int soLuong, int doanhThu) {
        this.maTK = maTK;
        this.NgayTK = NgayTK;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.doanhThu = doanhThu;
    }

    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public Date getNgayTK() {
        return NgayTK;
    }

    public void setNgayTK(Date NgayTK) {
        this.NgayTK = NgayTK;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(int doanhThu) {
        this.doanhThu = doanhThu;
    }

    
    
}
