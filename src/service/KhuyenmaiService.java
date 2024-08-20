/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import Connect.DBConnect;
import java.sql.Connection;
import Model.khuyenmai;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author NGUYEN DINH BACH
 */
public class KhuyenmaiService {
    private static Connection con = null;
    private Connection c = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<khuyenmai> getAll(){
          
        sql = "SELECT * FROM KHUYENMAI";
        ArrayList<khuyenmai> getlist = new ArrayList<>();
        try {
            c = DBConnect.getConnection();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int makm, giatri;
                String tenkm, ngaybd, ngaykt, donVi;
                boolean trangthai;
                makm = rs.getInt(1);
                tenkm = rs.getString(2);
                ngaybd = rs.getString(3);
                ngaykt = rs.getString(4);
                giatri = rs.getInt(5);
                donVi = rs.getString(6);
                trangthai = rs.getBoolean(7);
                
                khuyenmai km = new khuyenmai(makm, giatri, tenkm, ngaybd, ngaykt, trangthai, donVi);
                getlist.add(km);   
            }
            return getlist;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int them(khuyenmai km) {
        sql = "INSERT INTO KHUYENMAI(TEN, NGAYBATDAU, NGAYKETTHUC, GIATRI, DONVI , TRANGTHAI) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, km.getTenkm());
            ps.setObject(2, km.getNgaybatdau());
            ps.setObject(3, km.getNgaykeythuc());
            ps.setObject(4, km.getGiatri());
                  ps.setObject(5, km.getDonVi());
            ps.setObject(6, km.isTrangthai());
      
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean xoa(int makm) {
    sql = "DELETE FROM KHUYENMAI WHERE MAKHUYENMAI = ?";
    try {
        con = DBConnect.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, makm);
        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    public int sua(int makm, khuyenmai km) {
        sql = "UPDATE KHUYENMAI SET TEN=?, NGAYBATDAU=?, NGAYKETTHUC=?, GIATRI=?, DONVI=? , TRANGTHAI=? WHERE MAKHUYENMAI=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, km.getTenkm());
            ps.setObject(2, km.getNgaybatdau());
            ps.setObject(3, km.getNgaykeythuc());
            ps.setObject(4, km.getGiatri());
                        ps.setObject(5, km.getDonVi());
            ps.setObject(6, km.isTrangthai());
            ps.setObject(7, makm);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
     public khuyenmai getKhuyenMaiByMa(String maKhuyenMai) {
        khuyenmai km = null;
        Connection cn = Connect.DBConnect.getConnection();
        String sql = "SELECT * FROM KHUYENMAI WHERE MAKHUYENMAI = '" + maKhuyenMai + "'";
        
        try {
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                km = new khuyenmai();
                km.setMakm(rs.getInt("MAKHUYENMAI"));
                km.setTenkm(rs.getString("TEN"));
                km.setGiatri(rs.getInt("GIATRI"));
                km.setDonVi(rs.getString("DONVI")); // Assuming you have a column for unit (percentage or VND)
            }
            rs.close();
            stmt.close();
            cn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return km;
    }
     
     public int getNextMaKM() {
    try {
        Connection c = DBConnect.getConnection();
        Statement s = c.createStatement();
        ResultSet r = s.executeQuery("SELECT MAX(MAKHUYENMAI) FROM KHUYENMAI");
        if (r.next()) {
            return r.getInt(1) + 1;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 1; // If there are no promotions, start with 1
}
}