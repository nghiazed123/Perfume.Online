/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Model.NhanVienBang;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import service.NhanVienService;

/**
 *
 * @author NGUYEN NGUYEN TRONG NGHIA
 */
public class formnhanvien extends javax.swing.JPanel {

    private NhanVienService nvs = new NhanVienService();
    private DefaultTableModel model = new DefaultTableModel();
    NhanVienBang n = new NhanVienBang();
    ArrayList<NhanVienBang> v = new ArrayList<>();

    public formnhanvien() {
        initComponents();
        loadData();
    }

    public void loadData() {
    model = (DefaultTableModel) tblNhanVien.getModel();
    model.setRowCount(0);
    v = nvs.Getall(); // Cập nhật danh sách nhân viên từ cơ sở dữ liệu
    for (NhanVienBang a : v) {
        Object[] n = new Object[7];
        n[0] = a.getManv();
        n[1] = a.getTen();
        n[2] = a.getNgaysinh();
        n[3] = a.getGioitinh();
        n[4] = a.getSdt();
        n[5] = a.getDiachi();
        n[6] = a.isTrangthai();
        model.addRow(n);
    }
    updateMaNhanVienField(); // Cập nhật mã nhân viên tiếp theo
}

    
    private void updateMaNhanVienField() {
    if (v.size() > 0) {
        int maxMaNV = v.get(v.size() - 1).getManv();
        txtMaNV.setText(String.valueOf(maxMaNV + 1));
    } else {
        txtMaNV.setText("1");
    }
}
    
    
    void clearForm() {
    txtMaNV.setText("");
    txtTenNV.setText("");
    jDateNgaySinh.setDate(null);
    txtSDT.setText("");
    txtDiaChi.setText("");
    rdNam.setSelected(true);
    updateMaNhanVienField();
}

    
    
    public NhanVienBang getmodel() {
        try {
            NhanVienBang nv = new NhanVienBang();
            nv.setTen(txtTenNV.getText());
            Date date = jDateNgaySinh.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(date);
        nv.setNgaysinh(formattedDate);
            String gioiTinh = "";
            if (rdNam.isSelected()) {
                gioiTinh = "Nam";
            } else if (rdNu.isSelected()) {
                gioiTinh = "Nữ";
            }
            nv.setGioitinh(gioiTinh);
            nv.setSdt(Integer.parseInt(txtSDT.getText()));
            nv.setDiachi(txtDiaChi.getText());

            // Đặt lại trạng thái
//        boolean trangThai = false;
//        if (txtTrangThai.getText().equalsIgnoreCase("1")) {
//            trangThai = true;
//        } else if (txtTrangThai.getText().equalsIgnoreCase("0")) {
//            trangThai = false;
//        } else {
//            // Xử lý khi giá trị trạng thái không hợp lệ
//            // ...
//        }
//        nv.setTrangthai(trangThai);
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public NhanVienBang getmodel1() {
        try {
            NhanVienBang nv = new NhanVienBang();
            nv.setManv(Integer.parseInt(txtMaNV.getText()));
            nv.setTen(txtTenNV.getText());
            Date date = jDateNgaySinh.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(date);
        nv.setNgaysinh(formattedDate);
            String gioiTinh = "";
            if (rdNam.isSelected()) {
                gioiTinh = "Nam";
            } else if (rdNu.isSelected()) {
                gioiTinh = "Nữ";
            }
            nv.setGioitinh(gioiTinh);
            nv.setSdt(Integer.parseInt(txtSDT.getText()));
            nv.setDiachi(txtDiaChi.getText());

            // Đặt giá trị trạng thái dưới dạng boolean
//        boolean trangThai = false;
//        if (txtTrangThai.getText().equalsIgnoreCase("1")) {
//            trangThai = true;
//        }
//        nv.setTrangthai(trangThai);
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setmodel(NhanVienBang s) {
        try {
            txtMaNV.setText(String.valueOf(s.getManv()));
            txtTenNV.setText(s.getTen());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(s.getNgaysinh());
        jDateNgaySinh.setDate(date);
            String gioiTinh = s.getGioitinh();
            if (gioiTinh.equals("Nam")) {
                rdNam.setSelected(true);
            } else if (gioiTinh.equals("Nữ")) {
                rdNu.setSelected(true);
            }
            txtSDT.setText(String.valueOf(s.getSdt()));
            txtDiaChi.setText(s.getDiachi());

            // Lấy giá trị trạng thái dưới dạng boolean và đặt vào ô văn bản
//        String trangThai = s.isTrangthai() ? "1" : "0";
//        txtTrangThai.setText(trangThai);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean checkDuLieu() {
        if (txtTenNV.getText().trim().length() > 255) {
            JOptionPane.showMessageDialog(null, "Tên Nhân Viên không được vượt quá 255 ký tự.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    
    private boolean isValidPhoneNumber(String phoneNumber) {
    // Check if the phone number has 10 or 11 digits, starts with 0, and contains only digits
    return (phoneNumber.length() == 10 || phoneNumber.length() == 11) && phoneNumber.startsWith("0") && phoneNumber.matches("\\d+");
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnlammoikm = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        jDateNgaySinh = new com.toedter.calendar.JDateChooser();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng Khuyến Mãi"));

        jLabel6.setText("Tìm Kiếm :");

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên NV", "Ngày Sinh", "Giới Tính", "SDT", "Địa Chỉ"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhanVien);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addGap(1039, 1039, 1039))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1255, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(279, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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

        btnlammoikm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/cleaning.png"))); // NOI18N
        btnlammoikm.setText("Làm Mới");
        btnlammoikm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoikmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnlammoikm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(btnlammoikm)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel12.setText("Mã NV :");

        jLabel13.setText("Tên NV :");

        jLabel14.setText("Giới Tính");

        txtMaNV.setEditable(false);

        buttonGroup1.add(rdNam);
        rdNam.setSelected(true);
        rdNam.setText("Nam");
        rdNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNamActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdNu);
        rdNu.setText("Nữ");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(rdNam)
                                .addGap(33, 33, 33)
                                .addComponent(rdNu)))
                        .addGap(0, 249, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtTenNV)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdNam)
                    .addComponent(rdNu))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel15.setText("SĐT :");

        jLabel16.setText("Ngày Sinh :");

        jLabel17.setText("Địa Chỉ :");

        txtDiaChi.setColumns(20);
        txtDiaChi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDiaChi.setLineWrap(true);
        txtDiaChi.setRows(5);
        jScrollPane3.setViewportView(txtDiaChi);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSDT)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jDateNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
           if (txtTenNV.getText().isEmpty() || txtSDT.getText().isEmpty() || txtDiaChi.getText().isEmpty()|| jDateNgaySinh.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống dữ liệu");
            return;
        }
        if (!checkDuLieu()) {
            return;
        }
            if (!isValidPhoneNumber(txtSDT.getText())) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ! "
                    + "\n Vui lòng nhập số điện thoại có độ dài 10 hoặc 11 chữ số "
                    + "\n Số điện thoại phải bắt đầu bằng 0.");
            return; // Exit the method if the phone number is invalid
        }
      
        NhanVienBang nv = getmodel();
        if (nvs.add(nv)) {
            loadData();
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
            clearForm();
            updateMaNhanVienField();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Thêm thất bại");
    }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
         try {
             if (txtTenNV.getText().isEmpty() || txtSDT.getText().isEmpty() || txtDiaChi.getText().isEmpty()|| jDateNgaySinh.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống dữ liệu");
            return;
        }
        if (!checkDuLieu()) {
            return;
        }
            if (!isValidPhoneNumber(txtSDT.getText())) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ! "
                    + "\n Vui lòng nhập số điện thoại có độ dài 10 hoặc 11 chữ số "
                    + "\n Số điện thoại phải bắt đầu bằng 0.");
            return; // Exit the method if the phone number is invalid
        }
             
        if (tblNhanVien.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Chọn nhân viên cần sửa");
            return;
        }
        NhanVienBang nv = getmodel1();
        nvs.Edit(nv);
        loadData();
        JOptionPane.showMessageDialog(this, "Sửa dữ liệu thành công");
        clearForm();
        updateMaNhanVienField();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Sửa thất bại");
    }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
          try {
        int row = tblNhanVien.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn nhân viên cần xóa");
            return;
        }
        int manv = v.get(row).getManv();
        NhanVienBang nv = new NhanVienBang();
        nv.setManv(manv);
        if (nvs.Delete(nv) > 0) {
            loadData();
            JOptionPane.showMessageDialog(this, "Xóa dữ liệu thành công");
            clearForm();
            updateMaNhanVienField();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Xóa thất bại");
    }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnlammoikmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoikmActionPerformed
clearForm();
    updateMaNhanVienField();
    }//GEN-LAST:event_btnlammoikmActionPerformed

    private void rdNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNamActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
int selectedRow = tblNhanVien.getSelectedRow();
    if (selectedRow != -1) {
        NhanVienBang nv = v.get(selectedRow); // Lấy đối tượng NhanVienBang từ danh sách
        setmodel(nv); // Điền thông tin vào form
    }
    }//GEN-LAST:event_tblNhanVienMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnlammoikm;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser jDateNgaySinh;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
