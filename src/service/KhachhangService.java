/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Connect.DBConnect;
import Model.khachhang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author NGUYEN DINH BACH
 */
public class KhachhangService {

     ArrayList<khachhang> list = new ArrayList<>();

    public ArrayList<khachhang> getAll() {
        ArrayList<khachhang> list = new ArrayList<>();
        try {
            Connection con = DBConnect.getConnection();
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM KHACHHANG");
            while (r.next()) {
                khachhang a = new khachhang();
                a.setMakh(r.getInt(1));
                a.setTen(r.getString(2));
                a.setNgaysinh(r.getString(3));
                a.setGioitinh(r.getString(4));
                a.setSdt(r.getString(5));
                a.setDiachi(r.getString(6));
                a.setEmail(r.getString(7));
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(khachhang a) {
        PreparedStatement ps = null;
        Connection c = null;
        try {
            c = DBConnect.getConnection();
            ps = c.prepareStatement("INSERT INTO KHACHHANG (TENKH,NGAYSINH,GIOITINH,SDT,DIACHI,EMAIL) VALUES (?,?,?,?,?,?)");
            ps.setString(1, a.getTen());
            ps.setString(2, a.getNgaysinh());
            ps.setString(3, a.getGioitinh());
            ps.setString(4, a.getSdt());
            ps.setString(5, a.getDiachi());
            ps.setString(6, a.getEmail());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int makh) {
        try {
            Connection c = DBConnect.getConnection();
            PreparedStatement p = c.prepareStatement("DELETE FROM KHACHHANG WHERE MAKH = ?");
            p.setInt(1, makh);
            p.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean edit(khachhang a) {
        try {
            Connection c = DBConnect.getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE KHACHHANG SET TENKH=?,NGAYSINH=?,GIOITINH=?,SDT=?,DIACHI=?,EMAIL=? WHERE MAKH=?");
            ps.setString(1, a.getTen());
            ps.setString(2, a.getNgaysinh());
            ps.setString(3, a.getGioitinh());
            ps.setString(4, a.getSdt());
            ps.setString(5, a.getDiachi());
            ps.setString(6, a.getEmail());
            ps.setInt(7, a.getMakh());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getNextMakh() {
        try {
            Connection c = DBConnect.getConnection();
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT MAX(MAKH) FROM KHACHHANG");
            if (r.next()) {
                return r.getInt(1) + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1; // If there are no customers, start with 1
    }
}
