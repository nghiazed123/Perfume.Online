/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Connect.DBConnect;

import java.sql.*;
import java.util.ArrayList;

import Model.HoaDonCuaBang;

public class HoaDonService {

    private static Connection c = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public HoaDonService() {
        c = DBConnect.getConnection();
    }

    public ArrayList<HoaDonCuaBang> GetAll() {
        sql = "SELECT \n"
                + "    HD.MAHD,\n"
                + "    HD.MAKH,\n"
                + "    HD.MANV,\n"
                + "    HD.MAHTTT,\n"
                + "    HD.TRANGTHAI,\n"
                + "    HD.NGAYTAO,\n"
                + "    HD.NGAYSUA\n"
                + "FROM \n"
                + "    HOADON HD" ; // Sửa điều kiện join// Sửa điều kiện join
        ArrayList<HoaDonCuaBang> LISThd = new ArrayList<>();  // Sửa kiểu dữ liệu của LISThd
        try {
            c = DBConnect.getConnection();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int maHd, makh, manv, mahttt, mahdct, soluong, dongia, tongtien, masp;
                String tensp, ngaytaosp, ngaysuahd, ngaysuasp, trangthaihd;

                Date ngaytaohd;

                maHd = rs.getInt(1);
                makh = rs.getInt(2);
                manv = rs.getInt(3);
                mahttt = rs.getInt(4);
                boolean trangthaihdvalue = rs.getBoolean(5);
                if (trangthaihdvalue) {
                    trangthaihd = "chờ thanh toán";

                } else {
                    trangthaihd = "đã thanh toán";
                }
                ngaytaohd = rs.getDate(6);
                ngaysuahd = rs.getString(7);

                HoaDonCuaBang chiTiet = new HoaDonCuaBang(maHd, makh, manv, mahttt, ngaytaohd, ngaysuahd, trangthaihd);
                LISThd.add(chiTiet);
            }
            return LISThd;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HoaDonCuaBang> Getallsp() {
        sql = "	SELECT \n"
                + "   \n"
                + "    SP.MASP,\n"
                + "   \n"
                + "    SP.TENSP, \n"
                + "    SP.NGAYTAO AS NGAYTAOSP,\n"
                + "    HDCT.MAHDCT,\n"
                + "    HDCT.SOLUONG,\n"
                + "    HDCT.DONGIA,\n"
                + "    HDCT.TONGTIEN\n"
                + "FROM \n"
                + "    HOADONCT HDCT\n"
                + "JOIN\n"
                + "    SANPHAM SP ON SP.MASP = SP.MASP";  // Sửa điều kiện join
        ArrayList<HoaDonCuaBang> LISTsp = new ArrayList<>();  // Sửa kiểu dữ liệu của LISThd
        try {
            c = DBConnect.getConnection();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int maHd, makh, manv, mahttt, mahdct, soluong, dongia, tongtien, masp;
                String ngaytaohd, tensp, ngaysuahd, trangthaihd;

                Date ngaytaosp;

                masp = rs.getInt(1);
                tensp = rs.getString(2);
                ngaytaosp = rs.getDate(3);
                mahdct = rs.getInt(4);
                soluong = rs.getInt(5);
                dongia = rs.getInt(6);
                tongtien = rs.getInt(7);

                HoaDonCuaBang chiTiet = new HoaDonCuaBang(masp, tensp, ngaytaosp, mahdct, soluong, dongia, tongtien);
                LISTsp.add(chiTiet);
            }
            return LISTsp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public ArrayList<HoaDonCuaBang> timkiem(String idcantim) {

        sql = " 	SELECT \n"
                + "    HD.MAHD,\n"
                + "    HD.MAKH,\n"
                + "    HD.MANV,\n"
                + "    HD.MAHTTT,\n"
                + "    HD.TRANGTHAI,\n"
                + "    HD.NGAYTAO,\n"
                + "    HD.NGAYSUA\n"
                + "FROM \n"
                + "    HOADON HD where HD.MAHD = ?";
        ArrayList<HoaDonCuaBang> getList = new ArrayList<>();
        try {
            c = DBConnect.getConnection();
            ps = c.prepareStatement(sql);
            ps.setObject(1, idcantim);
            rs = ps.executeQuery();
            while (rs.next()) {
                int maHd, makh, manv, mahttt, mahdct, soluong, dongia, tongtien, masp;
                String tensp, ngaytaosp, ngaysuahd, ngaysuasp, trangthaihd, trangthaisp;

                Date ngaytaohd;

                maHd = rs.getInt(1);
                makh = rs.getInt(2);
                manv = rs.getInt(3);
                mahttt = rs.getInt(4);
                boolean trangthaihdvalue = rs.getBoolean(5);
                if (trangthaihdvalue) {
                    trangthaihd = "chờ thanh toán";

                } else {
                    trangthaihd = "đã thanh toán";
                }
                ngaytaohd = rs.getDate(6);
                ngaysuahd = rs.getString(7);

                HoaDonCuaBang chiTiet = new HoaDonCuaBang(maHd, makh, manv, mahttt, ngaytaohd, ngaysuahd, trangthaihd);

                getList.add(chiTiet);
            }
            return getList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<HoaDonCuaBang> GetHoaDonCTByMaHD(int maHD) {
        ArrayList<HoaDonCuaBang> danhSachChiTiet = new ArrayList<>();
        try {
            sql = " SELECT \n"
                    + "    HD.MAHD,\n"
                    + "    HD.MAKH,\n"
                    + "    HD.MANV,\n"
                    + "    HD.MAHTTT,\n"
                    + "    HD.TRANGTHAI,\n"
                    + "    HD.NGAYTAO,\n"
                    + "    HD.NGAYSUA,\n"
                    + "    SP.MASP,\n"
                    + "    SP.TENSP,\n"
                    + "    SP.TRANGTHAI,\n"
                    + "    SP.NGAYTAO,\n"
                    + "    SP.NGAYSUA,\n"
                    + "    HDCT.MAHDCT,\n"
                    + "    HDCT.SOLUONG,\n"
                    + "    HDCT.DONGIA,\n"
                    + "    HDCT.TONGTIEN\n"
                    + "FROM \n"
                    + "    HOADON HD\n"
                    + "JOIN \n"
                    + "    HOADONCT HDCT ON HD.MAHD = HDCT.MAHD\n"
                    + "JOIN \n"
                    + "    SANPHAMCT SPCT ON HDCT.MASPCT = SPCT.MASPCT\n"
                    + "JOIN\n"
                    + "    SANPHAM SP ON SPCT.MASP = SP.MASP where HD.MAHD = ?";
            ps = c.prepareStatement(sql);
            ps.setInt(1, maHD); // Thiết lập giá trị cho tham số trong câu lệnh SQL
            rs = ps.executeQuery();

            while (rs.next()) {
                // Lấy dữ liệu từ ResultSet và tạo đối tượng HoaDonCuaBang

                int masp = rs.getInt("MASP");
                String tensp = rs.getString("TENSP");
                Date ngaytaoSP = rs.getDate("NGAYTAO");
                String ngaysuaSP = rs.getString("NGAYSUA");
                int mahdct = rs.getInt("MAHDCT");
                int soluong = rs.getInt("SOLUONG");
                int dongia = rs.getInt("DONGIA");
                int tongtien = rs.getInt("TONGTIEN");

                HoaDonCuaBang chiTietHD = new HoaDonCuaBang(
                        masp, tensp, ngaytaoSP, mahdct, soluong, dongia, tongtien
                );

                danhSachChiTiet.add(chiTietHD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi ở đây
        } finally {
            // Đóng ResultSet và PreparedStatement nếu không còn sử dụng
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
                // Không đóng Connection ở đây nếu nó được quản lý ở nơi khác
            } catch (SQLException e) {
                e.printStackTrace();
                // Xử lý lỗi đóng tài nguyên ở đây
            }
        }

        return danhSachChiTiet;
    }
    
    public int getTongSoHoaDon(String filterType, String filterValue) {
        int tongSoHoaDon = 0;
        try {
            c = DBConnect.getConnection();
            String sql = "SELECT COUNT(*) AS tongSoHoaDon FROM HOADON" + buildHoaDonFilterQuery(filterType, filterValue);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                tongSoHoaDon = rs.getInt("tongSoHoaDon");
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongSoHoaDon;
    }
    
    public int getTongSoHoaDonThanhCong(String filterType, String filterValue) {
        return getTongSoHoaDonTheoTrangThai(1, filterType, filterValue); // Trạng thái thành công là 1
    }

    // Phương thức lấy tổng số hóa đơn hủy theo ngày, tháng, năm
    public int getTongSoHoaDonHuy(String filterType, String filterValue) {
        return getTongSoHoaDonTheoTrangThai(0, filterType, filterValue); // Trạng thái hủy là 0
    }

    // Phương thức lấy tổng số hóa đơn theo trạng thái và ngày, tháng, năm
    private int getTongSoHoaDonTheoTrangThai(int trangThai, String filterType, String filterValue) {
        int tongSo = 0;
        try {
            c = DBConnect.getConnection();
            String sql = "SELECT COUNT(*) AS tongSo FROM HOADON WHERE TRANGTHAI = " + trangThai
                    + buildHoaDonFilterQuery(filterType, filterValue);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                tongSo = rs.getInt("tongSo");
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongSo;
    }

    // Phương thức lấy tổng doanh thu theo ngày, tháng, năm
    public int getTongDoanhThu(String filterType, String filterValue) {
        int tongDoanhThu = 0;
        try {
            c = DBConnect.getConnection();
            String sql = "SELECT SUM(TONGTIEN) AS tongDoanhThu FROM HOADON WHERE TRANGTHAI = 1" + buildHoaDonFilterQuery(filterType, filterValue);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                tongDoanhThu = rs.getInt("tongDoanhThu");
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongDoanhThu;
    }

    // Phương thức xây dựng điều kiện WHERE cho câu truy vấn dựa vào filterType và filterValue
    private String buildHoaDonFilterQuery(String filterType, String filterValue) {
        String condition = "";
        switch (filterType) {
            case "Ngày":
                condition = " WHERE DAY(NGAYTAO) = " + filterValue;
                break;
            case "Tháng":
                condition = " WHERE MONTH(NGAYTAO) = " + filterValue;
                break;
            case "Năm":
                condition = " WHERE YEAR(NGAYTAO) = " + filterValue;
                break;
        }
        return condition;
    }

}
