/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Connect.DBConnect;
import Model.SanPhamBang;
import Model.ThuocTinh;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import service.SanPhamServiceBang;
import service.ThuocTinhService;

/**
 *
 * @author NGUYEN DINH BACH
 */
public class formsanpham extends javax.swing.JPanel {

    private ThuocTinhService tts = new ThuocTinhService();
     private final SanPhamServiceBang sps = new SanPhamServiceBang();
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel model1 = new DefaultTableModel();
    private int index;
    String strHinh = null;
    
    public formsanpham() {
         initComponents();
        addRadioButtonsListener();
        model1 = (DefaultTableModel) tblThuocTinh.getModel();
         
        fillSanPhamComboBox();
        fillMuiHuongComboBox();
        fillThuongHieuComboBox();
        model = (DefaultTableModel) tblSanPham.getModel();
        loadDataToTable();
        if(tblSanPham.getRowCount()>0){
            index = 0;
            showsanpham();
        }

    }
  
public void loadDataToTable() {
        ArrayList<SanPhamBang> lstSanPham = sps.getAllSanPham();
        model.setRowCount(0);
        for (SanPhamBang sp : lstSanPham) {
            model.addRow(new Object[]{sp.getMaSP(),sp.getTenSp(),sp.getDonGia(),sp.getSoLuong(),sp.getDungTich(),sp.getMuiHuong(),sp.getThuongHieu(),sp.isTrangThai()?"Còn hàng":"Hết hàng",sp.getNgayTao(),sp.getNgaySua()});
        }
    }
    
    private void fillSanPhamComboBox(){
        DefaultComboBoxModel<String> thModel = sps.getAllTenSp();
        cboTenSp.setModel(thModel);
    }
    
    private void fillThuongHieuComboBox(){
        DefaultComboBoxModel<String> thModel = sps.getAllThuongHieu();
        cboThuongHieu.setModel(thModel);
    }
    
    
    private void fillMuiHuongComboBox(){
        DefaultComboBoxModel<String> mhModel = sps.getAllMuiHuong();
        cboMuiHuong.setModel(mhModel);
    }
    
    
//    thuộc tính
    public void loadTenSpToTable(){
        ArrayList<ThuocTinh> lstTenSp = tts.getAllTenSP();
        model1.setRowCount(0);
        for (ThuocTinh tsp : lstTenSp) {
            model1.addRow(new Object[]{tsp.getStt(),tsp.getLoaiTt(),tsp.getTenTt()});
        }
    }
    
    public void loadThuongHieuToTable(){
        ArrayList<ThuocTinh> lstTenSp = tts.getAllThuongHieu();
        model1.setRowCount(0);
        for (ThuocTinh tsp : lstTenSp) {
            model1.addRow(new Object[]{tsp.getStt(),tsp.getLoaiTt(),tsp.getTenTt()});
        }
    }
    
    public void loadMuiHuongToTable(){
        ArrayList<ThuocTinh> lstTenSp = tts.getAllMuiHuong();
        model1.setRowCount(0);
        for (ThuocTinh tsp : lstTenSp) {
            model1.addRow(new Object[]{tsp.getStt(),tsp.getLoaiTt(),tsp.getTenTt()});
        }
    }
    
    public void loadHinhAnhToTable(){
        ArrayList<ThuocTinh> lstTenSp = tts.getAllHinhAnh();
        model1.setRowCount(0);
        for (ThuocTinh tsp : lstTenSp) {
            model1.addRow(new Object[]{tsp.getStt(),tsp.getLoaiTt(),tsp.getTenTt()});
        }
    }
    
    private void addRadioButtonsListener() {
        rdoTenSp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTenSpToTable();
            }
        });
        
        rdoThuongHieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadThuongHieuToTable();
            }
        });

        rdoMuiHuong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadMuiHuongToTable();
            }
        });
        
    }
    
    
        
    
    public void showsanpham(){
     txtMaSp.setText(tblSanPham.getValueAt(index, 0).toString());
        String TenSp = tblSanPham.getValueAt(index, 1).toString();
       cboTenSp.setSelectedItem(TenSp);
        txtDonGia.setText(tblSanPham.getValueAt(index, 2).toString());
        txtSoLuong.setText(tblSanPham.getValueAt(index, 3).toString());
        txtDungTich.setText(tblSanPham.getValueAt(index, 4).toString());
        String muiHuong = tblSanPham.getValueAt(index, 5).toString();
       cboMuiHuong.setSelectedItem(muiHuong);
       String thuongHieu = tblSanPham.getValueAt(index, 6).toString();
       cboThuongHieu.setSelectedItem(thuongHieu);
       String trangThai = tblSanPham.getValueAt(index, 7).toString();
       cboTrangThai.setSelectedItem(trangThai);
    }
    
    
    public boolean checkDuLieu() {
    try {
        // Check if Don Gia is empty or less than 0
        if (txtDonGia.getText().trim().isEmpty() || Integer.parseInt(txtDonGia.getText().trim()) < 0) {
            JOptionPane.showMessageDialog(null, "Đơn giá không được nhỏ hơn 0", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Check if So Luong is empty or less than 0
        if (txtSoLuong.getText().trim().isEmpty() || Integer.parseInt(txtSoLuong.getText().trim()) < 0) {
            JOptionPane.showMessageDialog(null, "Số Lượng không được nhỏ hơn 0", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Check if Dung Tich is empty or less than 0
        if (txtDungTich.getText().trim().isEmpty() || Integer.parseInt(txtDungTich.getText().trim()) < 0) {
            JOptionPane.showMessageDialog(null, "Dung tích không được nhỏ hơn 0", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng cho dữ liệu giá, số lượng và dung tích", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return false;
    }

    return true;
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel8 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtDungTich = new javax.swing.JTextField();
        cboMuiHuong = new javax.swing.JComboBox<>();
        cboThuongHieu = new javax.swing.JComboBox<>();
        cboTrangThai = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtMaSp = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        cboTenSp = new javax.swing.JComboBox<>();
        txtDonGia = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnlammoi1 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblThuocTinh = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        rdoMuiHuong = new javax.swing.JRadioButton();
        rdoThuongHieu = new javax.swing.JRadioButton();
        rdoTenSp = new javax.swing.JRadioButton();
        btnThemThuocTinh = new javax.swing.JButton();
        btnSuaThuocTinh = new javax.swing.JButton();
        btnXoaThuocTinh = new javax.swing.JButton();
        txtThuocTinh = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1346, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng Sản Phẩm"));

        jLabel1.setText("Tìm Kiếm :");

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Dung tích", "Mùi hương", "Thương hiệu", "Trạng thái", "Ngày tạo", "Ngày sửa"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(968, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(258, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(37, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel32.setText("Dung Tích :");

        jLabel33.setText("Mùi Hương :");

        jLabel34.setText("Trạng Thái :");

        jLabel35.setText("Thương Hiệu :");

        cboMuiHuong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hương gỗ", "Hương vani" }));

        cboThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gucci", "Dior", " " }));

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn hàng", "Hết hàng" }));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDungTich)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cboMuiHuong, 0, 667, Short.MAX_VALUE)
                    .addComponent(cboTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboThuongHieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDungTich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboMuiHuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel11.setText("Mã SP:");

        jLabel13.setText("Tên SP :");

        jLabel18.setText("Đơn Giá :");

        txtMaSp.setEditable(false);

        jLabel19.setText("Số Lượng :");

        cboTenSp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(255, 349, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDonGia)
                            .addComponent(txtMaSp, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSoLuong)
                            .addComponent(cboTenSp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboTenSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Awicons-Vista-Artistic-Add.24.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnlammoi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/cleaning.png"))); // NOI18N
        btnlammoi1.setText("Làm Mới");
        btnlammoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoi1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnlammoi1, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addGap(18, 18, 18)
                .addComponent(btnXoa)
                .addGap(18, 18, 18)
                .addComponent(btnlammoi1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 69, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane2.addTab("Sản Phẩm", jPanel9);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng Khuyến Mãi"));

        tblThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Số thứ tự", "Loại thuộc tính", "Tên thuộc tính"
            }
        ));
        tblThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblThuocTinh);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1330, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        rdoMuiHuong.setText("Mùi hương");

        rdoThuongHieu.setText("Thương hiệu");

        rdoTenSp.setText("Tên sản phẩm");

        btnThemThuocTinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Awicons-Vista-Artistic-Add.24.png"))); // NOI18N
        btnThemThuocTinh.setText("Thêm");
        btnThemThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuocTinhActionPerformed(evt);
            }
        });

        btnSuaThuocTinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Edit.png"))); // NOI18N
        btnSuaThuocTinh.setText("Sửa");
        btnSuaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThuocTinhActionPerformed(evt);
            }
        });

        btnXoaThuocTinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Delete.png"))); // NOI18N
        btnXoaThuocTinh.setText("Xóa");
        btnXoaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaThuocTinhActionPerformed(evt);
            }
        });

        jLabel6.setText("Ô nhập dữ kiệu :");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(41, 41, 41)
                        .addComponent(txtThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addGap(218, 218, 218)
                            .addComponent(btnThemThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(231, 231, 231)
                            .addComponent(btnSuaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addGap(387, 387, 387)
                            .addComponent(rdoTenSp, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(174, 174, 174)
                            .addComponent(rdoThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(104, 104, 104)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoMuiHuong, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(42, 42, 42)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoTenSp)
                    .addComponent(rdoThuongHieu)
                    .addComponent(rdoMuiHuong))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemThuocTinh)
                    .addComponent(btnSuaThuocTinh)
                    .addComponent(btnXoaThuocTinh))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Thuộc Tính", jPanel10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 622, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:DefaultTableModel dtm  = (DefaultTableModel) tblNhanVien.getModel();
        TableRowSorter<DefaultTableModel> ab = new TableRowSorter<>(model);
        tblSanPham.setRowSorter(ab);
        ab.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        index = tblSanPham.getSelectedRow();
        showsanpham();
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
       try {
           if (txtDonGia.getText().isEmpty() || 
        txtDungTich.getText().isEmpty() || 
        txtSoLuong.getText().isEmpty() || 
        cboMuiHuong.getSelectedItem() == null || 
        cboTenSp.getSelectedItem() == null || 
        cboThuongHieu.getSelectedItem() == null || 
        cboTrangThai.getSelectedItem() == null) {
        
        JOptionPane.showMessageDialog(this, "Không được bỏ trống dữ liệu");
        return;
    }
        // Validate data
        if (!checkDuLieu()) {
            return;
        }

        // Gather data
        String tenSP = cboTenSp.getSelectedItem().toString();
        int donGia = Integer.parseInt(txtDonGia.getText());
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        int dungTich = Integer.parseInt(txtDungTich.getText());
        String muiHuong = cboMuiHuong.getSelectedItem().toString();
        String thuongHieu = cboThuongHieu.getSelectedItem().toString();
        boolean trangThai = cboTrangThai.getSelectedItem().toString().equals("Còn hàng"); // Chuyển đổi kiểu int sang boolean
        Date ngayTao = new Date(); // Sử dụng ngày hiện tại cho ngày tạo
        Date ngaySua = new Date(); // Sử dụng ngày hiện tại cho ngày sửa

        SanPhamBang sanPham = new SanPhamBang(0, tenSP, donGia, soLuong, dungTich, muiHuong, thuongHieu, trangThai, ngayTao, ngaySua);

        // Add the product and check the result
        boolean result = sps.add(sanPham);
        if (result) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
            loadDataToTable(); // Update the table after adding
            clearFields(); // Reset fields after successful additio
    setNewProductId();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng cho dữ liệu giá, số lượng và dung tích");
    }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
       try {
           if (txtDonGia.getText().isEmpty() || 
        txtDungTich.getText().isEmpty() || 
        txtSoLuong.getText().isEmpty() || 
        cboMuiHuong.getSelectedItem() == null || 
        cboTenSp.getSelectedItem() == null || 
        cboThuongHieu.getSelectedItem() == null || 
        cboTrangThai.getSelectedItem() == null) {
        
        JOptionPane.showMessageDialog(this, "Không được bỏ trống dữ liệu");
        return;
    }
        // Validate data
        if (!checkDuLieu()) {
            return;
        }

        // Gather data
        int maSP = Integer.parseInt(txtMaSp.getText());
        String tenSP = cboTenSp.getSelectedItem().toString();
        int donGia = Integer.parseInt(txtDonGia.getText());
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        int dungTich = Integer.parseInt(txtDungTich.getText());
        String muiHuong = cboMuiHuong.getSelectedItem().toString();
        String thuongHieu = cboThuongHieu.getSelectedItem().toString();
        boolean trangThai = cboTrangThai.getSelectedItem().toString().equals("Còn hàng"); // Chuyển đổi kiểu int sang boolean
        Date ngayTao = new Date(); // Sử dụng ngày hiện tại cho ngày tạo
        Date ngaySua = new Date(); // Sử dụng ngày hiện tại cho ngày sửa

        SanPhamBang sanPham = new SanPhamBang(maSP, tenSP, donGia, soLuong, dungTich, muiHuong, thuongHieu, trangThai, ngayTao, ngaySua);

        // Update the product and check the result
        boolean result = sps.update(sanPham);
        if (result) {
            JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadDataToTable();
            clearFields();
            setNewProductId();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng cho dữ liệu giá, số lượng và dung tích");
    }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
int selectedRow = tblSanPham.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xóa");
        return;
    }

    int maSP = (int) tblSanPham.getValueAt(selectedRow, 0);
    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        if (sps.delete(maSP)) {
            loadDataToTable();
            JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa sản phẩm thất bại");
        }
    }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnlammoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoi1ActionPerformed
clearFields();
    setNewProductId();
    }//GEN-LAST:event_btnlammoi1ActionPerformed

    
    private void clearFields() {
    txtMaSp.setText("");
    cboTenSp.setSelectedIndex(-1);
    txtDonGia.setText("");
    txtSoLuong.setText("");
    txtDungTich.setText("");
    cboMuiHuong.setSelectedIndex(-1);
    cboThuongHieu.setSelectedIndex(-1);
    cboTrangThai.setSelectedIndex(-1);
}

private void setNewProductId() {
    int newProductId = sps.getNewProductId();
    txtMaSp.setText(String.valueOf(newProductId));
}

    private void btnThemThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuocTinhActionPerformed
         String tenThuocTinh = txtThuocTinh.getText().trim();
if (tenThuocTinh.isEmpty()) {
    JOptionPane.showMessageDialog(formsanpham.this, "Vui lòng nhập giá trị thuộc tính!");
    return; // Exit the method if validation fails
}
        // Kiểm tra xem radio button nào đang được chọn
        if (rdoTenSp.isSelected()) {
            // Thêm thuộc tính vào bảng SANPHAM
            boolean result = tts.themTenSP(tenThuocTinh);
            if (result) {
                JOptionPane.showMessageDialog(formsanpham.this, "Thêm thành công!");
                // Reload bảng sau khi thêm thành công
                loadTenSpToTable();
            } else {
                JOptionPane.showMessageDialog(formsanpham.this, "Thêm thất bại!");
            }

        } else if (rdoThuongHieu.isSelected()) {
            // Thêm thuộc tính vào bảng THUONGHIEU
            boolean result = tts.themThuongHieu(tenThuocTinh);
            if (result) {
                JOptionPane.showMessageDialog(formsanpham.this, "Thêm thành công!");
                // Reload bảng sau khi thêm thành công
                loadThuongHieuToTable();
            } else {
                JOptionPane.showMessageDialog(formsanpham.this, "Thêm thất bại!");
            }

        } else if (rdoMuiHuong.isSelected()) {
            // Thêm thuộc tính vào bảng MUIHUONG
            boolean result = tts.themMuiHuong(tenThuocTinh);
            if (result) {
                JOptionPane.showMessageDialog(formsanpham.this, "Thêm thành công!");
                // Reload bảng sau khi thêm thành công
                loadMuiHuongToTable();
            } else {
                JOptionPane.showMessageDialog(formsanpham.this, "Thêm thất bại!");
            }

        }
    }//GEN-LAST:event_btnThemThuocTinhActionPerformed

    private void btnSuaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThuocTinhActionPerformed
       String tenThuocTinh = txtThuocTinh.getText().trim();

// Kiểm tra nếu chuỗi là trống, hiển thị thông báo và không thực hiện sửa
if (tenThuocTinh.isEmpty()) {
    JOptionPane.showMessageDialog(formsanpham.this, "Bạn phải nhập dữ liệu cho 'Tên thuộc tính'. Việc sửa không thể để trống.");
    return; // Thoát phương thức
}

// Lấy giá trị từ bảng và kiểm tra kiểu dữ liệu trước khi ép kiểu
Object cellValue = tblThuocTinh.getValueAt(index, 0); // Giả sử đây là cột 'Số thứ tự'
int maThuocTinh;

// Đoạn code sau giả định cellValue luôn là một số, nếu không, bạn cần kiểm tra và xử lý nó tương tự như đã làm ở trên
maThuocTinh = Integer.parseInt(cellValue.toString());

// Các đoạn kiểm tra radio button và sửa dữ liệu như trên
if (rdoTenSp.isSelected()) {
    // Sửa thuộc tính trong bảng SANPHAM...
    // Các đoạn code còn lại giống như đã cho ở trên
}

// Kiểm tra xem radio button nào đang được chọn
if (rdoTenSp.isSelected()) {
    // Sửa thuộc tính trong bảng SANPHAM
    boolean result = tts.suaTenSP(maThuocTinh, tenThuocTinh);
    if (result) {
        JOptionPane.showMessageDialog(formsanpham.this, "Sửa thành công!");
        // Reload bảng sau khi sửa thành công
        loadTenSpToTable();
    } else {
        JOptionPane.showMessageDialog(formsanpham.this, "Sửa thất bại!");
    }
} else if (rdoThuongHieu.isSelected()) {
    // Sửa thuộc tính trong bảng THUONGHIEU
    boolean result = tts.suaThuongHieu(maThuocTinh, tenThuocTinh);
    if (result) {
        JOptionPane.showMessageDialog(formsanpham.this, "Sửa thành công!");
        // Reload bảng sau khi sửa thành công
        loadThuongHieuToTable();
    } else {
        JOptionPane.showMessageDialog(formsanpham.this, "Sửa thất bại!");
    }
} else if (rdoMuiHuong.isSelected()) {
    // Sửa thuộc tính trong bảng MUIHUONG
    boolean result = tts.suaMuiHuong(maThuocTinh, tenThuocTinh);
    if (result) {
        JOptionPane.showMessageDialog(formsanpham.this, "Sửa thành công!");
        // Reload bảng sau khi sửa thành công
        loadMuiHuongToTable();
    } else {
        JOptionPane.showMessageDialog(formsanpham.this, "Sửa thất bại!");
    }
} 
    }//GEN-LAST:event_btnSuaThuocTinhActionPerformed

    private void btnXoaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThuocTinhActionPerformed
        
    }//GEN-LAST:event_btnXoaThuocTinhActionPerformed

    
    private void reloadThuocTinhTable() {
        if (rdoTenSp.isSelected()) {
            loadTenSpToTable();
        } else if (rdoThuongHieu.isSelected()) {
            loadThuongHieuToTable();
        } else if (rdoMuiHuong.isSelected()) {
            loadMuiHuongToTable();
        }
    }
    
    
    private void tblThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhMouseClicked
        index = tblThuocTinh.getSelectedRow();

        if (index != -1) { // Check if a row is indeed selected
            // Retrieve the value from the 'Tên thuộc tính' column of the selected row
            String tenThuocTinh = tblThuocTinh.getValueAt(index, 2).toString(); // Assuming column index for 'Tên thuộc tính' is 2

            // Set the retrieved value to the text field
            txtThuocTinh.setText(tenThuocTinh);

            // Optionally, if you want to check which radio button should be selected based on 'Loại thuộc tính'
            String loaiThuocTinh = tblThuocTinh.getValueAt(index, 1).toString(); // Assuming column index for 'Loại thuộc tính' is 1
            switch (loaiThuocTinh) {
                case "Tên sản phẩm":
                rdoTenSp.setSelected(true);
                break;
                case "Thương hiệu":
                rdoThuongHieu.setSelected(true);
                break;
                case "Mùi hương":
                rdoMuiHuong.setSelected(true);
                break;
                default:

            }
        }
    }//GEN-LAST:event_tblThuocTinhMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaThuocTinh;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemThuocTinh;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaThuocTinh;
    private javax.swing.JButton btnlammoi1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboMuiHuong;
    private javax.swing.JComboBox<String> cboTenSp;
    private javax.swing.JComboBox<String> cboThuongHieu;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JRadioButton rdoMuiHuong;
    private javax.swing.JRadioButton rdoTenSp;
    private javax.swing.JRadioButton rdoThuongHieu;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblThuocTinh;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtDungTich;
    private javax.swing.JTextField txtMaSp;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtThuocTinh;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
