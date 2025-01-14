/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Model.khachhang;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import service.KhachhangService;

/**
 *
 * @author NGUYEN TRONG NGHIA
 */
public class formKhachhang extends javax.swing.JPanel {

    DefaultTableModel dtm = new DefaultTableModel();
KhachhangService khService = new KhachhangService();
ArrayList<khachhang> list = new ArrayList<>();

/**
 * Creates new form formKhachhang
 */
public formKhachhang() {
    initComponents();
    loadData();
    updateMaKhachHangField();
}

public void loadData() {
    dtm = (DefaultTableModel) tblbangkhachhang.getModel();
    dtm.setRowCount(0);
    list = khService.getAll();
    for (khachhang a : list) {
        Object[] k = new Object[7];
        k[0] = a.getMakh();
        k[1] = a.getTen();
        k[2] = a.getNgaysinh();
        k[3] = a.getGioitinh();
        k[4] = a.getSdt();
        k[5] = a.getDiachi();
        k[6] = a.getEmail();
        dtm.addRow(k);
    }
}

public khachhang getModel() {
    khachhang a = new khachhang();
    a.setMakh(Integer.parseInt(txtmaKH.getText()));
    a.setTen(txthotenKH.getText());
    Date date = jDateNgaySinh.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(date);
        a.setNgaysinh(formattedDate);
    String gioiTinh = "";
    if (rdonam.isSelected()) {
        gioiTinh = "Nam";
    } else if (rdonu.isSelected()) {
        gioiTinh = "Nữ";
    }
    a.setGioitinh(gioiTinh);
    a.setSdt(txtsdtKH.getText());
    a.setDiachi(txtdiachiKH.getText());
    a.setEmail(txtemail.getText());
    return a;
}

public void setModel(khachhang s) {
    txtmaKH.setText(String.valueOf(s.getMakh()));
    txthotenKH.setText(s.getTen());
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(s.getNgaysinh());
            jDateNgaySinh.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    if (s.getGioitinh().equals("Nam")) {
        rdonam.setSelected(true);
    } else {
        rdonu.setSelected(true);
    }
    txtsdtKH.setText(s.getSdt());
    txtdiachiKH.setText(s.getDiachi());
    txtemail.setText(s.getEmail());
}


private void clearForm() {
    txtmaKH.setText("");
    txthotenKH.setText("");
    jDateNgaySinh.setDate(null);
    rdonam.setSelected(false);
    rdonu.setSelected(false);
    txtsdtKH.setText("");
    txtdiachiKH.setText("");
    txtemail.setText("");
}

private void updateMaKhachHangField() {
    int nextMakh = khService.getNextMakh();
    txtmaKH.setText(String.valueOf(nextMakh));
}

// Method to validate phone number (example logic)
private boolean isValidPhoneNumber(String phoneNumber) {
    // Check if the phone number has 10 or 11 digits, starts with 0, and contains only digits
    return (phoneNumber.length() == 10 || phoneNumber.length() == 11) && phoneNumber.startsWith("0") && phoneNumber.matches("\\d+");
}

private boolean isValidEmail(String email) {
    // Check if the email contains @gmail.com at the end
    return email.endsWith("@gmail.com");
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblbangkhachhang = new javax.swing.JTable();
        txttimkiemttkhachhang = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        btnthem = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnlammoi = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txthotenKH = new javax.swing.JTextField();
        rdonam = new javax.swing.JRadioButton();
        rdonu = new javax.swing.JRadioButton();
        txtmaKH = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtsdtKH = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtdiachiKH = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jDateNgaySinh = new com.toedter.calendar.JDateChooser();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng Khách Hàng"));

        tblbangkhachhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Họ tên", "Ngày sinh", "Giới tính", "SĐT", "Địa chỉ", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblbangkhachhang.setToolTipText("");
        tblbangkhachhang.setShowGrid(false);
        tblbangkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbangkhachhangMouseClicked(evt);
            }
        });
        tblbangkhachhang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblbangkhachhangKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblbangkhachhang);

        txttimkiemttkhachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimkiemttkhachhangActionPerformed(evt);
            }
        });
        txttimkiemttkhachhang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiemttkhachhangKeyReleased(evt);
            }
        });

        jLabel1.setText("Tìm Kiếm :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txttimkiemttkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txttimkiemttkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnthem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Awicons-Vista-Artistic-Add.24.png"))); // NOI18N
        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnsua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Edit.png"))); // NOI18N
        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnxoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Delete.png"))); // NOI18N
        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnlammoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/cleaning.png"))); // NOI18N
        btnlammoi.setText("Làm Mới");
        btnlammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnlammoi, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(btnthem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnxoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btnthem)
                .addGap(18, 18, 18)
                .addComponent(btnsua)
                .addGap(18, 18, 18)
                .addComponent(btnxoa)
                .addGap(18, 18, 18)
                .addComponent(btnlammoi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel10.setText("Mã KH:");

        jLabel12.setText("Họ Tên :");

        jLabel13.setText("Giới Tính :");

        buttonGroup1.add(rdonam);
        rdonam.setSelected(true);
        rdonam.setText("Nam");

        buttonGroup1.add(rdonu);
        rdonu.setText("Nữ");

        txtmaKH.setEditable(false);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(txtmaKH)
                        .addContainerGap())
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(rdonam)
                                .addGap(18, 18, 18)
                                .addComponent(rdonu))
                            .addComponent(jLabel10)
                            .addComponent(jLabel13))
                        .addGap(0, 261, Short.MAX_VALUE))
                    .addComponent(txthotenKH, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(255, 255, 255))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txthotenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdonam)
                    .addComponent(rdonu))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel14.setText("Số Điện Thoại :");

        jLabel15.setText("Ngày Sinh :");

        jLabel16.setText("Địa Chỉ :");

        txtdiachiKH.setColumns(20);
        txtdiachiKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtdiachiKH.setLineWrap(true);
        txtdiachiKH.setRows(5);
        jScrollPane3.setViewportView(txtdiachiKH);

        jLabel17.setText("Email :");

        txtemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
                    .addComponent(txtsdtKH)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtemail)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jDateNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsdtKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblbangkhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbangkhachhangMouseClicked
        int index = tblbangkhachhang.getSelectedRow();
    setModel(list.get(index));
    }//GEN-LAST:event_tblbangkhachhangMouseClicked

    private void tblbangkhachhangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblbangkhachhangKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblbangkhachhangKeyReleased

    private void txttimkiemttkhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimkiemttkhachhangActionPerformed
        TableRowSorter<DefaultTableModel> ab = new TableRowSorter<>(dtm);
        tblbangkhachhang.setRowSorter(ab);
        ab.setRowFilter(RowFilter.regexFilter(txttimkiemttkhachhang.getText()));
    }//GEN-LAST:event_txttimkiemttkhachhangActionPerformed

    private void txttimkiemttkhachhangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemttkhachhangKeyReleased
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) tblbangkhachhang.getModel();
        TableRowSorter<DefaultTableModel> ab = new TableRowSorter<>(dtm);
        tblbangkhachhang.setRowSorter(ab);
        ab.setRowFilter(RowFilter.regexFilter(txttimkiemttkhachhang.getText()));
    }//GEN-LAST:event_txttimkiemttkhachhangKeyReleased

    
    
    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
         try {
        // Check for empty fields
        if (txthotenKH.getText().isEmpty() || txtsdtKH.getText().isEmpty() || txtemail.getText().isEmpty() || txtdiachiKH.getText().isEmpty() || jDateNgaySinh.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống dữ liệu");
            return;
        }

        // Validate phone number
        if (!isValidPhoneNumber(txtsdtKH.getText())) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ! "
                    + "\n Vui lòng nhập số điện thoại có độ dài 10 hoặc 11 chữ số "
                    + "\n Số điện thoại phải bắt đầu bằng 0.");
            return; // Exit the method if the phone number is invalid
        }
        
        if (!isValidEmail(txtemail.getText())) {
            JOptionPane.showMessageDialog(this, "Email sai định dạng, phải là đuôi @gmail.com", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Get the customer model
        khachhang kh = getModel();

        // Add the customer
        if (khService.add(kh)) {
            loadData();
            
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công");
            clearForm();
            updateMaKhachHangField();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Thêm thất bại");
    }
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        try {
            if (txthotenKH.getText().isEmpty() || txtsdtKH.getText().isEmpty() || txtemail.getText().isEmpty() || txtdiachiKH.getText().isEmpty() || jDateNgaySinh.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống dữ liệu");
            return;
        }

        // Validate phone number
        if (!isValidPhoneNumber(txtsdtKH.getText())) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ! "
                    + "\n Vui lòng nhập số điện thoại có độ dài 10 hoặc 11 chữ số "
                    + "\n Số điện thoại phải bắt đầu bằng 0.");
            return; // Exit the method if the phone number is invalid
        }
        
        if (!isValidEmail(txtemail.getText())) {
            JOptionPane.showMessageDialog(this, "Email sai định dạng, phải là đuôi @gmail.com", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
            
        if (tblbangkhachhang.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Chọn khách hàng cần sửa");
            return;
        }
        khachhang kh = getModel();
        if (khService.edit(kh)) {
            loadData();
            JOptionPane.showMessageDialog(this, "Sửa dữ liệu thành công");
            clearForm();
            updateMaKhachHangField();
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Sửa thất bại");
    }
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
         try {
        int row = tblbangkhachhang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn khách hàng cần xóa");
            return;
        }
        int makh = list.get(row).getMakh();
        if (khService.delete(makh)) {
            loadData();
            updateMaKhachHangField();
            JOptionPane.showMessageDialog(this, "Xóa dữ liệu thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Xóa thất bại");
    }
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnlammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoiActionPerformed

        clearForm();
    updateMaKhachHangField();
    }//GEN-LAST:event_btnlammoiActionPerformed

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlammoi;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser jDateNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rdonam;
    private javax.swing.JRadioButton rdonu;
    private javax.swing.JTable tblbangkhachhang;
    private javax.swing.JTextArea txtdiachiKH;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txthotenKH;
    private javax.swing.JTextField txtmaKH;
    private javax.swing.JTextField txtsdtKH;
    private javax.swing.JTextField txttimkiemttkhachhang;
    // End of variables declaration//GEN-END:variables
}
