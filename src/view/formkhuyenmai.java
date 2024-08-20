/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Model.khachhang;
import Model.khuyenmai;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.KhuyenmaiService;

/**
 *
 * @author NGUYEN TRONG NGHIA
 */
public class formkhuyenmai extends javax.swing.JPanel {

    private DefaultTableModel model;
    private KhuyenmaiService repoKM;
    ArrayList<khuyenmai> listKM = new ArrayList<>();

    
    
    public formkhuyenmai() {
         initComponents();
        this.repoKM = new KhuyenmaiService();
        model = (DefaultTableModel) tblbangkhuyenmai.getModel();
        fillTable();
        updateMaKhuyenMaiField();
    }
        void fillTable() {
        ArrayList<khuyenmai> listKM = repoKM.getAll();
        model.setRowCount(0);

        for (khuyenmai kM : listKM) {
            String donVi = kM.getDonVi().equals("Giảm %") ? "Giảm %" : "";
            String trangThai = kM.isTrangthai() ? "Đang hoạt động" : "Kết thúc";

            Object[] data = {
                kM.getMakm(), kM.getTenkm(), kM.getNgaybatdau(), kM.getNgaykeythuc(), kM.getGiatri(), donVi,
                trangThai 
            };

            model.addRow(data);
        }
    }

    public khuyenmai readForm() {
        khuyenmai km = new khuyenmai();
        km.setMakm(Integer.parseInt(txtmakm.getText()));
        km.setTenkm(txttenkm.getText());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        Date startDate = jDateNgaybatdau.getDate();
        String formattedStartDate = dateFormat.format(startDate);
        km.setNgaybatdau(formattedStartDate);
        
        Date endDate = jDateNgayketthuc.getDate();
        String formattedEndDate = dateFormat.format(endDate);
        km.setNgaykeythuc(formattedEndDate);
        
        km.setGiatri(Integer.parseInt(txtgiatrigiam.getText()));
        km.setDonVi(rdoGiamPhanTram.isSelected() ? "Giảm %" : "");
        km.setTrangthai(cbbtrangthai.getSelectedItem().toString().equals("Đang hoạt động"));
        return km;
    }

    public boolean checkDuLieu() {

        if (txttenkm.getText().trim().length() > 255) {
            JOptionPane.showMessageDialog(null, "Tên Khuyến Mãi không được vượt quá 255 ký tự.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!rdoGiamPhanTram.isSelected()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn Đơn Vị.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
    try {
        int giaTriGiam = Integer.parseInt(txtgiatrigiam.getText().trim());
        if (giaTriGiam < 0 || giaTriGiam > 100) {
            JOptionPane.showMessageDialog(null, "Giá Trị Giảm không được nhỏ hơn 0 và không được lớn hơn 100.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Giá Trị Giảm phải là số nguyên.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return false;
    }

        if (jDateNgaybatdau.getDate()==null) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập Ngày Bắt Đầu.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (jDateNgayketthuc.getDate()==null) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập Ngày Kết Thúc.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    void getDataRow() {
        int selectedRow = tblbangkhuyenmai.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }

        txtmakm.setText(tblbangkhuyenmai.getValueAt(selectedRow, 0).toString());
        txttenkm.setText(tblbangkhuyenmai.getValueAt(selectedRow, 1).toString());

        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(tblbangkhuyenmai.getValueAt(selectedRow, 2).toString());
            jDateNgaybatdau.setDate(startDate);

            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(tblbangkhuyenmai.getValueAt(selectedRow, 3).toString());
            jDateNgayketthuc.setDate(endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        txtgiatrigiam.setText(tblbangkhuyenmai.getValueAt(selectedRow, 4).toString());

        String donVi = tblbangkhuyenmai.getValueAt(selectedRow, 5).toString();
        rdoGiamPhanTram.setSelected(donVi.equals("Giảm %"));

        String trangThai = tblbangkhuyenmai.getValueAt(selectedRow, 6).toString();
        cbbtrangthai.setSelectedItem(trangThai);
    }

    void clearForm() {
        txtmakm.setText("");
        txttenkm.setText("");
        jDateNgaybatdau.setDate(null);
        jDateNgayketthuc.setDate(null);
        txtgiatrigiam.setText("");
        rdoGiamPhanTram.setSelected(true);
        cbbtrangthai.setSelectedIndex(0);
    }
    
    
    public khuyenmai getPromotionDetails(String maKM) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().equals(maKM)) {
                khuyenmai km = new khuyenmai();
                km.setMakm(Integer.parseInt(model.getValueAt(i, 0).toString()));
                km.setTenkm(model.getValueAt(i, 1).toString());
                km.setGiatri(Integer.parseInt(model.getValueAt(i, 4).toString()));
                km.setDonVi(model.getValueAt(i, 5).toString());
                return km;
            }
        }
        return null;
    }
    
    private void updateMaKhuyenMaiField() {
    int nextMaKM = repoKM.getNextMaKM();
    txtmakm.setText(String.valueOf(nextMaKM));
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbangkhuyenmai = new javax.swing.JTable();
        txttimkiembangkhuyenmai = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        btnadd = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnlammoikm = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txttenkm = new javax.swing.JTextField();
        txtmakm = new javax.swing.JTextField();
        rdoGiamPhanTram = new javax.swing.JRadioButton();
        txtgiatrigiam = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cbbtrangthai = new javax.swing.JComboBox<>();
        jDateNgaybatdau = new com.toedter.calendar.JDateChooser();
        jDateNgayketthuc = new com.toedter.calendar.JDateChooser();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng Khuyến Mãi"));

        jLabel1.setText("Tìm Kiếm :");

        tblbangkhuyenmai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Khuyến Mãi", "Tên Khuyến Mãi", "Ngày bắt đầu", "Ngày kết thúc", "Giá trị", "Đơn vị", "Trạng thái"
            }
        ));
        tblbangkhuyenmai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbangkhuyenmaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblbangkhuyenmai);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txttimkiembangkhuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1261, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txttimkiembangkhuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnadd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Awicons-Vista-Artistic-Add.24.png"))); // NOI18N
        btnadd.setText("Thêm");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btnedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Edit.png"))); // NOI18N
        btnedit.setText("Sửa");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });

        btndelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Delete.png"))); // NOI18N
        btndelete.setText("Xóa");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
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
                    .addComponent(btnadd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnedit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btndelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btnadd)
                .addGap(18, 18, 18)
                .addComponent(btnedit)
                .addGap(18, 18, 18)
                .addComponent(btndelete)
                .addGap(18, 18, 18)
                .addComponent(btnlammoikm)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel10.setText("Mã KM:");

        jLabel12.setText("Tên KM :");

        jLabel13.setText("Giá Trị Giảm :");

        txtmakm.setEditable(false);

        rdoGiamPhanTram.setSelected(true);
        rdoGiamPhanTram.setText("Giảm %");
        rdoGiamPhanTram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoGiamPhanTramActionPerformed(evt);
            }
        });

        txtgiatrigiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgiatrigiamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttenkm)
                            .addComponent(txtmakm, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(txtgiatrigiam, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoGiamPhanTram)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmakm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txttenkm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtgiatrigiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoGiamPhanTram))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel14.setText("Ngày Bắt Đầu :");

        jLabel15.setText("Ngày Kết Thúc :");

        jLabel17.setText("Trạng Thái :");

        cbbtrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang hoạt động", "Kết thúc" }));
        cbbtrangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbtrangthaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbtrangthai, 0, 714, Short.MAX_VALUE)
                    .addComponent(jDateNgaybatdau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jDateNgayketthuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateNgaybatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateNgayketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1303, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 4, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 4, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 628, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
          if (txttenkm.getText().isEmpty() || txtgiatrigiam.getText().isEmpty() || jDateNgaybatdau.getDate() == null|| jDateNgayketthuc.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống dữ liệu");
            return;
        }
        if (!checkDuLieu()) {
            return;
        }try {
          
        khuyenmai km = readForm();
        if (repoKM.them(km) > 0) {
            fillTable();
            updateMaKhuyenMaiField();
            JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Thêm thất bại");
    }
    }//GEN-LAST:event_btnaddActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        if (txttenkm.getText().isEmpty() || txtgiatrigiam.getText().isEmpty() || jDateNgaybatdau.getDate() == null|| jDateNgayketthuc.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống dữ liệu");
            return;
        }
        if (!checkDuLieu()) {
            return;
        } 
        
        try {
        if (tblbangkhuyenmai.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Chọn khuyến mãi cần sửa");
            return;
        }
        khuyenmai km = readForm();
        if (repoKM.sua(km.getMakm(), km) > 0) {
            fillTable();
            JOptionPane.showMessageDialog(this, "Sửa dữ liệu thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Sửa thất bại");
    }
    }//GEN-LAST:event_btneditActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        try {
        int row = tblbangkhuyenmai.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn khuyến mãi cần xóa");
            return;
        }
        int makm = listKM.get(row).getMakm();
        if (repoKM.xoa(makm)) {
            fillTable();
            updateMaKhuyenMaiField();
            JOptionPane.showMessageDialog(this, "Xóa dữ liệu thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Xóa thất bại");
    }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btnlammoikmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoikmActionPerformed

        clearForm();
    updateMaKhuyenMaiField();
    }//GEN-LAST:event_btnlammoikmActionPerformed

    private void tblbangkhuyenmaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbangkhuyenmaiMouseClicked
        getDataRow();
    }//GEN-LAST:event_tblbangkhuyenmaiMouseClicked

    private void rdoGiamPhanTramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoGiamPhanTramActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoGiamPhanTramActionPerformed

    private void txtgiatrigiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgiatrigiamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgiatrigiamActionPerformed

    private void cbbtrangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbtrangthaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbtrangthaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnlammoikm;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbtrangthai;
    private com.toedter.calendar.JDateChooser jDateNgaybatdau;
    private com.toedter.calendar.JDateChooser jDateNgayketthuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoGiamPhanTram;
    private javax.swing.JTable tblbangkhuyenmai;
    private javax.swing.JTextField txtgiatrigiam;
    private javax.swing.JTextField txtmakm;
    private javax.swing.JTextField txttenkm;
    private javax.swing.JTextField txttimkiembangkhuyenmai;
    // End of variables declaration//GEN-END:variables
}
