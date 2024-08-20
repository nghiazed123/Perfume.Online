/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Connect.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.ThongKe;

/**
 *
 * @author 84347
 */
public class ThongKeService {
    

    private final  HoaDonService hoaDonService = new HoaDonService();

    public ArrayList<ThongKe> getAllThongKe(String filterType, String filterValue) {
    ArrayList<ThongKe> lstTK = new ArrayList<>();
    Connection cn = Connect.DBConnect.getConnection();
    String sql = "SELECT * FROM THONGKE_DOANHSO TD JOIN SANPHAM SP ON TD.MASP = SP.MASP";
    
    switch (filterType) {
        case "Ngày":
            sql += " WHERE DAY(TD.NGAYTHONGKE) = " + filterValue;
            break;
        case "Tháng":
            sql += " WHERE MONTH(TD.NGAYTHONGKE) = " + filterValue;
            break;
        case "Năm":
            sql += " WHERE YEAR(TD.NGAYTHONGKE) = " + filterValue;
            break;
    }

    Statement stm;
    try {
        stm = cn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            ThongKe tk = new ThongKe();
            tk.setMaTK(rs.getInt("MATHONGKE"));
            tk.setNgayTK(rs.getDate("NGAYTHONGKE"));
            tk.setTenSP(rs.getString("TENSP"));
            tk.setSoLuong(rs.getInt("SOLUONGBAN"));
            tk.setDoanhThu(rs.getInt("TONGDOANHSO"));

            lstTK.add(tk);
        }
        
        // Close resources
        rs.close();
        stm.close();
        cn.close();
        
    } catch (SQLException ex) {
        Logger.getLogger(ThongKeService.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    // Display the statistics in a table
    new ThongKeService();
    
    return lstTK;
}

    
    
    

    private String buildHoaDonFilterQuery(String filterType, String filterValue) {
        String condition = "";
        switch (filterType) {
            case "Ngày":
                condition = " WHERE DAY(NGAYTHONGKE) = " + filterValue;
                break;
            case "Tháng":
                condition = " WHERE MONTH(NGAYTHONGKE) = " + filterValue;
                break;
            case "Năm":
                condition = " WHERE YEAR(NGAYTHONGKE) = " + filterValue;
                break;
        }
        return condition;
    }

    public int getTongSoHoaDon(String filterType, String filterValue) {
        int tongSoHoaDon = 0;
        try {
            Connection cn = Connect.DBConnect.getConnection();
            String sql = "SELECT COUNT(*) AS tongSoHoaDon FROM HOADON" + buildHoaDonFilterQuery(filterType, filterValue);
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                tongSoHoaDon = rs.getInt("tongSoHoaDon");
            }

            rs.close();
            stmt.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return tongSoHoaDon;
        return hoaDonService.getTongSoHoaDon(filterType, filterValue);
    }

public int getTongSoHoaDonThanhCong(String filterType, String filterValue) {
//    return getTongSoHoaDonTheoTrangThai(1, filterType, filterValue); 
return hoaDonService.getTongSoHoaDon(filterType, filterValue);// Trạng thái thành công là 1
}

public int getTongSoHoaDonHuy(String filterType, String filterValue) {
//    return getTongSoHoaDonTheoTrangThai(0, filterType, filterValue);
return hoaDonService.getTongSoHoaDonHuy(filterType, filterValue);// Trạng thái hủy là 0
}
    private int getTongSoHoaDonTheoTrangThai(int trangThai, String filterType, String filterValue) {
        int tongSo = 0;
        try {
            Connection cn = Connect.DBConnect.getConnection();
            String sql = "SELECT COUNT(*) AS tongSo FROM HOADON WHERE TRANGTHAI = " + trangThai 
                         + buildHoaDonFilterQuery(filterType, filterValue);
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                tongSo = rs.getInt("tongSo");
            }

            rs.close();
            stmt.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return tongSo;
return hoaDonService.getTongSoHoaDonThanhCong(filterType, filterValue);
    }
    
    public int getTongDoanhThu(String filterType, String filterValue) {
        return hoaDonService.getTongDoanhThu(filterType, filterValue);
    }
}
